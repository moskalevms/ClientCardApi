package ru.sberbank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.MoneyTransferService;

@RestController
@RequestMapping("/account")
public class TransferController {

    private MoneyTransferService moneyTransferService;

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    public void setMoneyTranferService(MoneyTransferService moneyTranferService){
        this.moneyTransferService = moneyTranferService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody Client client, Card cardFrom, Card cardTo){
        moneyTransferService.addCard(client, cardFrom, cardTo);
        log.info("Card added!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/upbalance", method = RequestMethod.PUT)
    public ResponseEntity<?> addMoneyToCard(@RequestBody Long cardId, Integer sum){
        moneyTransferService.upTheBalance(cardId, sum);
        log.info("Money added!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
    public ResponseEntity<?> makeTransfer(@RequestBody Card cardFrom, Card cardTo, Integer sum){
        moneyTransferService.transfer(cardFrom.getId(), cardTo.getId(), sum );
        log.info("Transfer ok!");
        return new ResponseEntity<>(HttpStatus.OK);
    }














    /*
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ClientDTO createClient(@RequestBody ClientDTO client){
        return new ClientDTO();
    }
     */


}
