package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Category;
import ir.maktab.model.entity.SubService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class UpdateSubServiceTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    SubServiceService subServiceService = context.getBean(SubServiceService.class);
    Category service;
    SubService subService;

    @Test
    void give_Service_And_SubService_when_update_Calls_Then_Service_NotFoundException_Return(){
        service = new Category();
        service.setTitle("house facilities");
        subService = new SubService();
        subService.setTitle("water");
        subService.setBasePrice(100d);
        subService.setDescription("using newest equipment");

        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subServiceService.update(service, subService));
        assertEquals("Service not found!", result.getMessage());
    }

    @Test
    void give_Service_And_SubService_when_Update_Calls_Then_SubService_NotFoundException_Return(){
        service = new Category();
        service.setTitle("cleaning");
        subService = new SubService();
        subService.setTitle("floor washing");
        subService.setBasePrice(50d);
        subService.setDescription("extra drying");

        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subServiceService.update(service, subService));
        assertEquals("sub service not found!", result.getMessage());
    }
}
