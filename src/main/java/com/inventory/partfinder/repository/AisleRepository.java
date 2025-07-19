package com.inventory.partfinder.repository;

import com.inventory.partfinder.model.Aisle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AisleRepository extends JpaRepository<Aisle, Long> {
}
