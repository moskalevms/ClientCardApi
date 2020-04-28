package ru.sberbank.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sberbank.dto.ClientDTO;
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

    @Autowired
    private ClientService clientService;

    @Test
    public void createClient() {
        ClientDTO testClient = new ClientDTO();
        testClient.setLogin("U");
        testClient.setPassword("1");
        testClient.setFirstname("2");
        testClient.setLastname("3");

        ResponseEntity<ClientDTO> response = template.postForEntity("http://localhost:8080/app/api/clients/create", testClient, ClientDTO.class);
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
        ClientDTO testClient = new ClientDTO();
        testClient.setLogin("R");
        testClient.setPassword("1");
        testClient.setFirstname("2");
        testClient.setLastname("3");

        Client client = clientService.save(testClient);

        HttpEntity<Client> request = new HttpEntity<>(client);

        ResponseEntity<Client> response = template.exchange("http://localhost:8080/app/api/clients/delete/3", HttpMethod.DELETE,request, Client.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}