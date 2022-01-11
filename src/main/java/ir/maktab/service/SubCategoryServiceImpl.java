package ir.maktab.service;

import ir.maktab.dao.CategoryDao;
import ir.maktab.dao.SubCategoryDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.exception.UserStatusException;
import ir.maktab.model.dto.CategoryDto;
import ir.maktab.model.dto.SubCategoryDto;
import ir.maktab.model.entity.Category;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.SubCategory;
import ir.maktab.model.enumaration.UserStatus;
import ir.maktab.service.mapper.CategoryMapper;
import ir.maktab.service.mapper.SubCategoryMapper;
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
public class SubCategoryServiceImpl implements SubCategoryService{
    private final SubCategoryDao subCategoryDao;
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;
    private final SubCategoryMapper subCategoryMapper;

    @Override
    public void add(CategoryDto categoryDto, SubCategoryDto subCategoryDto){
        Category category = categoryMapper.toCategory(categoryDto);
        Optional<Category> foundService = categoryDao.findByTitleIgnoreCase(category.getTitle());
        if(foundService.isEmpty())
            throw new NotFoundException("Category not found!");
        SubCategory subCategory = subCategoryMapper.toSubCategory(subCategoryDto);
        Optional<SubCategory> foundSubService = subCategoryDao.findByTitleIgnoreCase(subCategory.getTitle());
        if(foundSubService.isPresent())
            throw new DuplicateException("Duplicate sub category!");
        List<SubCategory> subCategories = category.getSubCategories();
        subCategories.add(subCategory);
        category.setSubCategories(subCategories);
        categoryDao.save(category);
    }

    @Override
    public List<SubCategoryDto> getAllSubCategories(){
        List<SubCategory> subCategories = subCategoryDao.findAll();
        if(subCategories.isEmpty())
            throw new NotFoundException("there is no sub category!");
        return subCategories.stream().map(subCategoryMapper::toSubCategoryDto).collect(Collectors.toList());
    }

    @Override
    public void update(Category category, SubCategory subCategory){
        checkExistenceOfCategoryAndSubCategory(category, subCategory);
        List<SubCategory> subCategories = category.getSubCategories();
        subCategories.add(subCategory);
        category.setSubCategories(subCategories);
        categoryDao.save(category);
    }

    @Override
    public void remove(Category category, SubCategory subCategory){
        checkExistenceOfCategoryAndSubCategory(category, subCategory);
        List<SubCategory> subCategories = category.getSubCategories();
        subCategories.remove(subCategory);
        category.setSubCategories(subCategories);
        categoryDao.save(category);
    }

    private void checkExistenceOfCategoryAndSubCategory(Category category, SubCategory subCategory) {
        Optional<Category> foundService = categoryDao.findByTitleIgnoreCase(category.getTitle());
        if(foundService.isEmpty())
            throw new NotFoundException("Category not found!");
        Optional<SubCategory> found = subCategoryDao.findByTitleIgnoreCase(subCategory.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("Sub category not found!");
    }

    @Override
    public void addExpertToSubCategory(Category category, SubCategory subCategory, Expert expert){
        checkExistenceOfCategoryAndSubCategory(category, subCategory);
        if(!(expert.getUserStatus().equals(UserStatus.CONFIRMED)))
            throw new UserStatusException("The expert not confirmed!");
        List<Expert> experts = subCategory.getExperts();
        experts.add(expert);
        subCategory.setExperts(experts);
        subCategoryDao.save(subCategory);
    }

    @Override
    public void removeExpertFromSubCategory(Category category, SubCategory subCategory, Expert expert){
        checkExistenceOfCategoryAndSubCategory(category, subCategory);
        List<Expert> experts = subCategory.getExperts();
        experts.remove(expert);
        subCategory.setExperts(experts);
        subCategoryDao.save(subCategory);
    }
}
