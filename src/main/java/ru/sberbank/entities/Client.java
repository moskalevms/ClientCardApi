package ru.sberbank.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;

@Entity
@Table (name = "client")
public class Client implements Serializable {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Card> cards = new ArrayList<>();

    @ManyToMany
    @JoinTable (name = "clients_roles",
            joinColumns = @JoinColumn (name = "client_id"),
            inverseJoinColumns = @JoinColumn (name = "role_id")
    )
    private Collection<Role> roles;


    public Client(){
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long id) {
        this.client_id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setRole(Role role) {
        setRoles(singletonList(role));
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + client_id +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
