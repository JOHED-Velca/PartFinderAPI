package com.inventory.partfinder.service;

import com.inventory.partfinder.model.Part;
import com.inventory.partfinder.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService{
    private final PartRepository partRepository;

    //Constructor injection
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    @Override
    public Part getPartById(Long id) {
        return partRepository.findById(id).orElseThrow();
    }

    @Override
    public Part savePart(Part part) {
        return partRepository.save(part);
    }

    @Override
    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }
}
