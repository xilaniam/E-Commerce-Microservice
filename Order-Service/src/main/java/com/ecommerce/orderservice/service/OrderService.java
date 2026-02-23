package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.OrderServiceApplication;
import com.ecommerce.orderservice.dto.OrderLineItemRequest;
import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.grpc.InventoryGrpcClient;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderLineItem;
import com.ecommerce.orderservice.repository.OrderRepository;
import inventoryGrpc.InventoryResponse;
import inventoryGrpc.ProductResponse;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final InventoryGrpcClient inventoryGrpcClient;
    public OrderService(OrderRepository orderRepository , InventoryGrpcClient inventoryGrpcClient){
        this.inventoryGrpcClient = inventoryGrpcClient;
        this.orderRepository = orderRepository;
    }

    public String placeOrder(OrderRequestDto orderReq){
        Order order = new Order();

        List<OrderLineItem> orderLineItems = orderReq.getOrderList().stream().map(orderItem -> toOrderLineItem(orderItem , order)).toList();

        order.setOrderLineItemList(orderLineItems);

        //Now check in inventory and if the product is in stock
        InventoryResponse itemList = inventoryGrpcClient.isInStock(orderReq);

        log.info("Creating Order : {}" , order);

        boolean allProductInStock = itemList.getProductList().stream().allMatch(ProductResponse::getIsInStock);

        //If it is in stock save the order repository
        if(allProductInStock){
            orderRepository.save(order);
            //Deduct item quantity from the inventory
            inventoryGrpcClient.deductFromInventory(orderReq);
            return "OrderPlaced : " + order;
        }
        else {
            throw new IllegalArgumentException("Product is not in stock");
        }
    }

    private OrderLineItem toOrderLineItem(OrderLineItemRequest lineItemRequest , Order order){
        OrderLineItem item = new OrderLineItem();
        item.setProductId(lineItemRequest.getProductId());
        item.setPrice(lineItemRequest.getPrice());
        item.setQuantity(lineItemRequest.getQuantity());
        item.setOrder(order);
        return item;
    }
}
