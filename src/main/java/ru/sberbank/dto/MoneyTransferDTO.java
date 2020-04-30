package ru.sberbank.dto;

public class MoneyTransferDTO {
    private Long cardFromId;
    private Long cardToId;
    private int sum;

    public Long getCardFromId() {
        return cardFromId;
    }

    public void setCardFromId(Long cardFromId) {
        this.cardFromId = cardFromId;
    }

    public Long getCardToId() {
        return cardToId;
    }

    public void setCardToId(Long cardToId) {
        this.cardToId = cardToId;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
