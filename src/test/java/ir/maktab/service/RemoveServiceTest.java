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
public class RemoveServiceTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ServiceService serviceService = context.getBean(ServiceService.class);
    Service service;

    @BeforeEach
    void init(){
        service = new Service();
        service.setTitle("house facility");
    }

    @Test
    void give_Service_when_Remove_Calls_Then_Exception_Return(){
        NotFoundException result = assertThrows(NotFoundException.class, ()->
                serviceService.update(service));
        assertEquals("service not found!", result.getMessage());
    }
}
