package ru.sberbank.rest;

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
import ru.sberbank.entities.Client;
import ru.sberbank.repositories.ClientRepository;
import ru.sberbank.service.ClientService;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientRestTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void createClient() {
        Client testClient = new Client();
        testClient.setLogin("A");
        testClient.setPassword("1");
        testClient.setFirstname("2");
        testClient.setLastname("3");

        ResponseEntity<Client> response = template.postForEntity("http://localhost:8080/app/api/clients/create", testClient, Client.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void showAllClients() {
        ResponseEntity<List<Client>> response = template.exchange("http://localhost:8080/app/api/clients", HttpMethod.GET, null, new ParameterizedTypeReference<List<Client>>() {
        });
        List<Client> clients = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(String.valueOf(clients), "is null");
    }

    @Test
    public void showClientById() {
        ResponseEntity<Client> response = template.exchange("http://localhost:8080/app/api/clients/1", HttpMethod.GET, null, Client.class);
        Client testClient = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(String.valueOf(testClient), "is null");
    }


    @Test
    public void delete() {
         ResponseEntity<Client> response = template.exchange("http://localhost:8080/app/api/clients/delete/26", HttpMethod.DELETE,null, Client.class);
         assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}