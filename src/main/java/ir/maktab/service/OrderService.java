package ir.maktab.service;

import ir.maktab.dao.OrderDao;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;
    private final ClientService clientService;

    public void addOrder(Order order){
        orderDao.save(order);
    }

    public void removeOrder(Order order){
        Optional<Order> foundOrder = findOrderById(order);
        if(foundOrder.isEmpty())
            throw new NotFoundException("order not found!");
        orderDao.delete(order);
    }

    private Optional<Order> findOrderById(Order order) {
        Optional<Order> foundOrder = orderDao.findOrderById(order);
        return foundOrder;
    }

    public void update(Order order){
        Optional<Order> foundOrder = findOrderById(order);
        if(foundOrder.isEmpty())
            throw new NotFoundException("order not found!");
        orderDao.update(order);
    }

    public List<Order> getAllOrders(){
        List<Order> orders = orderDao.findAllOrders();
        if(orders.isEmpty())
            throw new NotFoundException("there is no order!");
        return orders;
    }
}
