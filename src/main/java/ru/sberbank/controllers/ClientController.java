package ru.sberbank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.dto.ClientDTO;
import ru.sberbank.entities.Client;
import ru.sberbank.exceptions.ClientAlreadyExistsExceprion;
import ru.sberbank.exceptions.NotFoundException;
import ru.sberbank.repositories.RoleRepository;
import ru.sberbank.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    private final RoleRepository roleRepository;

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    private static final String USERNAME_ERR_MSG = "Пользователь с таким именем уже существует";

    @Autowired
    public ClientController(ClientService clientService, RoleRepository roleRepository){
        this.clientService = clientService;
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value = "/login-page" , method = RequestMethod.GET)
    public String loginPage() {
        return "login-page";
    }


    @RequestMapping(value = "/clients/create", method = RequestMethod.POST)
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO){
            if(clientService.isExistClient(clientDTO)){
                throw new ClientAlreadyExistsExceprion(USERNAME_ERR_MSG);
            }
            clientService.save(clientDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> showAllClients(){
        List<Client> clients = null;
        try {
            clients = clientService.getAllClientsList();
        } catch (NotFoundException ex) {
            log.info("Clients not found");
            ex.printStackTrace();
        }
        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> showClientById(@PathVariable(name = "id") Long id){
        Client client = null;
        try {
            client = clientService.getClientById(id);
            return client != null
                    ? new ResponseEntity<>(client, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NotFoundException ex) {
            log.info("Clinet by such id not found");
            ex.printStackTrace();
            throw new NotFoundException(ex.getMessage());
        }
    }


    @RequestMapping(value = "/clients/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
         clientService.delete(id);
         log.info("Client deleted");
         return new ResponseEntity<>(HttpStatus.OK);
    }



}
