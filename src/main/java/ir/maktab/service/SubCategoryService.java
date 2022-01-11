package ir.maktab.service;

import ir.maktab.model.dto.CategoryDto;
import ir.maktab.model.dto.SubCategoryDto;
import ir.maktab.model.entity.Category;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.SubCategory;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public interface SubCategoryService {

    void add(CategoryDto categoryDto, SubCategoryDto subCategoryDto);

    List<SubCategoryDto> getAllSubCategories();

    void update(Category category, SubCategory subCategory);

    void remove(Category category, SubCategory subCategory);

    void addExpertToSubCategory(Category category, SubCategory subCategory, Expert expert);

    public void removeExpertFromSubCategory(Category category, SubCategory subCategory, Expert expert);
}
