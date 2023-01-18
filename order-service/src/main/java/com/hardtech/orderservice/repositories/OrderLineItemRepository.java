package com.hardtech.orderservice.repositories;

import com.hardtech.orderservice.entities.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
}