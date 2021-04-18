package com.bookingsroom.irfaan.controllers;

import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.services.FileService;
import com.bookingsroom.irfaan.services.RoomsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

@WebMvcTest(RoomsController.class)
class RoomsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomsService roomsService;

    @MockBean
    private FileService fileService;

    @MockBean
    private ModelMapper modelMapper;


    private Rooms entity;

    @BeforeEach
    void init() {
        Rooms entity = new Rooms();
        entity.setId(1);
        entity.setRoomName("ruangan 1");
        entity.setRoomCapacity(2);


        this.entity = entity;
    }
    @Test
    void addShouldSuccess() throws Exception {

        when(roomsService.save(any())).thenReturn(entity);

        RequestBuilder request = post("/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"roomName\": \"ruangan 1\", \"roomCapacity\": 2}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.roomName", is(entity.getRoomName())));
    }

    @Test
    void findByIdShouldSuccess() throws Exception {

        when(roomsService.save(any())).thenReturn(entity);
        when(roomsService.findById(entity.getId())).thenReturn(entity);

        RequestBuilder request = get("/rooms/" + entity.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.roomName", is(entity.getRoomName())));
    }

    @Test
    void editShouldSuccess() throws Exception {

        when(roomsService.save(any())).thenReturn(entity);
        when(roomsService.findById(entity.getId())).thenReturn(entity);
        when(roomsService.save(any())).thenReturn(entity);

        RequestBuilder request = put("/rooms/" + entity.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"roomName\": \"ruangan 1\", \"roomCapacity\": 2}");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.roomName", is(entity.getRoomName())));
    }

    @Test
    void softDeleteByIdShouldFail() throws Exception {

        when(roomsService.save(any())).thenReturn(entity);
        when(roomsService.removeById(entity.getId())).thenReturn(entity);

        RequestBuilder request = put("/rooms/delete/" + entity.getId().toString());
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));
    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page entityPage = Page.empty();
        when(roomsService.findAll(any(), anyInt(),  anyInt(), any())).thenReturn(entityPage);

        RequestBuilder request = get("/rooms");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }
}
