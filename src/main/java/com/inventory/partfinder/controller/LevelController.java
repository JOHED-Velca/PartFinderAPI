package com.inventory.partfinder.controller;

import com.inventory.partfinder.model.Level;
import com.inventory.partfinder.repository.LevelRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
public class LevelController {
    private final LevelRepository levelRepository;

    public LevelController(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @GetMapping
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }
}
