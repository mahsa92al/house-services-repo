package ir.maktab.service;

import ir.maktab.dao.OrderDao;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.dto.OrderDto;
import ir.maktab.model.entity.Order;
import ir.maktab.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;

    public void addOrder(Order order){
        orderDao.save(order);
    }

    public void removeOrder(Order order){
        Optional<Order> foundOrder = orderDao.findById(order);
        if(foundOrder.isEmpty())
            throw new NotFoundException("order not found!");
        orderDao.delete(order);
    }

    public void update(Order order){
        Optional<Order> foundOrder = orderDao.findById(order);
        if(foundOrder.isEmpty())
            throw new NotFoundException("order not found!");
        orderDao.save(order);
    }

    public List<OrderDto> getAllOrders(){
        List<Order> orders = orderDao.findAll();
        if(orders.isEmpty())
            throw new NotFoundException("there is no order!");
        return orders.stream().map(orderMapper::toOrderDto).collect(Collectors.toList());
    }
}
