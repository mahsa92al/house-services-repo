package ir.maktab.service;

import ir.maktab.model.dto.OrderDto;
import ir.maktab.model.entity.Order;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public interface OrderService {

    String addOrder(Order order);

    void removeOrder(Order order);

    void update(Order order);

    List<OrderDto> getAllOrders();
}
