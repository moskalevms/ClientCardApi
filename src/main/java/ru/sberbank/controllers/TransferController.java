package ru.sberbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.entities.ClientDTO;
import ru.sberbank.service.ClientService;

@RestController
@RequestMapping("/controller")
public class TransferController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ClientDTO createClient(@RequestBody ClientDTO client){
        return new ClientDTO();
    }

}
