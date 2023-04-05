package com.example.demo.selfSalad.repository;

import com.example.demo.selfSalad.entity.IngredientAmount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientAmountRepository extends JpaRepository<IngredientAmount, Long> {
}
