package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Service;
import ir.maktab.model.entity.SubService;
import ir.maktab.model.enumaration.Role;
import ir.maktab.model.enumaration.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class AddExpertToSubServiceTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    SubServiceService subServiceService = context.getBean(SubServiceService.class);
    Service service;
    SubService subService;
    Expert expert;

    @Test
    void give_Service_And_SubService_And_Expert_when_AddExpertToSubService_Calls_Then_Service_NotFoundException_Return(){
        service = new Service();
        service.setTitle("house facilities");
        subService = new SubService();
        subService.setTitle("water");
        subService.setBasePrice(100d);
        subService.setDescription("using newest equipment");
        expert = new Expert();
        expert.setName("ladan");
        expert.setLastName("kiani");
        expert.setEmail("la.kiani@gmail.com");
        expert.setPassword("123");
        expert.setUserStatus(UserStatus.CONFIRMED);
        expert.setRole(Role.EXPERT);
        expert.setImageData(new byte[3000]);

        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subServiceService.addExpertToSubService(service, subService, expert));
        assertEquals("Service not found!", result.getMessage());
    }

    @Test
    void give_Service_And_SubService_And_Expert_when_AddExpertToSubService_Calls_Then_SubService_NotFoundException_Return(){
        service = new Service();
        service.setTitle("cleaning");
        subService = new SubService();
        subService.setTitle("floor washing");
        subService.setBasePrice(50d);
        subService.setDescription("extra drying");
        expert = new Expert();
        expert.setName("ladan");
        expert.setLastName("kiani");
        expert.setEmail("la.kiani@gmail.com");
        expert.setPassword("123");
        expert.setUserStatus(UserStatus.CONFIRMED);
        expert.setRole(Role.EXPERT);
        expert.setImageData(new byte[3000]);

        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subServiceService.addExpertToSubService(service, subService, expert));
        assertEquals("sub service not found!", result.getMessage());
    }
}
