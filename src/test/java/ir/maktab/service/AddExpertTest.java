package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.ImageSizeException;
import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.enumaration.ClientStatus;
import ir.maktab.model.enumaration.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class AddExpertTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ExpertService expertService = context.getBean(ExpertService.class);

    Expert expert;

    @Test
    void give_Expert_when_Add_Calls_Then_Exception_Return(){
        expert = new Expert();
        expert.setName("mahsa");
        expert.setLastName("alikhani");
        expert.setEmail("mahsa.alikhani@gmail.com");
        expert.setPassword("123");
        expert.setClientStatus(ClientStatus.NEW);
        expert.setRole(Role.EXPERT);
        expert.setImageData(new byte[3000]);
        DuplicateException result = assertThrows(DuplicateException.class, ()->
                expertService.add(expert));
        assertEquals("Duplicate expert!", result.getMessage());
    }

    @Test
    void give_Expert_when_Add_Calls_Then_Image_Size_Exception_Return(){
        expert = new Expert();
        expert.setName("mahsa");
        expert.setLastName("alikhani");
        expert.setEmail("mahsa.alikhani2@gmail.com");
        expert.setPassword("123");
        expert.setClientStatus(ClientStatus.NEW);
        expert.setRole(Role.EXPERT);
        expert.setImageData(new byte[4000]);
        ImageSizeException result = assertThrows(ImageSizeException.class, ()->
                expertService.add(expert));
        assertEquals("Maximum image size should be 300 KB", result.getMessage());
    }
}
