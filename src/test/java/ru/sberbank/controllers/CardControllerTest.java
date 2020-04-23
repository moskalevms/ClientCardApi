package ru.sberbank.controllers;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.repositories.ClientRepository;
import ru.sberbank.service.CardService;
import ru.sberbank.service.ClientService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CardService cardService;

    @Autowired
    private CardController cardController;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @LocalServerPort
    int randomServerPort;


    @Bean
    public TestRestTemplate testRestTemplate(){
         List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
         MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
         converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
         messageConverters.add(converter);
         restTemplate.getRestTemplate().setMessageConverters(messageConverters);
         return restTemplate;
    }


/*
    @Bean
    public RestTemplate restTemplate(){
        final RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

*/

    @Test
    public void createCard() {


        Client testClient = clientService.getClientById(1L);
        Card testCard = new Card();
        testCard.setNumber("cvbbc");
        testCard.setCash(6000);
        testCard.setClient(testClient);

        testClient.addCard(testCard);

        ResponseEntity<Card> response = (ResponseEntity<Card>) cardController.createCard(testCard, testClient.getClient_id());
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

        ResponseEntity<Card> response = cardController.showCardByClientId(1L);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Card geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }


    @Test
    public void showCardById() {
        ResponseEntity<Card> response = cardController.showCardById(2L);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Card geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }












/*

    @Test
    public void showAllCards() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "app/card/cards";
        URI uri = new URI(baseUrl);
        List<Card> cards =  cardService.getAllCardsList();
        HttpEntity<List<Card>> userHttpEntity = new HttpEntity<>(cards);
        ResponseEntity<List<Card>> response = this.restTemplate.exchange(uri, HttpMethod.GET, userHttpEntity, new ParameterizedTypeReference<List<Card>>() {
        });
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        List<Card> geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showCardById() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + randomServerPort + "/cards/1";
        URI uri = new URI(baseUrl);
        Card card = cardService.getCardById(1);
        Hibernate.initialize(card);
        HttpEntity<Card> userHttpEntity = new HttpEntity<>(card);
        ResponseEntity<Card> response = this.restTemplate.postForEntity(uri, userHttpEntity, Card.class);
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Card geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void delete() {
    }

 */
}