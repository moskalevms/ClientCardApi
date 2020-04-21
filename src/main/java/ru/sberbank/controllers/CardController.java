package ru.sberbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.CardService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService){
        this.cardService = cardService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createCard(@RequestBody Card card){
        cardService.save(card);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> showAllCards(){
        List<Card> cards = cardService.getAllCardsList();
        return cards != null && !cards.isEmpty()
                ? new ResponseEntity<>(cards, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cards/{id}", method = RequestMethod.GET)
    public ResponseEntity<Card> showCardById(@PathVariable(name = "id") Integer id){
        Card card = cardService.getCardById(id);
        return card != null
                ? new ResponseEntity<>(card, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/cards/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        cardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
