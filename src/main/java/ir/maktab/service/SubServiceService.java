package ir.maktab.service;

import ir.maktab.dao.ExpertDao;
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

    public void update(ir.maktab.model.entity.Service service, SubService subService){
        getServiceAndSubService(service, subService);
        List<SubService> subServices = service.getSubServices();
        subServices.add(subService);
        service.setSubServices(subServices);
        serviceDao.update(service);
    }

    public void remove(ir.maktab.model.entity.Service service, SubService subService){
        getServiceAndSubService(service, subService);
        List<SubService> subServices = service.getSubServices();
        subServices.remove(subService);
        service.setSubServices(subServices);
        serviceDao.update(service);
    }

    private void getServiceAndSubService(ir.maktab.model.entity.Service service, SubService subService) {
        Optional<ir.maktab.model.entity.Service> foundService = serviceService.findByTitle(service.getTitle());
        if(foundService.isEmpty())
            throw new NotFoundException("Service not found!");
        Optional<SubService> found = subServiceDao.findByTitle(subService.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("sub service not found!");
    }

    public void addExpertToSubService(ir.maktab.model.entity.Service service, SubService subService, Expert expert){
        getServiceAndSubService(service, subService);
        if(!(expert.getUserStatus().equals(UserStatus.CONFIRMED)))
            throw new UserStatusException("The expert not confirmed!");
        List<Expert> experts = subService.getExperts();
        experts.add(expert);
        subService.setExperts(experts);
        subServiceDao.update(subService);
    }

    public void removeExpertFromSubService(ir.maktab.model.entity.Service service, SubService subService, Expert expert){
        getServiceAndSubService(service, subService);
        List<Expert> experts = subService.getExperts();
        experts.remove(expert);
        subService.setExperts(experts);
        subServiceDao.update(subService);
    }
}
