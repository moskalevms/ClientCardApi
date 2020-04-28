package ru.sberbank.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.sberbank.dto.ClientDTO;
import ru.sberbank.entities.Client;
import ru.sberbank.service.ClientService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @Autowired
    private ClientController clientController;

    @Autowired
    private ClientService clientService;


    @Test
    public void createClient() {
        ClientDTO testClientDTO = new ClientDTO();
        testClientDTO.setLogin("xbxcbb");
        testClientDTO.setPassword("sdsf");
        testClientDTO.setFirstname("xcvxcv");
        testClientDTO.setLastname("xcvzvx");

        ResponseEntity<ClientDTO> response = clientController.createClient(testClientDTO);
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
        ClientDTO testClient = new ClientDTO();
        testClient.setId(6L);
        testClient.setLogin("fsff");
        testClient.setPassword("fcvxv");
        testClient.setFirstname("Ivan");
        testClient.setLastname("dfgg");


        ResponseEntity<ClientDTO> response = (ResponseEntity<ClientDTO>) clientController.delete(testClient.getId());
        response.getStatusCode();
        Assert.isTrue(HttpStatus.OK == response.getStatusCode(), "http state not OK");
        HttpStatus geoLocationInfo = response.getStatusCode();
        Assert.notNull(geoLocationInfo, "geoLocationInfo is null");

    }
}