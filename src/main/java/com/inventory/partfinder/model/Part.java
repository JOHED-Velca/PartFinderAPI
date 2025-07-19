package com.inventory.partfinder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be blank")
    @Size(max=100, message="Name must be at most 100 characters")
    private String name;

    @NotBlank(message = "SKU must not be blank")
    @Size(max=50, message="Name must be at most 50 characters")
    private String sku;

    @NotNull(message = "Quantity is required")
    @Min(value=0, message="Quantity must be 0 or more")
    private int quantity;

    @NotBlank(message = "Location must not be blank")
    @Size(max = 100, message = "Location must be at most 100 characters")
    private String location;
}
