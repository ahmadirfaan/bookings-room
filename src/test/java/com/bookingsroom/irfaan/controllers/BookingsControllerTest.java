package com.bookingsroom.irfaan.controllers;

import com.bookingsroom.irfaan.entities.Bookings;
import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.services.BookingsService;
import com.bookingsroom.irfaan.services.MailService;
import com.bookingsroom.irfaan.services.RoomsService;
import com.bookingsroom.irfaan.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookingsController.class)
class BookingsControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingsService bookingsService;

    @MockBean
    private RoomsService roomsService;

    @MockBean
    private UsersService usersService;

    @MockBean
    private MailService mailService;

    @MockBean
    private ModelMapper modelMapper;


    @Mock
    private Rooms room;

    @Mock
    private Users user;

    private Bookings entity;

    @BeforeEach
    void init() {
        LocalDateTime date = LocalDateTime.now();
        Bookings entity = new Bookings();
        entity.setId(1);
        entity.setRoomId(room);
        entity.setUserId(user);
        entity.setTotalPerson(10);
        entity.setCheckInTime(date);
        entity.setCheckOutTime(date);
        entity.setBookingTime(date);
        this.entity = entity;
    }

    @Test
    void addShouldFail() throws Exception {

        when(bookingsService.save(any())).thenReturn(entity);

        RequestBuilder request = post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": 1, \"roomId\": 1,  \"totalPerson\": 10,  \"bookingTime\": \"2021-04-26\", \"noted\" : \"Ruangannya tolong yah dibersihkan, makasih :D\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }

    @Test
    void findByIdShouldSuccess() throws Exception {

        when(bookingsService.save(any())).thenReturn(entity);
        when(bookingsService.findById(entity.getId())).thenReturn(entity);

        RequestBuilder request = get("/bookings/" + entity.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())));
    }

    @Test
    void editShouldSuccess() throws Exception {

        when(bookingsService.save(any())).thenReturn(entity);
        when(bookingsService.findById(entity.getId())).thenReturn(entity);
        when(bookingsService.save(any())).thenReturn(entity);

        RequestBuilder request = put("/bookings/" + entity.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.BAD_REQUEST.value())));

    }

    @Test
    void softDeleteByIdShouldFail() throws Exception {

        when(bookingsService.save(any())).thenReturn(entity);
        when(bookingsService.removeById(entity.getId())).thenReturn(entity);

        RequestBuilder request = put("/bookings/delete/" + entity.getId().toString());
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));
    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page entityPage = Page.empty();
        when(bookingsService.findAll(any(), anyInt(), anyInt(), any())).thenReturn(entityPage);

        RequestBuilder request = get("/bookings");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }
}
