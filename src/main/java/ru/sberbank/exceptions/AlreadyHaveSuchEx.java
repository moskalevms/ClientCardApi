package ru.sberbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyHaveSuchEx extends RuntimeException {

    public AlreadyHaveSuchEx(String message) {
        super(message);
    }

    public AlreadyHaveSuchEx(String message, Throwable cause) {
        super(message, cause);
    }
}
