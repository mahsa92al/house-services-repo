package ir.maktab.service;

import ir.maktab.dao.OrderDao;
import ir.maktab.exception.NotFoundException;
import ir.maktab.exception.ProposedPriceException;
import ir.maktab.model.dto.OrderDto;
import ir.maktab.model.entity.Order;
import ir.maktab.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;

    @Override
    public String addOrder(Order order){
        if(order.getProposedPrice() < order.getSubCategory().getBasePrice())
            throw new ProposedPriceException("the proposed price is less than base price!");
        Order registeredOrder =  orderDao.save(order);
        return registeredOrder.getSubCategory().getTitle();
    }

    @Override
    public void removeOrder(Order order){
        Optional<Order> foundOrder = orderDao.findById(order.getId());
        if(foundOrder.isEmpty())
            throw new NotFoundException("order not found!");
        orderDao.delete(order);
    }

    @Override
    public void update(Order order){
        Optional<Order> foundOrder = orderDao.findById(order.getId());
        if(foundOrder.isEmpty())
            throw new NotFoundException("order not found!");
        orderDao.save(order);
    }

    @Override
    public List<OrderDto> getAllOrders(){
        List<Order> orders = orderDao.findAll();
        if(orders.isEmpty())
            throw new NotFoundException("there is no order!");
        return orders.stream().map(orderMapper::toOrderDto).collect(Collectors.toList());
    }
}
