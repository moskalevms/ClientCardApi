package ru.sberbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.entities.Card;
import ru.sberbank.repositories.CardRepository;
import ru.sberbank.repositories.ClientRepository;

import java.util.List;

@Service
public class CardService {

    private CardRepository cardRepository;

    @Autowired
    public void setCardRepository(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Card save (Long clientId, Card card){
        return clientRepository.findById(clientId)
                .map(client -> {
                    card.setClient(client);
                    return cardRepository.save(card);
                }).orElseThrow(() -> new RuntimeException());
    }

    //TODO не использовать get
    public Card getCardById(Long id){
       return cardRepository.findById(id).get();
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
