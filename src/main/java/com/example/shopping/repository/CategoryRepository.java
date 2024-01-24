package com.example.shopping.repository;

import com.example.shopping.model.CategoryEntity;
import com.example.shopping.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
