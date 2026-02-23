package com.ecommerce.inventoryservice.repository;

import com.ecommerce.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    List<Inventory> findAllByProductIdIn(List<UUID> ids);
    Inventory findByProductId(UUID id);
    boolean existsByProductId(UUID id);
}
