package ru.sberbank.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "token")
  private String token;

  @Column(name = "expiry_date", columnDefinition = "TIMESTAMP")
  private Date expiryDate;

  @OneToOne
  @JoinColumn(nullable = false, name = "client_id")
  private Client client;

  private static final int EXPIRATION_TIME_IN_SEC = 60 * 60;

  public PasswordResetToken() {
  }

  public PasswordResetToken(String _token, Client _client){
	token = _token;
	client = _client;
	Date now = Calendar.getInstance().getTime();
	long time = now.getTime() + EXPIRATION_TIME_IN_SEC * 1000;
	expiryDate = new Date(time);
  }

}