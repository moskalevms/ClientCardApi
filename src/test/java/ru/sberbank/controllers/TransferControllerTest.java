package ru.sberbank.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.util.Assert;
import ru.sberbank.dto.AccountDTO;
import ru.sberbank.dto.AddMoneyToCardDTO;
import ru.sberbank.dto.MoneyTransferDTO;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.CardService;
import ru.sberbank.service.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferControllerTest {


    @Autowired
    private TransferController transferController;

    @Test
    public void createAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCardFromId(1L);
        accountDTO.setCardToId(2L);
        accountDTO.setClientId(1L);
        ResponseEntity<?> response = transferController.createAccount(accountDTO);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.CREATED == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void addMoneyToCard() {
        long cardId = 1L;
        int sumOfUpping = 100;
        AddMoneyToCardDTO addMoneyDTO = new AddMoneyToCardDTO();
        addMoneyDTO.setCardId(cardId);
        addMoneyDTO.setSumOfUpping(sumOfUpping);
        ResponseEntity<?> response = transferController.addMoneyToCard(addMoneyDTO);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void makeTransfer() {
        long cardFrom = 1L;
        long cardTo = 2L;
        MoneyTransferDTO transferDTO = new MoneyTransferDTO();
        transferDTO.setCardFromId(cardFrom);
        transferDTO.setCardToId(cardTo);
        transferDTO.setSum(100);

        ResponseEntity<?> response = transferController.makeTransfer(transferDTO);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

}