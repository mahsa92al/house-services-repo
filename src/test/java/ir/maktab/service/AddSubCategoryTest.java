package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.dto.CategoryDto;
import ir.maktab.model.dto.SubCategoryDto;
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
    SubCategoryServiceImpl subCategoryServiceImpl = context.getBean(SubCategoryServiceImpl.class);
    CategoryDto categoryDto;
    SubCategoryDto subCategoryDto;

    @Test
    void give_Service_And_SubService_when_Add_Calls_Then_NotFoundException_Return(){
        categoryDto = CategoryDto.builder().title("house facilities").build();
        subCategoryDto = SubCategoryDto.builder()
                        .title("water")
                                .basePrice(100d)
                                        .description("using newest equipment").build();

        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subCategoryServiceImpl.add(categoryDto, subCategoryDto));
        assertEquals("Category not found!", result.getMessage());
    }

    @Test
    void give_Service_And_SubService_when_Add_Calls_Then_DuplicateException_Return(){
        categoryDto = CategoryDto.builder().title("cleaning").build();
        subCategoryDto = SubCategoryDto.builder()
                .title("spraying")
                .basePrice(50d)
                .description("Comes with washing").build();

        DuplicateException result = assertThrows(DuplicateException.class, ()->
                subCategoryServiceImpl.add(categoryDto, subCategoryDto));
        assertEquals("Duplicate sub service!", result.getMessage());
    }
}
