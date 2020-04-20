package ru.sberbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.repositories.CardRepository;
import ru.sberbank.repositories.ClientRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class MoneyTransferService {
    private CardRepository cardRepository;

    private ClientService clientService;
    private CardService cardService;

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


    /*
    @Transactional
    public List<Card> moneyTranferServise(Client client, Card card, int numOfTransfer) {
        Client currentClient = clientService.getClientById(client.getId());
        Card currentCard = cardService.getCardById(card.getId());
        Card cardToTranfer = cardService.getCardById(Math.toIntExact(card.getClient().getId()));
        List<Card> cards = new ArrayList<>();
        cards.add(currentCard);
        cards.add(cardToTranfer);
        currentClient.setCards(cards);

        currentCard = cardRepository.setUpdateCardMinusById(numOfTransfer, currentCard.getId());
        cardToTranfer =  cardRepository.setUpdateCardPlusById(numOfTransfer, cardToTranfer.getId());

        cards.add(currentCard);
        cards.add(cardToTranfer);
        return cards;
    }
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public MoneyTransferService transfer(Integer fromCardId, Integer toCardId, int sumOfTransfer ){

           cardRepository.findById(fromCardId)
                   .map(c -> {c.setCash(c.getCash() - sumOfTransfer); return c;})
                   .map(cardRepository::save).orElseThrow(RuntimeException::new);

           cardRepository.findById(toCardId)
                   .map(c -> {c.setCash(c.getCash() + sumOfTransfer); return c;})
                   .map(cardRepository::save).orElseThrow(RuntimeException::new);
           return new MoneyTransferService();
    }

}
