package com.hardtech.orderservice.services;

import com.hardtech.orderservice.entities.Order;
import com.hardtech.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public String placeOrder(Order order) {
        order.setOrderNumbers(UUID.randomUUID().toString());
        orderRepository.save(order);
        log.info("Order is saved");
        return "Order placed Successfuly";
    }

}
