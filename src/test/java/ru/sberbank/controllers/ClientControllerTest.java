package ru.sberbank.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.sberbank.entities.Client;
import ru.sberbank.service.ClientService;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @Autowired
    private ClientController clientController;

    @Autowired
    private ClientService clientService;

    @LocalServerPort
    int randomServerPort;


    @Test
    public void createClient() {
        Client testClient = new Client();
        testClient.setLogin("fsff");
        testClient.setPassword("fcvxv");
        testClient.setFirstname("Ivan");
        testClient.setLastname("dfgg");

        ResponseEntity<Client> response = (ResponseEntity<Client>) clientController.createClient(testClient);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.CREATED == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showAllClients() {
        ResponseEntity<List<Client>> response = clientController.showAllClients();
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        List<Client> geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showClientById() {
        ResponseEntity<Client> response = clientController.showClientById(1L);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Client geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void delete() {
        Client testClient = new Client();
        testClient.setLogin("fsff");
        testClient.setPassword("fcvxv");
        testClient.setFirstname("Ivan");
        testClient.setLastname("dfgg");

        testClient = clientService.save(testClient);

        ResponseEntity<Client> response = (ResponseEntity<Client>) clientController.delete(testClient.getClient_id());
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");

    }
}