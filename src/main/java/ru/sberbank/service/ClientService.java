package ru.sberbank.service;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sberbank.dto.ClientDTO;
import ru.sberbank.entities.Client;
import ru.sberbank.entities.Role;
import ru.sberbank.exceptions.NotFoundException;
import ru.sberbank.repositories.ClientRepository;
import ru.sberbank.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService  {
    private final ClientRepository clientRepository;

    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private static final String ROLE_PREFIX = "ROLE_";
    private static final String USER_ROLE_TITLE = ROLE_PREFIX + "USER";

    @Autowired
    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public Client save(ClientDTO clientDTO){
        Role clientRole = roleRepository.findOneByTitle(USER_ROLE_TITLE)
                .orElseThrow(RuntimeException::new);

        Client newClient = new Client();
        newClient.setLogin(clientDTO.getLogin());
        String pwhash = passwordEncoder.encode(clientDTO.getPassword());
        newClient.setPassword(pwhash);
        newClient.setFirstname(clientDTO.getFirstname());
        newClient.setLastname(clientDTO.getLastname());
        newClient.setRole(clientRole);
        return clientRepository.save(newClient);

    }

    public boolean isExistClient(ClientDTO clientDTO){
        return isExistClient(clientDTO.getLogin());
    }

    public boolean isExistClient(String login){
        boolean isExist = false;
        Client clientFromDB = clientRepository.findUserByLogin(login);
        if (clientFromDB != null) {
            isExist = true;
        } else {
            isExist = false;
        }
        return isExist;
    }

    public boolean checkPassword(Client client, String password) {
        String pwd = client.getPassword();
        return passwordEncoder.matches(password, pwd);
    }


    public Client getClientById(Long id){
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> getAllClientsList(){
        return clientRepository.findAll();
    }

    public void delete(Long id){
      clientRepository.deleteById(id);
    }


}
