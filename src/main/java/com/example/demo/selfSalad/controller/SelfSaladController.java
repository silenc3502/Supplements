package com.example.demo.selfSalad.controller;

import com.example.demo.selfSalad.controller.form.IngredientRegisterForm;
import com.example.demo.selfSalad.service.SelfSaladService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/selfsalad")
public class SelfSaladController {
    final private SelfSaladService selfSaladService;

    @PostMapping(value = "/register",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void ingredientRegister(
            @RequestPart(value = "imageFile") MultipartFile imageFile,
            @RequestPart(value = "ingredientInfo") IngredientRegisterForm ingredientRegisterForm){
        log.info("ingredientRegister(): " + ingredientRegisterForm);

        selfSaladService.register(ingredientRegisterForm.toIngredientRegisterRequest(imageFile));
    }

}
