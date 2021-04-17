package com.bookingsroom.irfaan.exceptions;

import org.springframework.http.HttpStatus;

public class PathNotFoundException extends ApplicationException {

    public PathNotFoundException() {
        super("error." +HttpStatus.NOT_FOUND.value() + ".path", HttpStatus.NOT_FOUND);
    }
}
