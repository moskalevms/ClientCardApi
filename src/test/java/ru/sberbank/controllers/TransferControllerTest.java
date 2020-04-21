package ru.sberbank.controllers;


import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.sberbank.entities.ClientDTO;

import java.net.URI;
import java.net.URISyntaxException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;


    @Test
    public void happyGeoLocationTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/controller/create";
        URI uri = new URI(baseUrl);
        ClientDTO user =  new ClientDTO();
        HttpEntity<ClientDTO> userHttpEntity = new HttpEntity<>(user);
        ResponseEntity<ClientDTO> response = this.restTemplate.postForEntity(uri, userHttpEntity, ClientDTO.class);
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        ClientDTO geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }



}