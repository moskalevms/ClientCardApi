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
/*

    @Bean
    public TestRestTemplate testRestTemplate(){
         List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
         MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
         converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
         messageConverters.add(converter);
         restTemplate.getRestTemplate().setMessageConverters(messageConverters);
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
        ResponseEntity<List<Card>> response = cardController.showAllClientCards(2L);
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
        ResponseEntity<Card> response = cardController.showCardById(2L);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Card geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }


    @Test
    public void delete() {
        Client testClient = new Client();
        testClient.setClient_id(100L);
        testClient.setLogin("fsff");
        testClient.setPassword("fcvxv");
        testClient.setFirstname("Ivan");
        testClient.setLastname("dfgg");

        testClient = clientService.save(testClient);

        Card testCard = new Card();
        testCard.setNumber("699326943");
        testCard.setCash(5000);

        testCard = cardService.save(testCard, testClient.getClient_id());

        ResponseEntity<Card> response = (ResponseEntity<Card>) cardController.delete(testCard.getId());
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
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