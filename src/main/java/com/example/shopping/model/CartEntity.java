package com.example.shopping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cart_date")
    private LocalDateTime cartDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CartItemEntity> items;

    public void addItem(CartItemEntity item) {
        if (items == null) { items = new ArrayList<>(); }
        this.items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItemEntity item) {
        if (items != null) { this.items.remove(item); }
        item.setCart(null);
    }
}
