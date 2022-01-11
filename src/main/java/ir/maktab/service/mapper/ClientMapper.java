package ir.maktab.service.mapper;

import ir.maktab.model.dto.ClientDto;
import ir.maktab.model.entity.Client;

/**
 * @author Mahsa Alikhani m-58
 */
public class ClientMapper {
    private ClientDto clientDto;
    private Client client;

    public Client toClient(ClientDto clientDto){
        client = new Client();
        client.setName(clientDto.getName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setUserStatus(clientDto.getUserStatus());
        client.setCredit(clientDto.getCredit());
        return client;
    }

    public ClientDto toClientDto(Client client){
        clientDto = new ClientDto();
        clientDto.setName(client.getName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setUserStatus(client.getUserStatus());
        clientDto.setCredit(client.getCredit());
        return clientDto;
    }
}
