package ir.maktab.service;

import ir.maktab.dao.ClientDao;
import ir.maktab.exception.DuplicateException;
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
public class ClientService {
    private final ClientDao clientDao;

    public void add(Client client){
        Optional<Client> found = findByEmail(client.getEmail());
        if(found.isPresent())
            throw new DuplicateException("Duplicate client!");
        clientDao.save(client);
    }

    public Optional<Client> findByEmail(String email){
        Optional<Client> found = clientDao.findByEmail(email);
        return found;
    }

    public void update(Client client){
        Optional<Client> found = findByEmail(client.getEmail());
        if(found.isEmpty())
            throw new NotFoundException("client not found!");
        clientDao.update(client);
    }

    public List<Client> getAllClients(){
        List<Client> clients = clientDao.findAllClients();
        if(clients.isEmpty())
            throw new NotFoundException("there is no client!");
        return clients;
    }

     public void remove(Client client){
         Optional<Client> found = findByEmail(client.getEmail());
         if(found.isEmpty())
             throw new NotFoundException("client not found!");
         clientDao.delete(client);
     }
}
