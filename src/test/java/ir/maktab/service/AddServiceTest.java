package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.DuplicateException;
import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Service;
import ir.maktab.model.enumaration.Role;
import ir.maktab.model.enumaration.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class AddServiceTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ServiceService serviceService = context.getBean(ServiceService.class);
    Service service;
    @BeforeEach
    void init(){
        service = new Service();
        service.setTitle("cleaning");
    }

    @Test
    void give_Service_when_Add_Calls_Then_Exception_Return(){
        DuplicateException result = assertThrows(DuplicateException.class, ()->
                serviceService.add(service));
        assertEquals("Duplicate service!", result.getMessage());
    }
}
