package com.inventory.partfinder.repository;

import com.inventory.partfinder.model.Part;
import com.inventory.partfinder.model.ShelfSide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    List<Part> findByNameContainingIgnoreCase(String name);

    List<Part> findBySkuContainingIgnoreCase(String sku);

    @Query("""
        SELECT p FROM Part p
        WHERE (CAST(:name AS string) IS NULL OR p.name ILIKE CONCAT('%', :name, '%'))
          AND (CAST(:sku AS string) IS NULL OR p.sku ILIKE CONCAT('%', :sku, '%'))
          AND (:aisle IS NULL OR p.level.shelf.aisle.number = :aisle)
          AND (:side IS NULL OR p.level.shelf.side = :side)
          AND (:level IS NULL OR p.level.levelNumber = :level)
    """) // queries need to be adjusted 'cause it is not working
    List<Part> advancedSearch(
            @Param("name") @Nullable String name,
            @Param("sku") @Nullable String sku,
            @Param("aisle") @Nullable Integer aisle,
            @Param("side") @Nullable ShelfSide side,
            @Param("level") @Nullable Integer level
    );
}