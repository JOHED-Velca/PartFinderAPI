package com.inventory.partfinder.controller;

import com.inventory.partfinder.dto.PartDto;
import com.inventory.partfinder.model.Level;
import com.inventory.partfinder.model.Part;
import com.inventory.partfinder.repository.LevelRepository;
import com.inventory.partfinder.service.PartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    private final PartService partService;
    private final LevelRepository levelRepository;

    public PartController(PartService partService, LevelRepository levelRepository) {
        this.partService = partService;
        this.levelRepository=levelRepository;
    }

    @GetMapping
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/{id}")
    public Part getPartById(@PathVariable Long id) {
        return partService.getPartById(id);
    }

    @PostMapping
    public Part savePart(@Valid @RequestBody PartDto partDto) {
        Level level = levelRepository.findById(partDto.getLevelId())
                .orElseThrow(() -> new RuntimeException("Level not found"));

        Part part = new Part();
        part.setName(partDto.getName());
        part.setSku(partDto.getSku());
        part.setQuantity(partDto.getQuantity());
        part.setLevel(level);

        return partService.savePart(part);
    }

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }

    @PutMapping("/{id}")
    public Part updatePart(@PathVariable Long id, @Valid @RequestBody PartDto partDto) {
        Level level = levelRepository.findById(partDto.getLevelId())
                .orElseThrow(() -> new RuntimeException("Level not found"));

        Part updatedPart = new Part();
        updatedPart.setName(partDto.getName());
        updatedPart.setSku(partDto.getSku());
        updatedPart.setQuantity(partDto.getQuantity());
        updatedPart.setLevel(level);

        return partService.updatePart(id, updatedPart);
    }
}
