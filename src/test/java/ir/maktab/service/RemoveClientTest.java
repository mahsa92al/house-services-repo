package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.exception.DuplicateClientException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Client;
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
public class RemoveClientTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ClientService clientService = context.getBean(ClientService.class);
    Client client;
    @BeforeEach
    void init(){
        client = new Client();
        client.setName("mahsa");
        client.setLastName("alikhani");
        client.setEmail("mahsa.alikhani@gmail.com");
        client.setPassword("123");
        client.setClientStatus(ClientStatus.NEW);
        client.setRole(Role.CLIENT);
    }

    @Test
    void give_Client_when_Add_Calls_Then_Exception_Return(){
        NotFoundException result = assertThrows(NotFoundException.class, ()->
                clientService.remove(client));
        assertEquals("client not found!", result.getMessage());
    }
}
