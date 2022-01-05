package ir.maktab.service;

import ir.maktab.dao.ServiceDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceDao serviceDao;

    public void add(ir.maktab.model.entity.Service service){
        Optional<ir.maktab.model.entity.Service> found = findByTitle(service.getTitle());
        if(found.isPresent())
            throw new DuplicateException("Duplicate service!");
        serviceDao.save(service);
    }

    private Optional<ir.maktab.model.entity.Service> findByTitle(String title) {
        Optional<ir.maktab.model.entity.Service> found = serviceDao.findByTitle(title);
        return found;
    }

    public List<ir.maktab.model.entity.Service> getAllServices(){
        List<ir.maktab.model.entity.Service> services = serviceDao.findAllServices();
        if(services.isEmpty())
            throw new NotFoundException("there is no service!");
        return services;
    }

    public void update(ir.maktab.model.entity.Service service){
        Optional<ir.maktab.model.entity.Service> found = findByTitle(service.getTitle());
        if(found.isEmpty())
            throw new NotFoundException("service not found!");
        serviceDao.update(service);
    }
}
