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
import ru.sberbank.entities.Client;
import ru.sberbank.entities.ClientDTO;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;



    @Test
    public void createClient() throws URISyntaxException {

        ClientDTO client = new ClientDTO();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientDTO> entity = new HttpEntity<ClientDTO>(client, headers);

        final String baseUrl = "http://localhost:" + randomServerPort + "/contoller/create";
        URI uri = new URI(baseUrl);
        ResponseEntity<ClientDTO> response = this.restTemplate.postForEntity(uri, entity, ClientDTO.class);
        Assert.isTrue(HttpStatus.CREATED == response.getStatusCode(), "http state not OK");
        ClientDTO geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }
}