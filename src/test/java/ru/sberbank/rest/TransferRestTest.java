package ru.sberbank.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.sberbank.controllers.TransferController;
import ru.sberbank.dto.AccountDTO;
import ru.sberbank.dto.MoneyTransferDTO;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.CardService;
import ru.sberbank.service.ClientService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferRestTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void mustCreateAnAccount(){
        long fromCardId = 1L;
        long toCardId = 2L;
        long clientId = 1L;
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId(clientId);
        accountDTO.setCardFromId(fromCardId);
        accountDTO.setCardToId(toCardId);
        HttpEntity<AccountDTO> request = new HttpEntity<>(accountDTO);

        ResponseEntity<AccountDTO> response = template.postForEntity("http://localhost:8080/app/account/create", request, AccountDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    public void mustAddMoney(){
        ResponseEntity<Card> response = template.exchange("http://localhost:8080/app/account/upbalance/1/card/100", HttpMethod.PUT, null, Card.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void mustMakeTranfer(){
        long fromCardId = 1L;
        long toCardId = 2L;
        MoneyTransferDTO transferDTO = new MoneyTransferDTO();
        transferDTO.setCardFromId(fromCardId);
        transferDTO.setCardToId(toCardId);
        transferDTO.setSum(100);
        HttpEntity<MoneyTransferDTO> request = new HttpEntity<>(transferDTO);

       ResponseEntity<MoneyTransferDTO> response = template.exchange("http://localhost:8080/app/account//transfer", HttpMethod.PUT, request, MoneyTransferDTO.class);
       assertEquals(HttpStatus.OK, response.getStatusCode());
    }




}
