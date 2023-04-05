package com.example.demo.selfSalad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IngredientImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column
    private String randomFileName;


    public IngredientImage(String randomFileName) {
        this.randomFileName = randomFileName;
    }

    public static IngredientImage of (String randomFileName) {
        return new IngredientImage(randomFileName);
    }

    public void setIngredient (Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
