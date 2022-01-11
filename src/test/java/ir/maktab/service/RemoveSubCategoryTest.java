package ir.maktab.service;

import ir.maktab.config.SpringConfig;
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
public class RemoveSubCategoryTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    SubCategoryServiceImpl subCategoryServiceImpl = context.getBean(SubCategoryServiceImpl.class);
    Category service;
    SubCategory subCategory;

    @Test
    void give_Service_And_SubService_when_update_Calls_Then_Service_NotFoundException_Return(){
        service = new Category();
        service.setTitle("house facilities");
        subCategory = new SubCategory();
        subCategory.setTitle("water");
        subCategory.setBasePrice(100d);
        subCategory.setDescription("using newest equipment");

        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subCategoryServiceImpl.update(service, subCategory));
        assertEquals("Service not found!", result.getMessage());
    }

    @Test
    void give_Service_And_SubService_when_Update_Calls_Then_SubService_NotFoundException_Return(){
        service = new Category();
        service.setTitle("cleaning");
        subCategory = new SubCategory();
        subCategory.setTitle("floor washing");
        subCategory.setBasePrice(50d);
        subCategory.setDescription("extra drying");

        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subCategoryServiceImpl.update(service, subCategory));
        assertEquals("sub service not found!", result.getMessage());
    }
}
