package ir.maktab.service;

import ir.maktab.dao.ServiceDao;
import ir.maktab.dao.SubServiceDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.SubService;
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
}
