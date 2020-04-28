package ru.sberbank.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ClientDTO implements Serializable {

    private Long id;

    @NotBlank (message = "Обязательное поле")
    @Size (max = 50, message = "Количество символов должно быть не больше 50")
    private String firstname;

    @NotBlank (message = "Обязательное поле")
    @Size (max = 50, message = "Количество символов должно быть не больше 50")
    private String lastname;


    @NotBlank (message = "Обязательное поле")
    @Size(min = 4, max = 30, message = "Количество символов должно быть от 4 до 30")
    private String login;

    @NotBlank (message = "обязательное поле")
    @Size (min = 8, max = 80, message = "количество символов должно быть от 8 до 80")
    private String password;

    @NotBlank(message = "Обязательное поле")
    public String matchingPassword;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
