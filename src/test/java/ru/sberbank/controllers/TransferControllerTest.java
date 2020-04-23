package ru.sberbank.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.util.Assert;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.CardService;
import ru.sberbank.service.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferControllerTest {

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransferController transferController;

    @Test
    public void createAccount() {
        Card cardFrom = cardService.getCardById(1L);
        Card cardTo = cardService.getCardById(2L);
        Client testClient = clientService.getClientById(1L);

        ResponseEntity<?> response = transferController.createAccount(testClient, cardFrom, cardTo);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.CREATED == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void addMoneyToCard() {
        Card testCard= cardService.getCardById(1L);
        ResponseEntity<?> response = transferController.addMoneyToCard(testCard.getId(), 100);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void makeTransfer() {
        Card cardFrom = cardService.getCardById(1L);
        Card cardTo = cardService.getCardById(2L);
        ResponseEntity<?> response = transferController.makeTransfer(cardFrom, cardTo, 100);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

}