package ru.sberbank.rest;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.ClientService;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardRestTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    ClientService clientService;

    @Test
    public void createCard() {
        Card testCard = new Card();
        testCard.setNumber("A");
        testCard.setCash(2000);
        ResponseEntity<Card> response = template.postForEntity("http://localhost:8080/app/api/cli/1/cards", testCard, Card.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void showAllCards() {
        ResponseEntity<List<Card>> response = template.exchange("http://localhost:8080/app/api/cards", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Card>>() {
        });
        List<Card> cards = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(String.valueOf(cards), "is null");
    }

    @Test
    public void showAllClientsCards() {
        ResponseEntity<List<Card>> response = template.exchange("http://localhost:8080/app/api/cardscli/1", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Card>>() {
        });
        List<Card> cards = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(String.valueOf(cards), "is null");
    }

    @Test
    public void showCardByClientId(){
        ResponseEntity<Card> response = template.exchange("http://localhost:8080/app/api/card/2", HttpMethod.GET,
                null, Card.class);
        Card testCard = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(String.valueOf(testCard), "is null");
    }

    @Test
    public void showCardById(){
        ResponseEntity<Card> response = template.exchange("http://localhost:8080/app/api/cards/1", HttpMethod.GET,
                null, Card.class);
        Card testCard = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(String.valueOf(testCard), "is null");
    }

    @Test
    public void shouldDeleteCard(){
        ResponseEntity<Card> response = template.exchange("http://localhost:8080/app/api//cards/del/1", HttpMethod.DELETE,null, Card.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }




}
