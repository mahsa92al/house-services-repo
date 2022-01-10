package ir.maktab.service;

import ir.maktab.model.dto.ClientDto;
import ir.maktab.model.entity.Client;

import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public interface ClientService {

    public void add(Client client);

    public void update(Client client);

    public List<ClientDto> getAllClients();

    public void remove(Client client);
}
