package com.example.demo.selfSalad.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "category_uq_category_name",
                columnNames = {"categoryType"})
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType categoryType;

    public Category (CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryType=" + categoryType +
                '}';
    }
}
