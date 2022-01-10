package ir.maktab.service;

import ir.maktab.dao.ClientDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.dto.ClientDto;
import ir.maktab.model.entity.Client;
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
public class ClientService {
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;

    public void add(Client client){
        Optional<Client> found = clientDao.findByEmail(client.getEmail());
        if(found.isPresent())
            throw new DuplicateException("Duplicate client!");
        clientDao.save(client);
    }

    public void update(Client client){
        Optional<Client> found = clientDao.findByEmail(client.getEmail());
        if(found.isEmpty())
            throw new NotFoundException("client not found!");
        clientDao.save(client);
    }

    public List<ClientDto> getAllClients(){
        List<Client> clients = clientDao.findAll();
        if(clients.isEmpty())
            throw new NotFoundException("there is no client!");
        return clients.stream().map(clientMapper::toClientDto).collect(Collectors.toList());
    }

     public void remove(Client client){
         Optional<Client> found = clientDao.findByEmail(client.getEmail());
         if(found.isEmpty())
             throw new NotFoundException("client not found!");
         clientDao.delete(client);
     }
}
