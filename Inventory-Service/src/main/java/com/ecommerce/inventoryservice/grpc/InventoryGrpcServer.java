package com.ecommerce.inventoryservice.grpc;

import com.ecommerce.inventoryservice.service.InventoryService;
import inventoryGrpc.InventoryRequest;
import inventoryGrpc.InventoryResponse;
import inventoryGrpc.ProductResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import inventoryGrpc.InventoryServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@GrpcService
public class InventoryGrpcServer extends InventoryServiceGrpc.InventoryServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(InventoryGrpcServer.class);
    private final InventoryService inventoryService;

    public InventoryGrpcServer(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @Override
    public void isInStock(InventoryRequest request, StreamObserver<InventoryResponse> responseObserver) {
        log.info("isInStock call received {}" , request.toString());
        //First convert the incoming grpc request to http(DTO) req
        List<com.ecommerce.inventoryservice.dto.InventoryRequest> allRequest  = request.getProductList().stream().map(req -> toDTO(req)).toList();
        //Then use those req to call the isInStock method from the inventory service that gives dto response
        List<com.ecommerce.inventoryservice.dto.InventoryResponse> response = inventoryService.isInStock(allRequest);

        //From that response , create a list of product response , which means basically converting it to grpc response
        List<inventoryGrpc.ProductResponse> responseList = response.stream().map(req->{
            ProductResponse productResult = ProductResponse.newBuilder()
                    .setProductId(String.valueOf(req.getProductId()))
                    .setIsInStock(req.isInStock())
                    .build();
            return productResult;
        }).toList();

        InventoryResponse finalResponse = InventoryResponse.newBuilder().addAllProduct(responseList).build();

        responseObserver.onNext(finalResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void deductFromInventoryGrpc(InventoryRequest request, StreamObserver<InventoryResponse> responseObserver) {
        log.info("deductFromInventoryGrpc call received {}" , request.toString());
        //First convert the incoming grpc request to http(DTO) req
        List<com.ecommerce.inventoryservice.dto.InventoryRequest> allRequest  = request.getProductList().stream().map(req -> toDTO(req)).toList();

        //Then use those req to call the isInStock method from the inventory service that gives dto response
        List<com.ecommerce.inventoryservice.dto.InventoryResponse> response = inventoryService.deductFromInventory(allRequest);
        //From that response , create a list of product response , which means basically converting it to grpc response
        List<inventoryGrpc.ProductResponse> responseList = response.stream().map(req->{
            ProductResponse productResult = ProductResponse.newBuilder()
                    .setProductId(String.valueOf(req.getProductId()))
                    .setIsInStock(req.isInStock())
                    .build();
            return productResult;
        }).toList();

        InventoryResponse finalResponse = InventoryResponse.newBuilder().addAllProduct(responseList).build();

        responseObserver.onNext(finalResponse);
        responseObserver.onCompleted();
    }

    com.ecommerce.inventoryservice.dto.InventoryRequest toDTO(inventoryGrpc.ProductRequest productRequest){
        com.ecommerce.inventoryservice.dto.InventoryRequest request = new com.ecommerce.inventoryservice.dto.InventoryRequest();
        request.setProductId(UUID.fromString(productRequest.getProductId()));
        request.setQuantity(productRequest.getQuantity());
        return request;
    }
}
