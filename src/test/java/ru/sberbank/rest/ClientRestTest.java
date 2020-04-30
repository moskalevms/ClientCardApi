package ru.sberbank.rest;

import org.junit.Ignore;
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

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setLogin("U");
        clientDTO.setPassword("100");
        clientDTO.setFirstname("2");
        clientDTO.setLastname("3");

        HttpEntity<ClientDTO> request = new HttpEntity<>(clientDTO);

        ResponseEntity<Client> response = template.withBasicAuth("admin", "qwerty")
                .postForEntity("http://localhost:8080/app/api/clients/create", request, Client.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void showAllClients() {
        ResponseEntity<List<Client>> response = template.withBasicAuth("admin", "qwerty")
                .exchange("http://localhost:8080/app/api/clients", HttpMethod.GET, null, new ParameterizedTypeReference<List<Client>>() {
        });
        List<Client> clients = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(String.valueOf(clients), "is null");
    }

    @Test
    public void showClientById() {
        ResponseEntity<Client> response = template.withBasicAuth("admin", "qwerty")
                .exchange("http://localhost:8080/app/api/clients/1", HttpMethod.GET, null, Client.class);
        Client testClient = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(String.valueOf(testClient), "is null");
    }


    @Test
    @Ignore
    public void delete() {
        ClientDTO testClientDTO = new ClientDTO();
        testClientDTO.setId(10L);
        testClientDTO.setLogin("R");
        testClientDTO.setPassword("1");
        testClientDTO.setFirstname("2");
        testClientDTO.setLastname("3");

        Client client = clientService.save(testClientDTO);

        HttpEntity<Client> request = new HttpEntity<>(client);
        ResponseEntity<Client> response = template.withBasicAuth("admin", "qwerty")
                .exchange("http://localhost:8080/app/api/clients/delete/10", HttpMethod.DELETE, request, Client.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}