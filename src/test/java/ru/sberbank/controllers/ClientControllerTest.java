package ru.sberbank.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.sberbank.entities.Client;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @Autowired
    private ClientController clientController;

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }


    @Test
    public void createClient() {
    }

    @Test
    public void showAllClients() {
        final String baseUrl = "http://localhost:" + 8080 + "/app/cli/clients";
        ResponseEntity<List<Client>> response = clientController.showAllClients();
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        List<Client> geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void showClientById() {
        final String baseUrl = "http://localhost:" + 8080 + "/app/cli/client/1";
        ResponseEntity<Client> response = clientController.showClientById(1L);
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        Client geoLocationInfo = response.getBody();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");
    }

    @Test
    public void delete() {
    }
}