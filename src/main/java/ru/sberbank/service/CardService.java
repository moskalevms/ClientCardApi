package ru.sberbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.entities.Card;
import ru.sberbank.repositories.CardRepository;

import java.util.List;

@Service
public class CardService {

    private CardRepository cardRepository;

    @Autowired
    public void setCardRepository(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    public Card save (Card card){
        Card currentCard = new Card();
        currentCard.setId(card.getId());
        currentCard.setNumber(card.getNumber());
        currentCard.setCash(card.getCash());
        return cardRepository.save(currentCard);
    }


    public Card getCardById(Integer id){
       return cardRepository.findById(id).get();
    }

    public List<Card> getAllByClientId(Long id){
        return cardRepository.findAllByClientId(id);
    }


    public List<Card> getAllCardsList(){
        return cardRepository.findAll();
    }


    public void delete(Integer id){
       cardRepository.deleteById(id);
    }


}
