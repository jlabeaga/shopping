package com.example.shopping.repository;

import com.example.shopping.model.CartEntity;
import com.example.shopping.model.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {

}
