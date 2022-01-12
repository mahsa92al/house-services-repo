package ir.maktab.service;

import ir.maktab.model.dto.CategoryDto;
import ir.maktab.model.entity.Category;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public interface CategoryService {

    void add(Category category);

    List<CategoryDto> getAllCategories();

    void update(CategoryDto categoryDto);

    void remove(CategoryDto categoryDto);

}
