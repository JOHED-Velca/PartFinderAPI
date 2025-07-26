package com.inventory.partfinder.dto;

public class PartSearchDto {
    private Long id;
    private String name;
    private String sku;
    private Integer quantity;
    private Integer aisle;
    private String side;
    private Integer level;

    public PartSearchDto(Long id, String name, String sku, Integer quantity, Integer aisle, String side, Integer level) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.quantity = quantity;
        this.aisle = aisle;
        this.side = side;
        this.level = level;
    }

    //Getters only (optional to add setters)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSku() { return sku; }
    public Integer getQuantity() { return quantity; }
    public Integer getAisle() { return  aisle; }
    public String getSide() { return side; }
    public Integer getLevel() { return level; }
}
