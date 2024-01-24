package com.example.shopping.rest.controller;

import com.example.shopping.config.ShoppingConfig;
import com.example.shopping.config.ShoppingConfigBean;
import com.example.shopping.exception.ResourceNotFoundException;
import com.example.shopping.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/general")
@Log4j2
@Import({ShoppingConfig.class})
public class GeneralController {

    private ShoppingConfigBean shoppingConfigBean;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private StoreItemRepository storeItemRepository;
    private CartRepository cartRepository;

    private Map<String, JpaRepository> entities = null;

    public GeneralController(
            ShoppingConfigBean shoppingConfigBean,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            StoreItemRepository storeItemRepository,
            CartRepository cartRepository) {
        this.shoppingConfigBean = shoppingConfigBean;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.storeItemRepository = storeItemRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.entities = Map.of(
                "USER", this.userRepository,
                "CATEGORY", this.categoryRepository,
                "PRODUCT", this.productRepository,
                "STORE", this.storeItemRepository,
                "CART", this.cartRepository);
    }

    @GetMapping("/config")
    public ResponseEntity<ShoppingConfigBean> getConfig() {
        log.info("invoked GeneralController.getConfig: " + this.shoppingConfigBean);
        return new ResponseEntity<>(this.shoppingConfigBean, HttpStatus.OK);
    }

    @GetMapping("/{entity}")
    public ResponseEntity<List> getAll(@PathVariable String entity) {
        log.info("invoked GeneralController.getAll for entity: " + entity);
        if ("ERROR".equals(entity)) {
//            throw new RuntimeException("Forced exception");
            throw new ResourceNotFoundException("Forced exception");
        }
        return new ResponseEntity<>(this.entities.get(entity).findAll(), HttpStatus.OK);
    }
}
