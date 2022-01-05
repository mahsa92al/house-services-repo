package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Service;
import ir.maktab.model.entity.SubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class UpdateServiceTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ServiceService serviceService = context.getBean(ServiceService.class);
    SubService subService;
    Service service;

    @BeforeEach
    void init(){
        subService = new SubService();
        subService.setTitle("water");
        subService.setBasePrice(300d);
        subService.setDescription("Comes with newest equipment");
        service = new Service();
        service.setTitle("house facility");
        service.getSubServices().add(subService);
    }

    @Test
    void give_Expert_when_Remove_Calls_Then_Exception_Return(){
        NotFoundException result = assertThrows(NotFoundException.class, ()->
                serviceService.update(service));
        assertEquals("service not found!", result.getMessage());
    }
}
