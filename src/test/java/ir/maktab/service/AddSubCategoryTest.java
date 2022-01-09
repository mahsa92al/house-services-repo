package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Category;
import ir.maktab.model.entity.SubCategory;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class AddSubCategoryTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    SubCategoryService subCategoryService = context.getBean(SubCategoryService.class);
    Category service;
    SubCategory subCategory;

    @Test
    void give_Service_And_SubService_when_Add_Calls_Then_NotFoundException_Return(){
        service = new Category();
        service.setTitle("house facilities");
        subCategory = new SubCategory();
        subCategory.setTitle("water");
        subCategory.setBasePrice(100d);
        subCategory.setDescription("using newest equipment");

        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subCategoryService.add(service, subCategory));
        assertEquals("Service not found!", result.getMessage());
    }

    @Test
    void give_Service_And_SubService_when_Add_Calls_Then_DuplicateException_Return(){
        service = new Category();
        service.setTitle("cleaning");
        subCategory = new SubCategory();
        subCategory.setTitle("spraying");
        subCategory.setBasePrice(50d);
        subCategory.setDescription("Comes with washing");

        DuplicateException result = assertThrows(DuplicateException.class, ()->
                subCategoryService.add(service, subCategory));
        assertEquals("Duplicate sub service!", result.getMessage());
    }
}
