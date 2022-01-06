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
public class GetSubServicesTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    SubServiceService subServiceService = context.getBean(SubServiceService.class);

    @Test
    void when_GetAllSubServices_Calls_Then_Exception_Return(){
        NotFoundException result = assertThrows(NotFoundException.class, ()->
                subServiceService.getAllSubServices());
        assertEquals("there is no sub service!", result.getMessage());
    }
}
