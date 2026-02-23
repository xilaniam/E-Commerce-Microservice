package com.ecommerce.inventoryservice.dto;

import java.util.UUID;

public class InventoryResponse {
    private UUID productId;
    private boolean isInStock;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
