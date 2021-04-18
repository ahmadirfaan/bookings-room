package com.bookingsroom.irfaan.controllers;

import com.bookingsroom.irfaan.controllers.defaults.DefaultErrorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DefaultErrorController.class)
class ErrorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void errorNotFoundTest() throws Exception {
        RequestBuilder request = get("/error").
                contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())))
                .andExpect(result -> assertNotNull(result.getResolvedException()));
    }

    @Test
    void errorMethodNotAllowedTest() throws Exception {
        RequestBuilder request = post("/error").
                contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(HttpStatus.METHOD_NOT_ALLOWED.value())))
                .andExpect(result -> assertNotNull(result.getResolvedException()));
    }

}
