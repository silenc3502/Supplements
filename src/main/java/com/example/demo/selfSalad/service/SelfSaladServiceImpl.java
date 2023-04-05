package com.example.demo.selfSalad.service;

import com.example.demo.selfSalad.entity.*;
import com.example.demo.selfSalad.repository.*;
import com.example.demo.selfSalad.service.request.IngredientRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SelfSaladServiceImpl implements SelfSaladService {

    final private CategoryRepository categoryRepository;
    final private IngredientRepository ingredientRepository;
    final private AmountRepository amountRepository;
    final private IngredientAmountRepository ingredientAmountRepository;
    final private IngredientCategoryRepository ingredientCategoryRepository;

    private Ingredient registerIngredient (IngredientRegisterRequest request) {
        final Ingredient ingredient = request.toIngredient();
        ingredientRepository.save(ingredient);

        return ingredient;
    }

    private void registerIngredientAmount (
            Ingredient ingredient, IngredientRegisterRequest request) {

        final Amount amount =
                amountRepository.findByAmountType(request.getAmountType()).get();

        final IngredientAmount ingredientAmount =
                new IngredientAmount(ingredient, amount,
                        request.getPrice(),
                        request.getCalorie(),
                        request.getUnit(),
                        request.getMax(),
                        request.getMin());

        ingredientAmountRepository.save(ingredientAmount);
    }

    private void registerIngredientCategory (
            Ingredient ingredient, IngredientRegisterRequest request) {

        final Category category =
                categoryRepository.findByCategoryType(request.getCategoryType()).get();

        final IngredientCategory ingredientCategory =
                new IngredientCategory(ingredient, category);

        ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public boolean register(IngredientRegisterRequest ingredientRegisterRequest) {
        // 1. 재료 entity 및 image 생성
        Ingredient ingredient = registerIngredient(ingredientRegisterRequest);

        // 2. 수량 entity 생성
        registerIngredientAmount(ingredient, ingredientRegisterRequest);

        // 3. 카테고리 처리
        registerIngredientCategory(ingredient, ingredientRegisterRequest);

        return true;
    }
}
