package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Expert;
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
public class RemoveExpertTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ExpertServiceImpl expertServiceImpl = context.getBean(ExpertServiceImpl.class);
    Expert expert;
    @BeforeEach
    void init(){
        expert = new Expert();
        expert.setName("mahsa");
        expert.setLastName("alikhani");
        expert.setEmail("mahsa.alikhani@gmail.com");
        expert.setPassword("123");
        expert.setUserStatus(UserStatus.NEW);
        expert.setRole(Role.EXPERT);
        expert.setImageData(new byte[3000]);
    }

    @Test
    void give_Expert_when_Remove_Calls_Then_Exception_Return(){
        NotFoundException result = assertThrows(NotFoundException.class, ()->
                expertServiceImpl.remove(expert));
        assertEquals("expert not found!", result.getMessage());
    }
}
