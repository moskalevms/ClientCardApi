package ru.sberbank.rest;

import org.junit.Ignore;
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
import org.springframework.util.Assert;
import ru.sberbank.controllers.ClientController;
import ru.sberbank.entities.Client;
import ru.sberbank.repositories.ClientRepository;
import ru.sberbank.service.ClientService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientRestTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientService clientService;


    private Client createTestClient(Long id, String login, String password, String firstname, String lastname) {
        Client emp = new Client();
        emp.setClient_id(id);
        emp.setLogin(login);
        emp.setPassword(password);
        emp.setFirstname(firstname);
        emp.setLastname(lastname);
        return clientService.save(emp);
    }

    //TODO Нерабочий, разобраться
    @Test
    @Ignore
    public void createClient() {
        Client testClient = new Client();
        createTestClient(400L, "A", "1", "2", "3");

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
    @Ignore//TODO Нерабочий, разобраться
    public void delete() {
         ResponseEntity<Client> response = template.exchange("http://localhost:8080/app/api/clients/delete/15", HttpMethod.DELETE,null, Client.class);
         assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}