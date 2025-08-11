package com.inventory.partfinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartSearchDto {
    private Long id;
    private String name;
    private String sku;
    private Integer quantity;
    private Integer aisle;
    private String side;
    private Integer level;


}
