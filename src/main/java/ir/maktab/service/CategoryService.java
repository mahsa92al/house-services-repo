package ir.maktab.service;

import ir.maktab.dao.CategoryDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao;

    public void add(Category category){
        Optional<Category> found = categoryDao.findByTitle(category.getTitle());
        if(found.isPresent())
            throw new DuplicateException("Duplicate category!");
        categoryDao.save(category);
    }

    public List<Category> getAllCategories(){
        List<Category> services = categoryDao.findAllCategories();
        if(services.isEmpty())
            throw new NotFoundException("there is no category!");
        return services;
    }

    public void update(Category category){
        Optional<Category> found = categoryDao.findByTitle(category.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("category not found!");
        categoryDao.update(category);
    }

    public void remove(Category category){
        Optional<Category> found = categoryDao.findByTitle(category.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("category not found!");
        categoryDao.delete(category);
    }
}
