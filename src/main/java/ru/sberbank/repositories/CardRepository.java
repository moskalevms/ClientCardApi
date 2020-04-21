package ru.sberbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.sberbank.entities.Card;


public interface CardRepository extends JpaRepository<Card, Integer> {
/*

    @Modifying
    @Query("update Card c set c.cash = c.cash - ?1 where c.id = ?2")
    Card setUpdateCardMinusById(int numOfTransaction, Integer cardId);


    @Modifying
    @Query("update Card c set c.cash = c.cash + ?1 where c.id = ?2")
    Card setUpdateCardPlusById(int numOfTransaction, Integer cardId);
*/

}
