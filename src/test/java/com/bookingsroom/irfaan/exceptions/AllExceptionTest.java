package com.bookingsroom.irfaan.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class AllExceptionTest {


    @Test
    void getterTest() {
        ApplicationException applicationException = new ApplicationException("x", HttpStatus.NOT_FOUND);
        EntityNotFoundException entityNotFoundException = new EntityNotFoundException();
        PathNotFoundException pathNotFoundException = new PathNotFoundException();

        assertEquals(HttpStatus.NOT_FOUND, applicationException.getStatus());
        assertEquals(HttpStatus.NOT_FOUND, pathNotFoundException.getStatus());
        assertEquals(HttpStatus.NOT_FOUND, entityNotFoundException.getStatus());
    }
}
