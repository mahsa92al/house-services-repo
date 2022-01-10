package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class GetExpertsTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ExpertServiceImpl expertServiceImpl = context.getBean(ExpertServiceImpl.class);

    @Test
    void when_GetAllExperts_Calls_Then_Exception_Return(){
        NotFoundException result = assertThrows(NotFoundException.class, ()->
                expertServiceImpl.getAllExperts());
        assertEquals("there is no expert!", result.getMessage());
    }
}
