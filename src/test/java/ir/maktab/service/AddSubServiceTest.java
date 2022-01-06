package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.DuplicateException;
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
public class AddSubServiceTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    SubServiceService subServiceService = context.getBean(SubServiceService.class);
    Service service;
    SubService subService;

    @Test
    void give_Service_And_SubService_when_Add_Calls_Then_NotFoundException_Return(){
        service = new Service();
        service.setTitle("house facilities");
        subService = new SubService();
        subService.setTitle("water");
        subService.setBasePrice(100d);
        subService.setDescription("using newest equipment");

        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subServiceService.add(service, subService));
        assertEquals("Service not found!", result.getMessage());
    }

    @Test
    void give_Service_And_SubService_when_Add_Calls_Then_DuplicateException_Return(){
        service = new Service();
        service.setTitle("cleaning");
        subService = new SubService();
        subService.setTitle("spraying");
        subService.setBasePrice(50d);
        subService.setDescription("Comes with washing");

        DuplicateException result = assertThrows(DuplicateException.class, ()->
                subServiceService.add(service, subService));
        assertEquals("Duplicate sub service!", result.getMessage());
    }
}
