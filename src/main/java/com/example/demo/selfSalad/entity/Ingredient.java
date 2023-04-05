package com.example.demo.selfSalad.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private IngredientImage ingredientImage;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private Set<IngredientCategory> ingredientCategories = new HashSet<>();

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private Set<IngredientAmount> ingredientAmounts = new HashSet<>();

    public Ingredient(String name, IngredientImage ingredientImage) {

        this.name = name;
        this.ingredientImage = ingredientImage;

        ingredientImage.setIngredient(this);
    }

}
