package ru.sberbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClientAlreadyExistsExceprion extends RuntimeException {

    public ClientAlreadyExistsExceprion(String message) {
        super(message);
    }

    public ClientAlreadyExistsExceprion(String message, Throwable cause) {
        super(message, cause);
    }
}
