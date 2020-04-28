package ru.sberbank.dto;

public class AddMoneyToCardDTO {
    private Long cardId;
    private int sumOfUpping;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public int getSumOfUpping() {
        return sumOfUpping;
    }

    public void setSumOfUpping(int sumOfUpping) {
        this.sumOfUpping = sumOfUpping;
    }
}
