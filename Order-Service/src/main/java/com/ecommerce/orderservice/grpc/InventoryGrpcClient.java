package com.ecommerce.orderservice.grpc;

import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.service.OrderService;
import inventoryGrpc.InventoryRequest;
import inventoryGrpc.InventoryResponse;
import inventoryGrpc.InventoryServiceGrpc;
import inventoryGrpc.ProductRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(InventoryGrpcClient.class);

    private final InventoryServiceGrpc.InventoryServiceBlockingStub blockingStub;

    public InventoryGrpcClient(@Value("${inventory.service.address:localhost}") String serverAddress ,@Value("${inventory.service.grpc.port:3002}") int serverPort){
        log.info("Connecting to Inventory Service : " + serverAddress + " : " + serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress,serverPort).usePlaintext().build();
        blockingStub = InventoryServiceGrpc.newBlockingStub(channel);
    }

    public InventoryResponse isInStock(OrderRequestDto requestDto){
        InventoryRequest request = createInventoryRequest(requestDto);
        InventoryResponse response = blockingStub.isInStock(request);

        log.info("Received response from Inventory Service via Grpc : " + response);

        return response;
    }

    public InventoryResponse deductFromInventory(OrderRequestDto requestDto){
        InventoryRequest request = createInventoryRequest(requestDto);
        InventoryResponse response = blockingStub.deductFromInventoryGrpc(request);
        log.info("Received response from Inventory Service via Grpc : " + response);
        return response;
    }

    private InventoryRequest createInventoryRequest(OrderRequestDto requestDto){
        List<ProductRequest> requestList = requestDto.getOrderList().stream().map(
                order->{
                    ProductRequest pr = ProductRequest.newBuilder()
                            .setProductId(String.valueOf(order.getProductId()))
                            .setQuantity(order.getQuantity())
                            .build();
                    return pr;
                }
        ).toList();

        InventoryRequest request = InventoryRequest.newBuilder()
                .addAllProduct(requestList)
                .build();
        log.info("Sending request to the inventory " + request);
        return request;
    }

}
