package ru.sberbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.entities.Client;
import ru.sberbank.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {


    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService){
        this.clientService = clientService;
    }

    @RequestMapping(value = "/clients/create", method = RequestMethod.POST)
    public ResponseEntity<?> createClient(@RequestBody Client client){
        clientService.save(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> showAllClients(){
        List<Client> clients = clientService.getAllClientsList();
        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> showClientById(@PathVariable(name = "id") Long id){
        Client client = clientService.getClientById(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/clients/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
         clientService.delete(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }



}
