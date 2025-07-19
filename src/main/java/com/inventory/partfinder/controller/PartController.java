package com.inventory.partfinder.controller;

import com.inventory.partfinder.model.Part;
import com.inventory.partfinder.service.PartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
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
    public Part savePart(@Valid @RequestBody Part part) {
        return partService.savePart(part);
    }

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }

    @PutMapping("/{id}")
    public Part updatePart(@PathVariable Long id, @Valid @RequestBody Part updatedPart) {
        return partService.updatePart(id, updatedPart);
    }
}
