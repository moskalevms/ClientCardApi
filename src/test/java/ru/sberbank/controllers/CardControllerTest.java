package ru.sberbank.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.CardService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CardService cardService;

    @LocalServerPort
    int randomServerPort;


    @Test
    public void happyCreateCardTest() throws URISyntaxException {
        Client client = new Client();
        client.setClient_id(1L);
        client.setLogin("tutu");
        client.setPassword("vxzvxcv");
        client.setFirstName("fsdf");
        client.setLastName("rqrweqr");
        Card card = new Card();

        card.setId(1);
        card.setNumber("35235355");
        card.setCash(200);

        List<Card> cards = new ArrayList<>();
        cards.add(card);

        client.setCards(cards);


        final String baseUrl = "http://localhost:" + randomServerPort + "/card/create";
        URI uri = new URI(baseUrl);
        cardService.save(card);
        HttpEntity<Card> userHttpEntity = new HttpEntity<>(card);
        ResponseEntity<Card> response = this.restTemplate.postForEntity(uri, userHttpEntity, Card.class);
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Card geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showAllCards() throws URISyntaxException {
      
        List<Card> cards = new ArrayList<>();

        final String baseUrl = "http://localhost:" + randomServerPort + "/card/cards";
        URI uri = new URI(baseUrl);
        cardService.getAllCardsList();
        HttpEntity<List<Card>> userHttpEntity = new HttpEntity<>(cards);
        ResponseEntity<Card> response = this.restTemplate.postForEntity(uri, userHttpEntity, Card.class);
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Card geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showCardById() {
    }

    @Test
    public void delete() {
    }
}