package ir.maktab.service;

import ir.maktab.dao.OrderDao;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.ClientOrder;
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

    public void addOrder(ClientOrder clientOrder){
        orderDao.save(clientOrder);
    }

    public void removeOrder(ClientOrder clientOrder){
        Optional<ClientOrder> foundOrder = orderDao.findOrderById(clientOrder);
        if(foundOrder.isEmpty())
            throw new NotFoundException("order not found!");
        orderDao.delete(clientOrder);
    }

    public void update(ClientOrder clientOrder){
        Optional<ClientOrder> foundOrder = orderDao.findOrderById(clientOrder);
        if(foundOrder.isEmpty())
            throw new NotFoundException("order not found!");
        orderDao.update(clientOrder);
    }

    public List<ClientOrder> getAllOrders(){
        List<ClientOrder> clientOrder = orderDao.findAllOrders();
        if(clientOrder.isEmpty())
            throw new NotFoundException("there is no order!");
        return clientOrder;
    }
}
