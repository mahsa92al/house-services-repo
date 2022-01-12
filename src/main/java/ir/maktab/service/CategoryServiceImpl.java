package ir.maktab.service;

import ir.maktab.dao.CategoryDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.dto.CategoryDto;
import ir.maktab.model.entity.Category;
import ir.maktab.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;

    @Override
    public void add(Category category){
        Optional<Category> found = categoryDao.findByTitle(category.getTitle());
        if(found.isPresent())
            throw new DuplicateException("Duplicate category!");
        categoryDao.save(category);
    }

    @Override
    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryDao.findAll();
        if(categories.isEmpty())
            throw new NotFoundException("there is no category!");
        return categories.stream().map(categoryMapper::toCategoryDto).collect(Collectors.toList());
    }

    @Override
    public void update(CategoryDto categoryDto){
        Category category = categoryMapper.toCategory(categoryDto);
        Optional<Category> found = categoryDao.findByTitle(category.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("category not found!");
        categoryDao.save(category);
    }

    @Override
    public void remove(CategoryDto categoryDto){
        Optional<Category> found = categoryDao.findByTitle(categoryDto.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("category not found!");
        Category category = categoryMapper.toCategory(categoryDto);
        categoryDao.delete(category);
    }
}
