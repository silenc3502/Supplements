package com.example.demo.selfSalad.controller.form;

import com.example.demo.selfSalad.entity.*;
import com.example.demo.selfSalad.service.request.IngredientRegisterRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
public class IngredientRegisterForm {

    final private String name;

    @JsonProperty("category")
    final private CategoryType categoryType;

    final private Integer price;
    final private Integer calorie;
    final private Integer max;
    final private Integer min;
    final private Integer unit;

    @JsonProperty("measure")
    final private AmountType amountType;

    public IngredientRegisterRequest toIngredientRegisterRequest (MultipartFile imageFile) {
        UUID randomName = UUID.randomUUID();
        String fileRandomName = randomName + imageFile.getOriginalFilename();
        
        return new IngredientRegisterRequest(
                name, categoryType, price, calorie,
                max, min, unit, amountType, fileRandomName);
    }
}

