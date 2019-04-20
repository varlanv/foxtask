package com.company.foxtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoomIsTakenException extends RuntimeException {

    public RoomIsTakenException(String message) {
        super(message);
    }
}
