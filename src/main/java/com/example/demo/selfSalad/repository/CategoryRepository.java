package com.example.demo.selfSalad.repository;

import com.example.demo.selfSalad.entity.Category;
import com.example.demo.selfSalad.entity.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryType(CategoryType type);
}
