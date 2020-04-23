package ru.sberbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.service.CardService;
import ru.sberbank.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {

    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService){
        this.cardService = cardService;
    }

    @RequestMapping(value = "/clients/{clientId}/cards", method = RequestMethod.POST)
    public ResponseEntity<?> createCard(@RequestBody Card card, Long clientId){
        cardService.save(card, clientId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> showAllCards(){
        List<Card> cards = cardService.getAllCardsList();
        return cards != null && !cards.isEmpty()
                ? new ResponseEntity<>(cards, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cardscli/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> showAllClientCards(@PathVariable(name = "id") Long id){
        List<Card> cards = cardService.getAllByClientId(id);
        return cards != null && !cards.isEmpty()
                ? new ResponseEntity<>(cards, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
    public ResponseEntity<Card> showCardByClientId(@PathVariable(name = "id") Long id){
        Card card = cardService.getCardByClientId(id);
        return card != null
                ? new ResponseEntity<>(card, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cards/{id}", method = RequestMethod.GET)
    public ResponseEntity<Card> showCardById(@PathVariable(name = "id") Long id){
        Card card = cardService.getCardById(id);
        return card != null
                ? new ResponseEntity<>(card, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        cardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
