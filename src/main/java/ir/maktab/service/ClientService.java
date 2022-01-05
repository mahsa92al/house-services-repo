package ir.maktab.service;

import ir.maktab.dao.ClientDao;
import ir.maktab.exception.DuplicateClientException;
import ir.maktab.model.entity.Client;
import ir.maktab.model.enumaration.ClientStatus;
import ir.maktab.model.enumaration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            throw new DuplicateClientException("Duplicate client!");
        clientDao.save(client);
    }

    public Optional<Client> findByEmail(String email){
        Optional<Client> found = clientDao.findByEmail(email);
        return found;
    }
}
