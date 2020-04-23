package ru.sberbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sberbank.entities.Card;

import java.util.List;


public interface CardRepository extends JpaRepository<Card, Long> {

    @Modifying
    @Query("update Card c set c.cash = c.cash - ?1 where c.id = ?2")
    Card setUpdateCardMinusById(int numOfTransaction, Long cardId);

    @Modifying
    @Query("update Card c set c.cash = c.cash + ?1 where c.id = ?2")
    Card setUpdateCardPlusById(int numOfTransaction, Long cardId);

    @Query(value = "select c from Card c where c.client.client_id = :clientId")
    Card findByClientId(@Param("clientId") Long clientId);

    @Query(value = "select c from Card c where c.client.client_id = :clientId")
    List<Card> findAllByClientId(@Param("clientId") Long clientId);

}
