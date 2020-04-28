package ru.sberbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CardAlreadyExistsExceprion extends RuntimeException {

    public CardAlreadyExistsExceprion(String message) {
        super(message);
    }

    public CardAlreadyExistsExceprion(String message, Throwable cause) {
        super(message, cause);
    }
}
