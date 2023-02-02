package com.hardtech.orderservice.services;

import com.hardtech.orderservice.dto.InventoryResponse;
import com.hardtech.orderservice.entities.Order;
import com.hardtech.orderservice.entities.OrderLineItem;
import com.hardtech.orderservice.event.OrderPlacedEvent;
import com.hardtech.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(Order order) {
        order.setOrderNumbers(UUID.randomUUID().toString());

        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItem::getSkuCode).toList();

        log.info("Calling inventory service");

        //call Inventory Service, and place order if product is in stock
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumbers()));
            orderRepository.save(order);
            log.info("Order is saved");
            return "Order placed Successfuly";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

    }

}
