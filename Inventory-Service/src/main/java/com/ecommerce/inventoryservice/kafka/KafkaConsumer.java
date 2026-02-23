package com.ecommerce.inventoryservice.kafka;

import com.ecommerce.inventoryservice.dto.InventoryRequest;
import com.ecommerce.inventoryservice.service.InventoryService;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import product.events.ProductEvents;

import java.util.List;
import java.util.UUID;

@Service
public class KafkaConsumer {
    private final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private final InventoryService inventoryService;

    public KafkaConsumer(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "product", groupId = "inventory-service")
    public void ListenProductEvent(byte[] event){
        try {
            ProductEvents product = ProductEvents.parseFrom(event);
            List<InventoryRequest> productList = product.getProductList().stream().map(
                    p -> {
                        InventoryRequest request = new InventoryRequest();
                        request.setProductId(UUID.fromString(p.getProductId()));
                        request.setQuantity(Integer.valueOf(p.getQuantity()));
                        return request;
                    }
            ).toList();
            inventoryService.addToInventory(productList);
            log.info("Successfully added items to the inventory : {}" , productList);
        }
        catch (InvalidProtocolBufferException ex){
            log.error("Error deserializing event : " , ex.getMessage());
        }
    }
}
