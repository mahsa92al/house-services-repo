package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.DuplicateException;
import ir.maktab.model.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class AddCategoryTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    CategoryService categoryService = context.getBean(CategoryService.class);
    Category category;
    @BeforeEach
    void init(){
        category = new Category();
        category.setTitle("cleaning");
    }

    @Test
    void give_Service_when_Add_Calls_Then_Exception_Return(){
        DuplicateException result = assertThrows(DuplicateException.class, ()->
                categoryService.add(category));
        assertEquals("Duplicate service!", result.getMessage());
    }
}
