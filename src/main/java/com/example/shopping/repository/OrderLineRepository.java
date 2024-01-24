package com.example.shopping.repository;

import com.example.shopping.model.OrderEntity;
import com.example.shopping.model.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Integer> {

}
