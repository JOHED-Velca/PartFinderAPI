package com.inventory.partfinder.specification;

import com.inventory.partfinder.model.Part;
import com.inventory.partfinder.model.ShelfSide;
import org.springframework.data.jpa.domain.Specification;

public class PartSpecification {
    public static Specification<Part> matches(
            String name, String sku, Integer aisle, ShelfSide side, Integer level
    ) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (name != null && !name.isBlank()) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (sku != null && !sku.isBlank()) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("sku")), "%" + sku.toLowerCase() + "%"));
            }
            if (aisle != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("level").get("shelf").get("aisle").get("number"), aisle));
            }
            if (side != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("level").get("shelf").get("side"), side));
            }
            if (level != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("level").get("levelNumber"), level));
            }

            return predicates;
        };
    }
}
