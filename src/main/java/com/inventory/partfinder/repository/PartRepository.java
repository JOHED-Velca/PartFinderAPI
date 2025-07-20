package com.inventory.partfinder.repository;

import com.inventory.partfinder.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    List<Part> findByNameContainingIgnoreCase(String name);

    List<Part> findBySkuContainingIgnoreCase(String sku);

    @Query("SELECT p FROM Part p " +
            "WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:sku IS NULL OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :sku, '%'))) " +
            "AND (:aisle IS NULL OR p.level.shelf.aisle.number = :aisle) " +
            "AND (:side IS NULL OR p.level.shelf.side = :side) " +
            "AND (:level IS NULL OR p.level.levelNumber = :level)")
    List<Part> advancedSearch(@Param("name") String name,
                              @Param("sku") String sku,
                              @Param("aisle") Integer aisle,
                              @Param("side") String side,
                              @Param("level") Integer level);
}
