package ru.sberbank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.dto.AccountDTO;
import ru.sberbank.dto.AddMoneyToCardDTO;
import ru.sberbank.dto.MoneyTransferDTO;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.MoneyTransferService;

import java.util.HashMap;

@RestController
@RequestMapping("/account")
public class TransferController {

    private MoneyTransferService moneyTransferService;

    private static final Logger log = LoggerFactory.getLogger(TransferController.class);

    @Autowired
    public void MoneyTranferService(MoneyTransferService moneyTransferService){
        this.moneyTransferService = moneyTransferService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO){
        moneyTransferService.addCard(accountDTO.getClientId(), accountDTO.getCardFromId(), accountDTO.getCardToId());
        log.info("Card added!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/upbalance/{cardId}/card/{sum}", method = RequestMethod.PUT)
    public ResponseEntity<?> addMoneyToCard(@RequestBody AddMoneyToCardDTO addMoneyDTO){
        moneyTransferService.upTheBalance(addMoneyDTO.getCardId(), addMoneyDTO.getSumOfUpping());
        log.info("Money added!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
    public ResponseEntity<?> makeTransfer(@RequestBody MoneyTransferDTO transferDTO){
        moneyTransferService.transfer(transferDTO.getCardFromId(), transferDTO.getCardToId(), transferDTO.getSum());
        log.info("Transfer ok!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
