package com.ecommerce.orderservice.dto;

import java.util.List;

public class OrderRequestDto {
    private List<OrderLineItemRequest> orderList;

    public List<OrderLineItemRequest> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderLineItemRequest> orderList) {
        this.orderList = orderList;
    }
}
