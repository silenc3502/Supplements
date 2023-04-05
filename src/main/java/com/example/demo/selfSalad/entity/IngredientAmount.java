package com.example.demo.selfSalad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class IngredientAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column // 재료 최소 단위당 가격
    private Integer price;

    @Column // 재료 최소 단위당 칼로리
    private Integer calorie;

    @Column
    private Integer unit;

    @Column
    private Integer max;

    @Column
    private Integer min;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "amount_id")
    private Amount amount;

    public IngredientAmount(Ingredient ingredient, Amount amount,
                            Integer price, Integer calorie,
                            Integer unit, Integer max, Integer min) {

        this.ingredient = ingredient;
        this.amount = amount;

        this.price = price;
        this.calorie = calorie;
        this.unit = unit;
        this.max = max;
        this.min = min;
    }

    public Amount getCategory () {
        return amount;
    }
}
