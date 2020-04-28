package ru.sberbank.dto;

import ru.sberbank.entities.Client;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CardDTO {

    @NotBlank(message = "Обязательное поле")
    private Long id;

    @NotBlank(message = "Обязательное поле")
    @Size(min = 16, max = 16, message = "Количество символов должно быть 16")
    private String number;

    private Integer cash;

    private Client client;


    public CardDTO(Long id, String number, Integer cash, Client client) {
        this.id = id;
        this.number = number;
        this.cash = cash;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
