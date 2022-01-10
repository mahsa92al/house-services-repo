package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.dto.CategoryDto;
import ir.maktab.model.entity.SubCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class UpdateCategoryTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    CategoryServiceImpl categoryServiceImpl = context.getBean(CategoryServiceImpl.class);
    SubCategory subCategory;
    CategoryDto categoryDto;

    @BeforeEach
    void init() {
        List<SubCategory> subCategories = new ArrayList<>();
        subCategory = new SubCategory();
        subCategory.setTitle("water");
        subCategory.setBasePrice(300d);
        subCategory.setDescription("Comes with newest equipment");
        subCategories.add(subCategory);
        categoryDto = CategoryDto.builder()
                .title("house facility")
                .subCategories(subCategories)
                .build();
    }

    @Test
    void give_Service_when_Update_Calls_Then_Exception_Return() {
        NotFoundException result = assertThrows(NotFoundException.class, () ->
                categoryServiceImpl.update(categoryDto));
        assertEquals("service not found!", result.getMessage());
    }
}
