package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductRequestDTO;
import com.ecommerce.productservice.dto.ProductResponseDTO;
import com.ecommerce.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@Tag(name = "Products" , description = "API for managing products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    @Operation(description = "Get all the products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> productResponseDTOS = productService.getAllProducts();
        return ResponseEntity.ok().body(productResponseDTOS);
    }
    @GetMapping("/{id}")
    @Operation(description = "Get a product by their ID")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable  UUID id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
    @PostMapping
    @Operation(description = "Create a new product")
    public ResponseEntity<List<ProductResponseDTO>> createProduct(@Validated({Default.class}) @RequestBody List<ProductRequestDTO> reqDTO){
        List<ProductResponseDTO> responseDTO = productService.addProduct(reqDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(description = "Modify an existing product using their ID")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID id , @Validated({Default.class})@RequestBody ProductRequestDTO requestDTO){
        return ResponseEntity.ok().body(productService.updateProduct(id,requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete an existing product")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id){
        productService.removeProductById(id);
        return ResponseEntity.noContent().build();
    }

}
