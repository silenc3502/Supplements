package com.example.demo.ingredient.nonRepo;

import com.example.demo.selfSalad.controller.form.IngredientRegisterForm;
import com.example.demo.selfSalad.entity.*;
import com.example.demo.selfSalad.repository.*;
import com.example.demo.selfSalad.service.request.IngredientRegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("일반 게시판에 대한 테스트")
@SpringBootTest
@AutoConfigureMockMvc
public class NonRepoIngredientTest {

//    @Mock
//    private AmountRepository mockAmountRepository;
//    @Mock
//    private IngredientCategoryRepository mockCategoryRepository;
//    @Mock
//    private IngredientRepository mockIngredientRepository;
//    @Mock
//    private IngredientImageRepository mockIngredientImageRepository;
//
//    @InjectMocks
//    private SelfSaladServiceImplBackup mockSelfSaladService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AmountRepository amountRepository;

    @Autowired
    private IngredientAmountRepository ingredientAmountRepository;

    private MultipartFile imageFile;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

//    private void prepareFileInputTest () throws IOException {
//        final String pathToLoad = Paths.get("D:\\picture\\test.png").toString();
//        System.out.println("path: " + pathToLoad);
//        File file = new File(pathToLoad);
//        FileItem fileItem = new DiskFileItem(
//                "mainFile", Files.probeContentType(file.toPath()),
//                false, file.getName(), (int) file.length(), file.getParentFile());
//
//        try {
//            FileInputStream input = new FileInputStream(file);
//            OutputStream output = fileItem.getOutputStream();
//            IOUtils.copy(input, output);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//        imageFile = new CommonsMultipartFile(fileItem);
//    }

    @DisplayName("enum Test")
    @Test
    public void enum_변환_테스트 () {
        String receivedData = "MEAT";
        CategoryType categoryType = CategoryType.valueOf(receivedData);

        assertEquals(categoryType, CategoryType.MEAT);
    }

    // 테스트 할 때 이 파트는 실제 파일이 필요해서 의존성 문제가 존재함
//    @DisplayName("enum Test")
//    @Test
//    public void 식재료_등록_테스트 () throws IOException {
//        IngredientRegisterForm ingredientRegisterForm =
//                new IngredientRegisterForm("돼지고기",
//                        CategoryType.MEAT,
//                        10000, 50, 5, 1, 1,
//                        AmountType.COUNT);
//
//        // 실 파일 테스트라 문제가 좀 있음.
//        prepareFileInputTest();
//        System.out.println(imageFile.getOriginalFilename());
//
//        IngredientRegisterRequest ingredientRegisterRequest =
//                ingredientRegisterForm.toIngredientRegisterRequest(imageFile);
//
//        Ingredient ingredient = ingredientRegisterRequest.toIngredient();
//        System.out.println(ingredient);
//
//        ingredientRepository.save(ingredient);
//
//        final Category category =
//                categoryRepository.findByCategoryType(ingredientRegisterRequest.getCategoryType()).get();
//        final IngredientCategory ingredientCategory =
//                new IngredientCategory(ingredient, category);
//
//        ingredientCategoryRepository.save(ingredientCategory);
//
//        System.out.println(category);
//
//        final Amount amount =
//                amountRepository.findByAmountType(ingredientRegisterRequest.getAmountType()).get();
//        final IngredientAmount ingredientAmount =
//                new IngredientAmount(ingredient, amount,
//                        ingredientRegisterRequest.getPrice(),
//                        ingredientRegisterRequest.getCalorie(),
//                        ingredientRegisterRequest.getUnit(),
//                        ingredientRegisterRequest.getMax(),
//                        ingredientRegisterRequest.getMin());
//
//        ingredientAmountRepository.save(ingredientAmount);
//    }

    @Test
    public void whenFileUploaded_thenVerifyStatus() throws Exception {
        MockMultipartFile imageFile
                = new MockMultipartFile(
                "imageFile",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        ObjectMapper objectMapper = new ObjectMapper();

        String content = objectMapper.writeValueAsString(new IngredientRegisterForm("돼지고기",
                CategoryType.MEAT,
                10000, 50, 5, 1, 1,
                AmountType.COUNT));

        MockMultipartFile ingredientInfo
                = new MockMultipartFile(
                "ingredientInfo",
                "jsonData",
                MediaType.APPLICATION_JSON_VALUE,
                content.getBytes()
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/selfsalad/register")
                .file(imageFile)
                .file(ingredientInfo))
                .andExpect(status().isOk());
    }
}
