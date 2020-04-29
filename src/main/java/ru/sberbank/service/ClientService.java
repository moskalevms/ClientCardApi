package ru.sberbank.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sberbank.dto.ClientDTO;
import ru.sberbank.entities.Client;
import ru.sberbank.entities.Role;
import ru.sberbank.repositories.ClientRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService  {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).get();
    }

    public List<Client> getAllClientsList(){
        return clientRepository.findAll();
    }

    public Client save(ClientDTO clientDTO){
        Client newClient = new Client();
        newClient.setClient_id(clientDTO.getId());
        newClient.setLogin(clientDTO.getLogin());
        newClient.setPassword(clientDTO.getPassword());
        newClient.setFirstname(clientDTO.getFirstname());
        newClient.setLastname(clientDTO.getLastname());
        return clientRepository.save(newClient);
    }

    public void delete(Long id){
      clientRepository.deleteById(id);
    }


}
