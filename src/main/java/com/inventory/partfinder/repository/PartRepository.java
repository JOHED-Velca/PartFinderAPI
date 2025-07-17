package com.inventory.partfinder.repository;

import com.inventory.partfinder.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> { //an interface (PartRepository) -> inherits database methods from Jpa... <entity the repo manages, primary key>
}
