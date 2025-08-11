package com.inventory.partfinder.controller;

import com.inventory.partfinder.dto.PartDto;
import com.inventory.partfinder.dto.PartSearchDto;
import com.inventory.partfinder.model.Level;
import com.inventory.partfinder.model.Part;
import com.inventory.partfinder.model.Shelf;
import com.inventory.partfinder.model.ShelfSide;
import com.inventory.partfinder.repository.LevelRepository;
import com.inventory.partfinder.repository.PartRepository;
import com.inventory.partfinder.service.PartService;
import com.inventory.partfinder.specification.PartSpecification;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/parts")
public class PartController {

    private final PartService partService;
    private final LevelRepository levelRepository;
    private final PartRepository partRepository;

    public PartController(PartService partService, LevelRepository levelRepository, PartRepository partRepository) {
        this.partService = partService;
        this.levelRepository=levelRepository;
        this.partRepository=partRepository;
    }

    @GetMapping
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/{id}")
    public Part getPartById(@PathVariable Long id) {
        return partService.getPartById(id);
    }

    @GetMapping("/search")
    public Page<PartSearchDto> searchParts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sku,
            @RequestParam(required = false) Integer aisle,
            @RequestParam(required = false) String side,
            @RequestParam(required = false) Integer level,
            Pageable pageable
    ) {
        ShelfSide shelfSide = null;
        if (side != null) {
            try {
                shelfSide = ShelfSide.valueOf(side.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid shelf side: " + side);
            }
        }
        Page<Part> partPage = partRepository.findAll(
                PartSpecification.matches(name, sku, aisle, shelfSide, level),
                pageable
        );

        // Null-safe mapping to DTO (handles parts without a level)
        return partPage.map(this::toDto);
    }

    /** Null-safe mapper: Part -> PartSearchDto */
    private PartSearchDto toDto(Part part) {
        Integer aisleNum = null;
        String sideStr = null;
        Integer levelNum = null;

        if (part.getLevel() != null) {
            levelNum = part.getLevel().getLevelNumber();
            if (part.getLevel().getShelf() != null) {
                Shelf shelf = part.getLevel().getShelf();
                if (shelf.getSide() != null) {
                    sideStr = shelf.getSide().toString();
                }
                if (shelf.getAisle() != null) {
                    aisleNum = shelf.getAisle().getNumber();
                }
            }
        }

        return new PartSearchDto(
                part.getId(),
                part.getName(),
                part.getSku(),
                part.getQuantity(),
                aisleNum,
                sideStr,
                levelNum
        );
    }

    @PostMapping
    public Part savePart(@Valid @RequestBody PartDto partDto) {
//        Level level = levelRepository.findById(partDto.getLevelId())
//                .orElseThrow(() -> new RuntimeException("Level not found"));

        Part part = new Part();
        part.setName(partDto.getName());
        part.setSku(partDto.getSku());
        part.setQuantity(partDto.getQuantity());
//        part.setLevel(level);
        if (partDto.getLevelId() != null) {
            Level level = levelRepository.findById(partDto.getLevelId())
                    .orElseThrow(() ->  new RuntimeException("Level not found"));
            part.setLevel(level);
        }

        return partService.savePart(part);
    }

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }

    @PutMapping("/{id}")
    public Part updatePart(@PathVariable Long id, @Valid @RequestBody PartDto partDto) {
//        Level level = levelRepository.findById(partDto.getLevelId())
//                .orElseThrow(() -> new RuntimeException("Level not found"));

        Part updatedPart = new Part();
        updatedPart.setName(partDto.getName());
        updatedPart.setSku(partDto.getSku());
        updatedPart.setQuantity(partDto.getQuantity());
//        updatedPart.setLevel(level);
        if (partDto.getLevelId() != null) {
            Level level = levelRepository.findById(partDto.getLevelId())
                    .orElseThrow(() -> new RuntimeException("Level not found"));
            updatedPart.setLevel(level);
        } else {
            // Intentionally allow clearing the level by sending null
            updatedPart.setLevel(null);
        }

        return partService.updatePart(id, updatedPart);
    }
}
