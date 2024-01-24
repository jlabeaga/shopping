package com.example.shopping.rest.controller;

import com.example.shopping.model.OrderEntity;
import com.example.shopping.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@Log4j2
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        log.info("invoked OrderController.getAllOrders");
        return new ResponseEntity<>(this.orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Integer orderId) {
        Optional<OrderEntity> order = orderService.getOrderById(orderId);
        return order
                .map(orderEntity -> new ResponseEntity<>(orderEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<OrderEntity> checkout(@PathVariable Integer cartId) {
        log.info("invoked OrderController.checkout");
        return new ResponseEntity<>(this.orderService.checkout(cartId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderEntity> updateOrder(@RequestBody OrderEntity order) {
        log.info("invoked OrderController.saveOrder");
        return new ResponseEntity<>(this.orderService.saveOrder(order), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        boolean deleted = orderService.deleteOrder(orderId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
