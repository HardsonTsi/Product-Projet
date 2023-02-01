package com.hardtech.orderservice.controllers;

import com.hardtech.orderservice.entities.Order;
import com.hardtech.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderControllers {

    private final OrderService orderService;

    @PostMapping
/*    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")*/
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@Valid @RequestBody Order order) {
        return orderService.placeOrder(order);
    }


    public CompletableFuture<String> fallbackMethod(Order order, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time !");
    }

}
