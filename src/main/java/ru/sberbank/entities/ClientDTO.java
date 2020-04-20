package ru.sberbank.entities;

import java.io.Serializable;

public class ClientDTO implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
