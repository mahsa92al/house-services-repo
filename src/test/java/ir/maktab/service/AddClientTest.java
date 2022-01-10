package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.DuplicateException;
import ir.maktab.model.entity.Client;
import ir.maktab.model.enumaration.UserStatus;
import ir.maktab.model.enumaration.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */

public class AddClientTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ClientServiceImpl clientServiceImpl = context.getBean(ClientServiceImpl.class);
    Client client;
    @BeforeEach
    void init(){
        client = new Client();
        client.setName("mahsa");
        client.setLastName("alikhani");
        client.setEmail("mahsa.alikhani@gmail.com");
        client.setPassword("123");
        client.setUserStatus(UserStatus.NEW);
        client.setRole(Role.CLIENT);
    }

    @Test
    void give_Client_when_Add_Calls_Then_Exception_Return(){
        DuplicateException result = assertThrows(DuplicateException.class, ()->
                clientServiceImpl.add(client));
        assertEquals("Duplicate client!", result.getMessage());
    }
}
