package ru.sberbank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.entities.Card;
import ru.sberbank.entities.Client;
import ru.sberbank.exceptions.AlreadyHaveSuchEx;
import ru.sberbank.service.CardService;
import ru.sberbank.service.ClientService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {

    private CardService cardService;

    private static final Logger log = LoggerFactory.getLogger(CardController.class);

    @Autowired
    public void CardService(CardService cardService){
        this.cardService = cardService;
    }

    @RequestMapping(value = "/cli/{clientId}/cards", method = RequestMethod.POST)
    public ResponseEntity<?> createCard(@PathVariable Long clientId, @RequestBody Card card){
        try {
            cardService.save(clientId, card);
        } catch (AlreadyHaveSuchEx ex){
            log.info("Save not ok, Card already exist!" , ex);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> showAllCards(){
        List<Card> cards = cardService.getAllCardsList();
        log.info("All cards showed");
        return cards != null && !cards.isEmpty()
                ? new ResponseEntity<>(cards, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cardscli/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> showAllClientCards(@PathVariable(name = "id") Long id){
        List<Card> cards = cardService.getAllByClientId(id);
        log.info("All cards by client");
        return cards != null && !cards.isEmpty()
                ? new ResponseEntity<>(cards, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
    public ResponseEntity<Card> showCardByClientId(@PathVariable(name = "id") Long id){
        Card card = cardService.getCardByClientId(id);
        log.info("Card by client");
        return card != null
                ? new ResponseEntity<>(card, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cards/{id}", method = RequestMethod.GET)
    public ResponseEntity<Card> showCardById(@PathVariable(name = "id") Long id){
        Card card = cardService.getCardById(id);
        log.info("Card by id");
        return card != null
                ? new ResponseEntity<>(card, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cards/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        cardService.delete(id);
        log.info("Card deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
