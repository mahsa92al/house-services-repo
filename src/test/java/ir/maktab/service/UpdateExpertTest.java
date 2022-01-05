package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.enumaration.UserStatus;
import ir.maktab.model.enumaration.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mahsa Alikhani m-58
 */
public class UpdateExpertTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ExpertService expertService = context.getBean(ExpertService.class);

    Expert expert;
    @BeforeEach
    void init(){
        expert.setName("mahsa");
        expert.setLastName("alikhani");
        expert.setEmail("mahsa.alikhani@gmail.com");
        expert.setPassword("456");
        expert.setUserStatus(UserStatus.NEW);
        expert.setRole(Role.EXPERT);
        //expert.setImageData(new byte[3000]);
    }

    @Test
    void give_Expert_when_Update_Calls_Then_Password_Update(){
        expertService.update(expert);
        assertEquals("456", expert.getPassword());
    }
}
