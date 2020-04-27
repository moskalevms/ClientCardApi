package ru.sberbank.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.repositories.ClientRepository;
import ru.sberbank.service.CardService;
import ru.sberbank.service.ClientService;

import java.util.List;

//TODO Изучить интеграционное тестирование с помощью моков, чтобы не трогать реальную БД
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardController cardController;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @Test
    public void createCard() {
        Client testClient = clientService.getClientById(1L);
        Card testCard = new Card();
        testCard.setNumber("99999");
        testCard.setCash(6000);
        testCard.setClient(testClient);

        ResponseEntity<Card> response = (ResponseEntity<Card>) cardController.createCard(testClient.getClient_id(), testCard);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.CREATED == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showAllCards() {
        ResponseEntity<List<Card>> response = cardController.showAllCards();
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        List<Card> geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showAllCardByClientId() {
        ResponseEntity<List<Card>> response = cardController.showAllClientCards(1L);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        List<Card> geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showCardByClientId() {
        ResponseEntity<Card> response = cardController.showCardByClientId(2L);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Card geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showCardById() {
        ResponseEntity<Card> response = cardController.showCardById(1L);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Card geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }


    @Test
    public void delete() {
        Client testClient = new Client();
        testClient.setClient_id(700000000L);
        testClient.setLogin("GodLike");
        testClient.setPassword("789");
        testClient.setFirstname("Test");
        testClient.setLastname("Testov");

        testClient = clientService.save(testClient);

        Card testCard = new Card();
        testCard.setId(100000L);
        testCard.setNumber("699326943");
        testCard.setCash(5000);

        testCard = cardService.save(testClient.getClient_id(), testCard);

        ResponseEntity<Card> response = (ResponseEntity<Card>) cardController.delete(testCard.getId());
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");

    }
}