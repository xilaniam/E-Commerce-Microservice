package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductRequestDTO;
import com.ecommerce.productservice.dto.ProductResponseDTO;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.kafka.KafkaProducer;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final KafkaProducer kafkaProducer;
    public ProductService(ProductRepository productRepository, KafkaProducer kafkaProducer){
        this.productRepository = productRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public List<ProductResponseDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = products.stream().map(product->toDTO(product)).toList();
        return  productResponseDTOS;
    }

    public ProductResponseDTO getProductById(UUID id){
        Product product =  productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with " + id + " is not found"));
        return toDTO(product);
    }

    public List<ProductResponseDTO> addProduct(List<ProductRequestDTO> reqDTO){
        List<Product> newProducts = reqDTO.stream().map(productDTO -> toProduct(productDTO)).toList();

        newProducts.forEach(product -> productRepository.save(product));

        List<ProductResponseDTO> savedProduct = newProducts.stream().map(product -> toDTO(product)).toList();

        //a product create event is generated
        kafkaProducer.SendEvent(newProducts);

        return savedProduct;
    }

    public ProductResponseDTO updateProduct(UUID id , ProductRequestDTO reqDTO){
        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product with " + id + " is not found"));
        product.setName(reqDTO.getName());
        product.setDescription(reqDTO.getDescription());
        product.setPrice(reqDTO.getPrice());
        productRepository.save(product);
        return toDTO(product);
    }

    public void removeProductById(UUID id){
        productRepository.deleteById(id);
    }

    ProductResponseDTO toDTO(Product product){
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductId(product.getId().toString());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        return dto;
    }

    Product toProduct(ProductRequestDTO reqDTO){
        Product product = new Product();
        product.setName(reqDTO.getName());
        product.setDescription(reqDTO.getDescription());
        product.setPrice(reqDTO.getPrice());
        product.setQuantity(reqDTO.getQuantity());
        return product;
    }
}
