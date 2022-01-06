package ir.maktab.service;

import ir.maktab.dao.ServiceDao;
import ir.maktab.dao.SubServiceDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.exception.UserStatusException;
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
    private final ServiceService serviceService;
    private final ServiceDao serviceDao;

    public void add(ir.maktab.model.entity.Service service, SubService subService){
        Optional<ir.maktab.model.entity.Service> foundService = serviceService.findByTitle(service.getTitle());
        if(foundService.isEmpty())
            throw new NotFoundException("Service not found!");
        Optional<SubService> foundSubService = findSubServiceByTitle(subService.getTitle());
        if(foundSubService.isPresent())
            throw new DuplicateException("Duplicate sub service!");
        subServiceDao.save(subService);
    }

    private Optional<SubService> findSubServiceByTitle(String title) {
        Optional<SubService> found = subServiceDao.findByTitle(title);
        return found;
    }

    public List<SubService> getAllSubServices(){
        List<SubService> subServices = subServiceDao.findAllSubServices();
        if(subServices.isEmpty())
            throw new NotFoundException("there is no sub service!");
        return subServices;
    }

    public void update(ir.maktab.model.entity.Service service, SubService subService){
        getServiceAndSubService(service, subService);
        service.getSubServices().add(subService);
        serviceDao.update(service);
    }

    public void remove(ir.maktab.model.entity.Service service, SubService subService){
        getServiceAndSubService(service, subService);
        service.getSubServices().remove(subService);
        serviceDao.update(service);
    }

    private void getServiceAndSubService(ir.maktab.model.entity.Service service, SubService subService) {
        Optional<ir.maktab.model.entity.Service> foundService = serviceService.findByTitle(service.getTitle());
        if(foundService.isEmpty())
            throw new NotFoundException("Service not found!");
        Optional<SubService> found = findSubServiceByTitle(subService.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("sub service not found!");
    }

    public void addExpertToSubService(ir.maktab.model.entity.Service service, SubService subService, Expert expert){
        getServiceAndSubService(service, subService);
        if(!(expert.getUserStatus().equals(UserStatus.CONFIRMED)))
            throw new UserStatusException("The expert not confirmed!");
        subService.getExperts().add(expert);
        subServiceDao.update(subService);
    }

    public void removeExpertFromSubService(ir.maktab.model.entity.Service service, SubService subService, Expert expert){
        getServiceAndSubService(service, subService);
        subService.getExperts().remove(expert);
        subServiceDao.update(subService);
    }

    public void updateExpertSubService(ir.maktab.model.entity.Service service, SubService subService, Expert expert){
        getServiceAndSubService(service, subService);
        subService.getExperts().add(expert);
        subServiceDao.update(subService);
    }

}
