package ru.sberbank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.controllers.TransferController;
import ru.sberbank.entities.Card;
import ru.sberbank.exceptions.NotFoundException;
import ru.sberbank.repositories.CardRepository;
import ru.sberbank.repositories.ClientRepository;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public CardService(CardRepository cardRepository, ClientRepository clientRepository){
        this.cardRepository = cardRepository;
        this.clientRepository = clientRepository;
    }


    private static final Logger log = LoggerFactory.getLogger(CardService.class);

    public Card save (Long clientId, Card card){
        return clientRepository.findById(clientId)
                .map(client -> {
                    card.setClient(client);
                    return cardRepository.save(card);
                }).orElseThrow(() -> new NotFoundException("Client not found!"));
    }

    public Card getCardById(Long id){
        Card card = cardRepository.findById(id).orElse(null);
        if(card == null){
            log.warn("No card found by id: {}", id);
            return null;
        }
        log.info("Card was found by id: {} ", card, id);
        return card;

    }

    public List<Card> getAllByClientId(Long id){
        return cardRepository.findAllByClientId(id);
    }

    public Card getCardByClientId(Long id){
        return cardRepository.findByClientId(id);
    }

    public List<Card> getAllCardsList(){
        return cardRepository.findAll();
    }

    public void delete(Long id){
       cardRepository.deleteById(id);
    }


}
