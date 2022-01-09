package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Category;
import ir.maktab.model.entity.SubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class UpdateCategoryTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    CategoryService categoryService = context.getBean(CategoryService.class);
    SubService subService;
    Category category;

    @BeforeEach
    void init(){
        subService = new SubService();
        subService.setTitle("water");
        subService.setBasePrice(300d);
        subService.setDescription("Comes with newest equipment");
        category = new Category();
        category.setTitle("house facility");
        category.getSubServices().add(subService);
    }

    @Test
    void give_Service_when_Update_Calls_Then_Exception_Return(){
        NotFoundException result = assertThrows(NotFoundException.class, ()->
                categoryService.update(category));
        assertEquals("service not found!", result.getMessage());
    }
}
