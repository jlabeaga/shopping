package com.example.shopping.repository;

import com.example.shopping.model.CartItemEntity;
import com.example.shopping.model.StoreItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItemEntity, Integer> {

}
