package com.ecommerce.productservice.kafka;

import com.ecommerce.productservice.model.Product;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import product.events.ProductEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import product.events.ProductEvents;

import java.util.List;

@Service
public class KafkaProducer {
    private final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String,byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String ,byte[]> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void SendEvent(List<Product> productList){
        List<ProductEvent> products = productList.stream().map(p -> {
            ProductEvent productEvent = ProductEvent.newBuilder()
                    .setProductId(String.valueOf(p.getId()))
                    .setQuantity(String.valueOf(p.getQuantity()))
                    .build();
            return productEvent;
        }).toList();

        ProductEvents event = ProductEvents.newBuilder().addAllProduct(products).build();
        try {
            kafkaTemplate.send("product" , event.toByteArray());
            log.info("Product Created Event Generated" + event);
        }
        catch (Exception e){
            log.error("Error sending product created event " + event + e);
        }
    }
}
