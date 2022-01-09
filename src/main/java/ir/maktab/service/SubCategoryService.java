package ir.maktab.service;

import ir.maktab.dao.CategoryDao;
import ir.maktab.dao.SubCategoryDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.exception.UserStatusException;
import ir.maktab.model.entity.Category;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.SubCategory;
import ir.maktab.model.enumaration.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final SubCategoryDao subCategoryDao;
    private final CategoryDao categoryDao;

    public void add(Category category, SubCategory subCategory){
        Optional<Category> foundService = categoryDao.findByTitle(category.getTitle());
        if(foundService.isEmpty())
            throw new NotFoundException("Category not found!");
        Optional<SubCategory> foundSubService = subCategoryDao.findByTitle(subCategory.getTitle());
        if(foundSubService.isPresent())
            throw new DuplicateException("Duplicate sub service!");
        subCategoryDao.save(subCategory);
    }

    public List<SubCategory> getAllSubCategories(){
        List<SubCategory> subCategories = subCategoryDao.findAllSubCategories();
        if(subCategories.isEmpty())
            throw new NotFoundException("there is no sub category!");
        return subCategories;
    }

    public void update(Category category, SubCategory subCategory){
        getCategoryAndSubCategory(category, subCategory);
        List<SubCategory> subCategories = category.getSubCategories();
        subCategories.add(subCategory);
        category.setSubCategories(subCategories);
        categoryDao.update(category);
    }

    public void remove(Category category, SubCategory subCategory){
        getCategoryAndSubCategory(category, subCategory);
        List<SubCategory> subCategories = category.getSubCategories();
        subCategories.remove(subCategory);
        category.setSubCategories(subCategories);
        categoryDao.update(category);
    }

    private void getCategoryAndSubCategory(Category category, SubCategory subCategory) {
        Optional<Category> foundService = categoryDao.findByTitle(category.getTitle());
        if(foundService.isEmpty())
            throw new NotFoundException("Category not found!");
        Optional<SubCategory> found = subCategoryDao.findByTitle(subCategory.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("sub category not found!");
    }

    public void addExpertToSubCategory(Category category, SubCategory subCategory, Expert expert){
        getCategoryAndSubCategory(category, subCategory);
        if(!(expert.getUserStatus().equals(UserStatus.CONFIRMED)))
            throw new UserStatusException("The expert not confirmed!");
        List<Expert> experts = subCategory.getExperts();
        experts.add(expert);
        subCategory.setExperts(experts);
        subCategoryDao.update(subCategory);
    }

    public void removeExpertFromSubCategory(Category category, SubCategory subCategory, Expert expert){
        getCategoryAndSubCategory(category, subCategory);
        List<Expert> experts = subCategory.getExperts();
        experts.remove(expert);
        subCategory.setExperts(experts);
        subCategoryDao.update(subCategory);
    }
}
