package com.example.shopping.service;

import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.model.CartEntity;
import com.example.shopping.model.OrderEntity;
import com.example.shopping.model.OrderLineEntity;
import com.example.shopping.repository.CartRepository;
import com.example.shopping.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class OrderService {

    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public List<OrderEntity> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        log.info("invoked OrderService.getAllOrders");
        return orders;
    }

    public Optional<OrderEntity> getOrderById(Integer id) {
        log.info("invoked OrderService.getOrderById for id: " + id);
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        log.info("orderEntity found: " + orderEntity.orElse(null));
        return orderEntity;
    }

    @Transactional
    public OrderEntity saveOrder(OrderEntity order) {
        log.info("invoked OrderService.saveOrder for order: " + order);
        order = orderRepository.save(order);
        log.info("orderEntity saved: " + order);
        return order;
    }

    @Transactional
    public boolean deleteOrder(Integer id) {
        log.info("invoked OrderService.deleteOrder for id: " + id);
        try {
            orderRepository.deleteById(id);
            log.info("userEntity deleted for id: " + id);
            return true;
        } catch (IllegalArgumentException e) {
            log.info("userEntity not found for id: " + id);
            return false;
        }
    }


    @Transactional
    public OrderEntity checkout(Integer cartId) {
        log.info("invoked OrderService.checkout for cartId: " + cartId);
        CartEntity cart = this.cartRepository.findById(cartId).orElseThrow(
          () -> new ResourceNotFoundException("Cart not found for cartId: " + cartId)
        );
        log.info("cart found: " + cart);
        OrderEntity order = buildOrderFromCart(cart);
        log.info("order built: " + order);
        order = this.orderRepository.save(order);
        log.info("order saved: " + order);
        return order;
    }

    private OrderEntity buildOrderFromCart(CartEntity cart) {
        OrderEntity order = OrderEntity.builder()
          .withOrderDate(LocalDateTime.now())
          .withUser(cart.getUser())
          .build();
        cart.getItems().forEach(item -> {
            System.out.println("item found: " + item);
            order.addLine(
              OrderLineEntity.builder()
                .withProduct(item.getProduct())
                .withQuantity(item.getQuantity())
                .withPrice(item.getProduct().getPrice())
                .withAmount(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .build()
            );
        });
        return order;
    }

}
