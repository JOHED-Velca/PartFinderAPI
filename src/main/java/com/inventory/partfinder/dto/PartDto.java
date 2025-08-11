package com.inventory.partfinder.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartDto {

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    @NotNull
    @Min(0)
    private Integer quantity;

    //@NotNull(message = "Level ID is required")
    private Long levelId;
}
