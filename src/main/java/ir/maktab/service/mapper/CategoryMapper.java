package ir.maktab.service.mapper;

import ir.maktab.model.dto.CategoryDto;
import ir.maktab.model.entity.Category;

/**
 * @author Mahsa Alikhani m-58
 */
public class CategoryMapper {

    public Category toCategory(CategoryDto categoryDto){
        return Category.builder()
                .title(categoryDto.getTitle())
                .subCategories(categoryDto.getSubCategories())
                .build();
    }

    public CategoryDto toCategoryDto(Category category){
        return CategoryDto.builder()
                .title(category.getTitle())
                .subCategories(category.getSubCategories())
                .build();
    }
}
