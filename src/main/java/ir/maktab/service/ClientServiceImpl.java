package ir.maktab.service;

import ir.maktab.dao.ClientDao;
import ir.maktab.dao.OfferDao;
import ir.maktab.dao.OrderDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.dto.ClientDto;
import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Offer;
import ir.maktab.model.entity.Order;
import ir.maktab.model.enumaration.OrderStatus;
import ir.maktab.service.mapper.ClientMapper;
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
public class ClientServiceImpl implements ClientService{
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;
    private final OfferDao offerDao;
    private final OrderDao orderDao;

    @Override
    public void add(Client client){
        Optional<Client> found = clientDao.findByEmail(client.getEmail());
        if(found.isPresent())
            throw new DuplicateException("Duplicate client!");
        clientDao.save(client);
    }

    @Override
    public void update(Client client){
        Optional<Client> found = clientDao.findByEmail(client.getEmail());
        if(found.isEmpty())
            throw new NotFoundException("client not found!");
        clientDao.save(client);
    }

    @Override
    public List<ClientDto> getAllClients(){
        List<Client> clients = clientDao.findAll();
        if(clients.isEmpty())
            throw new NotFoundException("there is no client!");
        return clients.stream().map(clientMapper::toClientDto).collect(Collectors.toList());
    }

    @Override
     public void remove(Client client){
         Optional<Client> found = clientDao.findByEmail(client.getEmail());
         if(found.isEmpty())
             throw new NotFoundException("client not found!");
         clientDao.delete(client);
     }

    @Override
    public List<Offer> getClientOrderOffersOrderByProposedPriceAndExpertRate(Order order) {
        List<Offer> offers = offerDao.findOfferByOrder(order);
        if(offers.isEmpty())
            throw new NotFoundException("there is no offers!");
        return offers;
    }

    @Override
    public Expert chooseAnExpertForOrder(Order order, Expert expert) {
        order.setOrderStatus(OrderStatus.WAITING_FOR_THE_EXPERT_TO_ARRIVE_AT_LOCATION);
        order.setExpert(expert);
        Order updatedOrder = orderDao.save(order);
        return updatedOrder.getExpert();
    }
}
