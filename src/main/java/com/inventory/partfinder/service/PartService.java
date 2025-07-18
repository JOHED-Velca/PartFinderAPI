package com.inventory.partfinder.service;

import com.inventory.partfinder.model.Part;

import java.util.List;

public interface PartService {
    List<Part> getAllParts();
    Part getPartById(Long id);
    Part savePart(Part part);
    void deletePart(Long id);
    Part updatePart(Long id, Part updatedPart);
}
