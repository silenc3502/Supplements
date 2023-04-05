package com.example.demo.selfSalad.repository;

import com.example.demo.selfSalad.entity.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {
    //Optional<IngredientCategory> findByCategoryType(CategoryType categoryType);
}
