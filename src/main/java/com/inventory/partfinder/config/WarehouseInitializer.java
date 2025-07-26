package com.inventory.partfinder.config;

import com.inventory.partfinder.model.Aisle;
import com.inventory.partfinder.model.Level;
import com.inventory.partfinder.model.Shelf;
import com.inventory.partfinder.model.ShelfSide;
import com.inventory.partfinder.repository.AisleRepository;
import com.inventory.partfinder.repository.LevelRepository;
import com.inventory.partfinder.repository.ShelfRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class WarehouseInitializer {
    private final AisleRepository aisleRepository;
    private final ShelfRepository shelfRepository;
    private final LevelRepository levelRepository;

    public WarehouseInitializer(
            AisleRepository aisleRepository,
            ShelfRepository shelfRepository,
            LevelRepository levelRepository
    ) {
        this.aisleRepository = aisleRepository;
        this.shelfRepository = shelfRepository;
        this.levelRepository = levelRepository;
    }

    @PostConstruct
    public void init() {
        if (!aisleRepository.findAll().isEmpty()) return; // Prevent duplicate init

        for (int aisleNum = 1; aisleNum <= 5; aisleNum++) {
            Aisle aisle = new Aisle();
            aisle.setNumber(aisleNum);
            aisleRepository.save(aisle);

            for (ShelfSide side : ShelfSide.values()) {
                Shelf shelf = new Shelf();
                shelf.setAisle(aisle);
                shelf.setSide(side);
                shelfRepository.save(shelf);

                for (int levelNum = 1; levelNum <= 3; levelNum++) {
                    Level level = new Level();
                    level.setShelf(shelf);
                    level.setLevelNumber(levelNum);
                    levelRepository.save(level);
                }
            }
        }

        System.out.println("✅ Warehouse initialized: 5 aisles, 10 shelves, 30 levels.");
    }
}
