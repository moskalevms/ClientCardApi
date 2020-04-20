package ru.sberbank.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.repositories.CardRepository;
import ru.sberbank.repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MoneyTransferServiceTest {

    private ClientRepository clientRepository;
    private CardRepository cardRepository;

    private ClientService clientService;
    private CardService cardService;
    private MoneyTransferService moneyTransferService;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    @Autowired
    public void setCardRepository(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }
    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Autowired
    public void setMoneyTransferService(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }



    @Test
    public void setClientRepository() {
    }

    @Test
    public void setCardRepository() {
    }

    @Test
    public void setClientService() {
    }

    @Test
    public void setCardService() {
    }

    @Test
    public void moneyTranferServise() {
        Client testClient = new Client();
        int numOfTranfer = 100;

        Card currentCard = new Card();
        currentCard.setId(6);
        currentCard.setNumber("11112222333344444");
        currentCard.setCash(1000);
        currentCard.setClient(testClient);

        Card cardToTranfer = new Card();
        cardToTranfer.setId(7);
        cardToTranfer.setNumber("5555666677778888");
        cardToTranfer.setCash(500);
        cardToTranfer.setClient(testClient);


        List<Card> clientCards = new ArrayList<>();
        clientCards.add(currentCard);
        clientCards.add(cardToTranfer);

        testClient.setId(5L);
        testClient.setLogin("admin");
        testClient.setPassword("qwerty");
        testClient.setFirstName("Ivan");
        testClient.setLastName("Ivanov");
        testClient.setCards(clientCards);


        List<Card> expectedCard = new ArrayList<>();
        currentCard.setCash(900);
        cardToTranfer.setCash(600);
        expectedCard.add(currentCard);
        expectedCard.add(cardToTranfer);

  //     List<Card>  actual =  mTService.moneyTranferServise(testClient, currentCard, numOfTranfer);
   //     Assert.assertEquals(expectedCard, actual);

    }

    @Test
    public void shouldReturnTransfer() {
            Client client = new Client();

            Card cardFrom = new Card();
            cardFrom.setId(1);
            cardFrom.setNumber("1111222233334444");
            cardFrom.setCash(1000);
            cardFrom.setClient(client);


            Card cardTo = new Card();
            cardTo.setId(2);
            cardTo.setNumber("45657755764");
            cardTo.setCash(2000);
            cardTo.setClient(client);



            client.setId(1L);
            client.setLogin("gsdfg");
            client.setPassword("123");
            client.setFirstName("geafg");
            client.setLastName("twttdf");

         //   moneyTransferService.transfer(1, 2, 100, 1L);




    }


}