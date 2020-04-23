package ru.sberbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sberbank.entities.Client;
import ru.sberbank.repositories.ClientRepository;
import ru.sberbank.repositories.CardRepository;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).get();
    }

    public List<Client> getAllClientsList(){
        return clientRepository.findAll();
    }

    public Client save(Client client){
        Client cli = new Client();
        cli.setClient_id(client.getClient_id());
        cli.setLogin(client.getLogin());
        cli.setPassword(client.getPassword());
        cli.setFirstname(client.getFirstname());
        cli.setLastname(client.getLastname());
        return clientRepository.save(cli);
    }

    public void delete(Long id){
      clientRepository.deleteById(id);
    }








}
