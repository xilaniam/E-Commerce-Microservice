package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.InventoryRequest;
import com.ecommerce.inventoryservice.dto.InventoryResponse;
import com.ecommerce.inventoryservice.exception.ProductDoesntExistsException;
import com.ecommerce.inventoryservice.exception.StockNegativeException;
import com.ecommerce.inventoryservice.model.Inventory;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import inventoryGrpc.InventoryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    //this is called from the order service so grpc
    public List<InventoryResponse> isInStock(List<InventoryRequest> inventoryRequest){
        Map<UUID,Integer> requestedQty = inventoryRequest.stream().collect(Collectors.toMap(InventoryRequest::getProductId,InventoryRequest::getQuantity));
        List<Inventory> inventoryList = inventoryRepository.findAllByProductIdIn(inventoryRequest.stream().map(item -> item.getProductId()).toList());
        List<InventoryResponse> inventoryResponseList = inventoryList.stream().map(inventoryItem -> toInventoryResponse(inventoryItem , requestedQty.get(inventoryItem.getProductId()))).toList();
        return inventoryResponseList;
    }

    //call this on grpc or more like when product created event is occurred, because this is called only when a new product is added
    public List<InventoryResponse> addToInventory(List<InventoryRequest> request){
        List<Inventory> items = request.stream().map(req -> toInventoryItem(req)).toList();
        Map<UUID,Integer> requestedQuantity = request.stream().collect(Collectors.toMap(InventoryRequest::getProductId , InventoryRequest::getQuantity));
        items.stream().forEach(item -> {
            Integer finalQuantity = requestedQuantity.get(item.getProductId());
            if(inventoryRepository.existsByProductId(item.getProductId())){
                finalQuantity = requestedQuantity.get(item.getProductId()) + item.getQuantity();
                log.info("The product already exists so adding the quantity of the inventory : {} + {} = {}" , item.getQuantity() , requestedQuantity.get(item.getProductId())  , finalQuantity);
            }
            item.setQuantity(finalQuantity);
            inventoryRepository.save(item);

        });

        return items.stream().map(i -> toInventoryResponse(i)).toList();
    }

    // this is done by the user;/seller and also from kafka , when order is placed successfully , update the inventory
    public List<InventoryResponse> deductFromInventory(List<InventoryRequest> request){
        List<Inventory> items = request.stream().map(req->{
            if(!inventoryRepository.existsByProductId(req.getProductId())){
                throw new ProductDoesntExistsException("This product of productID : " + req.getProductId() + "doesn't exists");
            }

            Inventory item = inventoryRepository.findByProductId(req.getProductId());

            Integer finalQuantity = item.getQuantity() - req.getQuantity();

            item.setQuantity(finalQuantity);

            if(finalQuantity<0) {
                throw new StockNegativeException("Stock cannot be negative : No Stock of this product available " + item.getProductId());
            }

            inventoryRepository.save(item);
            return item;
        }).toList();

        return items.stream().map(item -> toInventoryResponse(item , item.getQuantity())).toList();
    }

    private InventoryResponse toInventoryResponse(Inventory inventoryItem){
        return toInventoryResponse(inventoryItem,0);
    }
    private InventoryResponse toInventoryResponse(Inventory inventoryItem , Integer quantity){
        InventoryResponse response = new InventoryResponse();
        response.setProductId(inventoryItem.getProductId());
        response.setInStock(inventoryItem.getQuantity()>quantity);
        return response;
    }

    private Inventory toInventoryItem(InventoryRequest request){
        Inventory item = new Inventory();
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());
        return item;
    }
}
