package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<String> placeOrder(@Valid @RequestBody OrderRequestDto orderRequestDto){
        String response = orderService.placeOrder(orderRequestDto);
        return ResponseEntity.ok().body(response);
    }
}
