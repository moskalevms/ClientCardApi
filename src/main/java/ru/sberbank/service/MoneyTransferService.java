package ru.sberbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import ru.sberbank.dto.CardDTO;
import ru.sberbank.dto.ClientDTO;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.exceptions.NotEnoughMoneyException;
import ru.sberbank.repositories.CardRepository;


import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class MoneyTransferService {
    private CardRepository cardRepository;

    private ClientService clientService;
    private CardService cardService;

    @Autowired
    public void CardRepository(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }
    @Autowired
    public void ClientService(ClientService clientService) {
        this.clientService = clientService;
    }
    @Autowired
    public void CardService(CardService cardService) {
        this.cardService = cardService;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void addCard(Long clientId, Long cardFromId, Long cardToId){
        Set<Card> cards = new LinkedHashSet<>();
        Client client = clientService.getClientById(clientId);
        Card cardFrom = cardService.getCardById(cardFromId);
        Card cardTo = cardService.getCardById(cardToId);
        cards.add(cardFrom);
        cards.add(cardTo);
        cardTo.setClient(client);
        cardFrom.setClient(client);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void removeCard(Long cardId, Long clientId, List<Card> cards){
        Client client = clientService.getClientById(clientId);
        Card card = cardService.getCardById(cardId);
        cards.remove(card);
        card.setClient(client);
    }

    public void upTheBalance(Long cardId, int sumOfUpdate ){
        Optional<Card> card = cardRepository.findById(cardId);
        card.map(c -> {c.setCash(c.getCash() + sumOfUpdate); return c;})
                .map(cardRepository::save).orElseThrow(RuntimeException::new);
        card.get().getCash();
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void transfer(Long fromCardId, Long toCardId, int sumOfTransfer ){
            Optional<Card> cardFrom = cardRepository.findById(fromCardId);
            if(cardFrom.get().getCash() < sumOfTransfer){
                throw new NotEnoughMoneyException("На карте недостаточно средств для перевода");
            }
            cardFrom
                   .map(c -> {c.setCash(c.getCash() - sumOfTransfer); return c;})
                   .map(cardRepository::save).orElseThrow(RuntimeException::new);

           cardRepository.findById(toCardId)
                   .map(c -> {c.setCash(c.getCash() + sumOfTransfer); return c;})
                   .map(cardRepository::save).orElseThrow(RuntimeException::new);
    }

}
