package ru.yandex.yandexlavka.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.yandexlavka.managers.OrderManager;
import ru.yandex.yandexlavka.rest.Dto.CourierDto;
import ru.yandex.yandexlavka.rest.Dto.CreateOrderDto;
import ru.yandex.yandexlavka.rest.Dto.OrderDto;
import ru.yandex.yandexlavka.rest.RateLimiter;
import ru.yandex.yandexlavka.rest.requests.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.rest.requests.CreateOrderRequest;
import ru.yandex.yandexlavka.rest.responses.GetCouriersResponse;

import java.util.LinkedList;
import java.util.List;

@RestController
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderManager orderManager;

    @Autowired
    RateLimiter rateLimiter;
    @PostMapping(value = "/orders", consumes = "application/json", produces = "application/json")
    List<OrderDto> createOrders(@RequestBody CreateOrderRequest request) {
        rateLimiter.countConnection();
        logger.info("Create order request: " + request);
        List<OrderDto> response = request.getOrders().stream()
                    .map(orderManager::CreateOrder).toList();

        logger.info("Response sent: " + response);

        return response;
    }


    @GetMapping(value = "/orders/{order_id}", produces = "application/json")
    OrderDto getSingleOrder(@PathVariable("order_id") Long id) {
        rateLimiter.countConnection();
        logger.info("Request for order with id " + id);

        OrderDto response = orderManager.findOrder(id);

        if(response == null)
            throw new NullPointerException("");

        logger.info("Response sent: " + response);

        return response;
    }

    @GetMapping(value = "/orders", produces = "application/json")
    List<OrderDto> getOrders(
            @RequestParam(name="offset", required = false, defaultValue = "0") Long offset,
            @RequestParam(name="limit", required = false, defaultValue = "1") Long limit
    ) {
        rateLimiter.countConnection();
        if(offset == null || limit == null || offset < 0 || limit < 0)
            throw new IllegalArgumentException();

        logger.info("Request for orders: offset " + offset + " limit " + limit);

        List<OrderDto> response = orderManager.findOrders(Math.toIntExact(offset), Math.toIntExact(limit));

        logger.info("Response sent: " + response);

        return response;
    }

    @PostMapping(value = "/orders/complete", consumes = "application/json", produces = "application/json")
    List<OrderDto> completeOrder(@RequestBody CompleteOrderRequestDto request) {
        rateLimiter.countConnection();

        logger.info("Complete order request: " + request);

        List<OrderDto> response;

        response = request.getInfo().stream().map(orderManager::completeOrder).toList();

        logger.info("Response sent: " + response);

        return response;
    }
}
