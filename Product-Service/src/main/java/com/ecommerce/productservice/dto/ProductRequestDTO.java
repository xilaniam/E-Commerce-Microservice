package com.ecommerce.productservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductRequestDTO {
    @NotBlank(message = "Name of the product is required")
    private String name;
    private String description;
    @NotNull(message = "Price of the product is required")
    @DecimalMin(value = "0" , message = "Price cannot be negative")
    private BigDecimal price;

    @NotNull(message = "Quantity of the product is required")
    @DecimalMin(value = "0" , message = "value must be positive")
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
