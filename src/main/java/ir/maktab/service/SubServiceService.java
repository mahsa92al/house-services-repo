package ir.maktab.service;

import ir.maktab.dao.CategoryDao;
import ir.maktab.dao.SubServiceDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.exception.UserStatusException;
import ir.maktab.model.entity.Category;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.SubService;
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
public class SubServiceService {
    private final SubServiceDao subServiceDao;
    private final CategoryDao categoryDao;

    public void add(Category category, SubService subService){
        Optional<Category> foundService = categoryDao.findByTitle(category.getTitle());
        if(foundService.isEmpty())
            throw new NotFoundException("Category not found!");
        Optional<SubService> foundSubService = subServiceDao.findByTitle(subService.getTitle());
        if(foundSubService.isPresent())
            throw new DuplicateException("Duplicate sub service!");
        subServiceDao.save(subService);
    }

    public List<SubService> getAllSubServices(){
        List<SubService> subServices = subServiceDao.findAllSubServices();
        if(subServices.isEmpty())
            throw new NotFoundException("there is no sub service!");
        return subServices;
    }

    public void update(Category category, SubService subService){
        getServiceAndSubService(category, subService);
        List<SubService> subServices = category.getSubServices();
        subServices.add(subService);
        category.setSubServices(subServices);
        categoryDao.update(category);
    }

    public void remove(Category category, SubService subService){
        getServiceAndSubService(category, subService);
        List<SubService> subServices = category.getSubServices();
        subServices.remove(subService);
        category.setSubServices(subServices);
        categoryDao.update(category);
    }

    private void getServiceAndSubService(Category category, SubService subService) {
        Optional<Category> foundService = categoryDao.findByTitle(category.getTitle());
        if(foundService.isEmpty())
            throw new NotFoundException("Category not found!");
        Optional<SubService> found = subServiceDao.findByTitle(subService.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("sub service not found!");
    }

    public void addExpertToSubService(Category category, SubService subService, Expert expert){
        getServiceAndSubService(category, subService);
        if(!(expert.getUserStatus().equals(UserStatus.CONFIRMED)))
            throw new UserStatusException("The expert not confirmed!");
        List<Expert> experts = subService.getExperts();
        experts.add(expert);
        subService.setExperts(experts);
        subServiceDao.update(subService);
    }

    public void removeExpertFromSubService(Category category, SubService subService, Expert expert){
        getServiceAndSubService(category, subService);
        List<Expert> experts = subService.getExperts();
        experts.remove(expert);
        subService.setExperts(experts);
        subServiceDao.update(subService);
    }
}
