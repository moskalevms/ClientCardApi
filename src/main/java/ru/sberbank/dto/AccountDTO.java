package ru.sberbank.dto;

public class AccountDTO {
    private long clientId;
    private long cardFromId;
    private long cardToId;


    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getCardFromId() {
        return cardFromId;
    }

    public void setCardFromId(long cardFromId) {
        this.cardFromId = cardFromId;
    }

    public long getCardToId() {
        return cardToId;
    }

    public void setCardToId(long cardToId) {
        this.cardToId = cardToId;
    }
}
