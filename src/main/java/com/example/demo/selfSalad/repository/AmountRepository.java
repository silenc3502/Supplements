package com.example.demo.selfSalad.repository;

import com.example.demo.selfSalad.entity.Amount;
import com.example.demo.selfSalad.entity.AmountType;
import com.example.demo.selfSalad.entity.IngredientAmount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface AmountRepository extends JpaRepository<Amount, Long> {
    Optional<Amount> findByAmountType(AmountType amountType);
}
