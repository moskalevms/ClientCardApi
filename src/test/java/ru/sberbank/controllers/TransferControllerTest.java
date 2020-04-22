package ru.sberbank.controllers;


import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.sberbank.entities.ClientDTO;
import ru.sberbank.service.CardService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CardService cardService;

    @LocalServerPort
    int randomServerPort;


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



}