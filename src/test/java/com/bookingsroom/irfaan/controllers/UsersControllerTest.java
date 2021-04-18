package com.bookingsroom.irfaan.controllers;

import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.models.request.UsersRequest;
import com.bookingsroom.irfaan.models.response.UsersResponse;
import com.bookingsroom.irfaan.services.FileService;
import com.bookingsroom.irfaan.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(UsersController.class)
class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private FileService fileService;

    @MockBean
    private ModelMapper modelMapper;


    private Users entity;
    private UsersRequest request;
    private UsersResponse response;

    @BeforeEach
    void init() {
        Users entity = new Users();
        entity.setId(1);
        entity.setEmail("test@gmail.com");
        entity.setPassword("adwoko");

        UsersRequest request = new UsersRequest();
        request.setEmail(entity.getEmail());
        request.setPassword(entity.getPassword());

        UsersResponse response = new UsersResponse();
        response.setEmail(entity.getEmail());
        response.setPhoto(entity.getPhoto());

        this.entity = entity;
        this.request = request;
        this.response = response;
    }
    @Test
    void addShouldSuccess() throws Exception {

        when(usersService.save(any())).thenReturn(entity);

        RequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"test@gmail.com\", \"password\": \"adwoko\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.email", is(entity.getEmail())));
    }

    @Test
    void findByIdShouldSuccess() throws Exception {

        when(usersService.save(any())).thenReturn(entity);
        when(usersService.findById(entity.getId())).thenReturn(entity);

        RequestBuilder request = get("/users/" + entity.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.email", is(entity.getEmail())));
    }

    @Test
    void editShouldSuccess() throws Exception {

        when(usersService.save(any())).thenReturn(entity);
        when(usersService.findById(entity.getId())).thenReturn(entity);
        when(usersService.save(any())).thenReturn(entity);

        RequestBuilder request = put("/users/" + entity.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"test@gmail.com\", \"password\": \"adwoko\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.email", is(entity.getEmail())));
    }

    @Test
    void softDeleteByIdShouldFail() throws Exception {

        when(usersService.save(any())).thenReturn(entity);
        when(usersService.removeById(entity.getId())).thenReturn(entity);

        RequestBuilder request = put("/users/delete/" + entity.getId().toString());
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));
    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page entityPage = Page.empty();
        when(usersService.findAll(any(), anyInt(),  anyInt(), any())).thenReturn(entityPage);

        RequestBuilder request = get("/users");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }
}
