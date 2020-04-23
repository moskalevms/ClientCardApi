package ru.sberbank.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.util.Assert;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.CardService;
import ru.sberbank.service.ClientService;
import ru.sberbank.service.MoneyTransferService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MoneyTransferService moneyTransferService;

    @Autowired
    private TransferController transferController;

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

    TestRestTemplate tr = new TestRestTemplate();

    @Test
    public void happyGeoLocationTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/account/cr";
        URI uri = new URI(baseUrl);
        ClientDTO user =  new ClientDTO();
        HttpEntity<ClientDTO> userHttpEntity = new HttpEntity<>(user);
        ResponseEntity<ClientDTO> response = tr.postForEntity(uri, userHttpEntity, ClientDTO.class);
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        ClientDTO geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }
*/

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