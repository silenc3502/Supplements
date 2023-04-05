package com.example.demo.selfSalad.service;

import com.example.demo.selfSalad.entity.Ingredient;
import com.example.demo.selfSalad.service.request.IngredientRegisterRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SelfSaladService {

    boolean register(IngredientRegisterRequest ingredientRegisterRequest);
}
