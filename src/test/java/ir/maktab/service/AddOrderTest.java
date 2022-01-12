package ir.maktab.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.dao.ClientDao;
import ir.maktab.dao.SubCategoryDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.ProposedPriceException;
import ir.maktab.model.dto.SubCategoryDto;
import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Order;
import ir.maktab.model.entity.SubCategory;
import ir.maktab.model.enumaration.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Mahsa Alikhani m-58
 */
public class AddOrderTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    OrderServiceImpl orderService = context.getBean(OrderServiceImpl.class);
    SubCategoryServiceImpl subCategoryService = context.getBean(SubCategoryServiceImpl.class);
    SubCategoryDao subCategoryDao = context.getBean(SubCategoryDao.class);
    ClientDao clientDao = context.getBean(ClientDao.class);

    Order order;
    Client client;

    @BeforeEach
    void init(){
        Optional<Client> foundClient = clientDao.findByEmail("mahsa.alikhani@gmail.com");
        client = foundClient.get();
    }

    @Test
    void give_Order_When_AddOrder_calls_Then_OrderTitle_Return() throws ParseException {
        List<SubCategoryDto> subCategories = subCategoryService.getAllSubCategories();
        System.out.println(subCategories);
        Optional<SubCategory> subCategory = subCategoryDao.findByTitle("painting");
        order = Order.builder()
                .subCategory(subCategory.get())
                .proposedDateToDo(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("1400-10-20 15:30"))
                .address("tehran")
                .client(client)
                .description("four room")
                .proposedPrice(700d)
                .orderStatus(OrderStatus.WAITING_FOR_EXPERT_OFFER)
                .build();
        String registeredOrder = orderService.addOrder(order);
        assertEquals("painting", registeredOrder);
    }

    @Test
    void give_Order_When_AddOrder_calls_Then_Exception_Return() throws ParseException {
        List<SubCategoryDto> subCategories = subCategoryService.getAllSubCategories();
        System.out.println(subCategories);
        Optional<SubCategory> subCategory = subCategoryDao.findByTitle("painting");
        order = Order.builder()
                .subCategory(subCategory.get())
                .proposedDateToDo(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("1400-10-20 15:30"))
                .address("tehran")
                .client(client)
                .description("four room")
                .proposedPrice(500d)
                .orderStatus(OrderStatus.WAITING_FOR_EXPERT_OFFER)
                .build();
        ProposedPriceException result = assertThrows(ProposedPriceException.class, ()->
                orderService.addOrder(order));
        assertEquals("the proposed price is less than base price!", result.getMessage());
    }
}
