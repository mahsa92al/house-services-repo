package ir.maktab.service;

import ir.maktab.model.dto.ClientDto;
import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Expert;
import ir.maktab.model.entity.Offer;
import ir.maktab.model.entity.Order;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public interface ClientService {

    void add(Client client);

    void update(Client client);

    List<ClientDto> getAllClients();

    void remove(Client client);

    List<Offer> getClientOrderOffersOrderByProposedPriceAndExpertRate(Order order);

    Expert chooseAnExpertForOrder(Order order, Expert expert);
}
