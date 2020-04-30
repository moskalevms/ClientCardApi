package ru.sberbank.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sberbank.dto.AccountDTO;
import ru.sberbank.dto.AddMoneyToCardDTO;
import ru.sberbank.dto.MoneyTransferDTO;

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

        ResponseEntity<AccountDTO> response = template.withBasicAuth("admin", "qwerty")
                .postForEntity("http://localhost:8080/app/account", request, AccountDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    public void mustAddMoney(){
        long cardId = 1L;
        int sumOfUpping = 100;
        AddMoneyToCardDTO addMoneyDTO = new AddMoneyToCardDTO();
        addMoneyDTO.setCardId(cardId);
        addMoneyDTO.setSumOfUpping(sumOfUpping);

        HttpEntity<AddMoneyToCardDTO> request = new HttpEntity<>(addMoneyDTO);

        ResponseEntity<AddMoneyToCardDTO> response = template.withBasicAuth("admin", "qwerty")
                .exchange("http://localhost:8080/app/account/1/card/100", HttpMethod.PUT, request, AddMoneyToCardDTO.class);
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

        ResponseEntity<MoneyTransferDTO> response = template.withBasicAuth("admin", "qwerty")
                .exchange("http://localhost:8080/app/account/", HttpMethod.PUT, request, MoneyTransferDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }




}
