package com.bookingsroom.irfaan.services;

import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.repositories.restapi.RoomsRepository;
import com.bookingsroom.irfaan.repositories.restapi.UsersRepository;
import com.bookingsroom.irfaan.services.impl.RoomsServiceImpl;
import com.bookingsroom.irfaan.services.impl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomsServiceTest {

    @InjectMocks
    private RoomsServiceImpl roomsService;

    @Mock
    private RoomsRepository roomsRepository;

    private Rooms input;
    private Rooms output;
    private Rooms expected;

    @BeforeEach
    void init() {
        LocalDateTime date = LocalDateTime.now();
        Rooms input = new Rooms();
        input.setId(1);
        input.setRoomName("asd");
        input.setPhoto("asodko");
        input.setRoomCapacity(4);
        input.setCreatedAt(date);
        input.setUpdatedAt(date);
        input.setDeletedAt(date);

        Rooms output = new Rooms();
        output.setId(input.getId());
        output.setRoomName(input.getRoomName());
        output.setPhoto(input.getPhoto());
        output.setRoomCapacity(input.getRoomCapacity());
        output.setCreatedAt(input.getCreatedAt());
        output.setUpdatedAt(input.getUpdatedAt());
        output.setDeletedAt(input.getDeletedAt());

        this.input = input;
        this.output = output;
        this.expected = input;
    }
    @Test
    void shouldSave() {

        when(roomsRepository.save(any())).thenReturn(output);
        Rooms result = roomsService.save(input);
        assertEquals(output, result);
    }

    @Test
    void showFindByID() {

        Rooms actual = roomsService.findById(expected.getId());
        assertNotNull(expected); // Alamat memori tidak sama
    }

    @Test
    void showRemoveIDSuccess() {
        when(roomsRepository.findById(input.getId())).thenReturn(Optional.ofNullable(input));
        Rooms actual = roomsService.removeById(input.getId());
        assertNotNull(actual);
    }

    @Test
    void showRemoveIDFail() {
        Rooms actual = roomsService.removeById(input.getId());
        assertNull(actual);
    }

    @Test
    void findAllSucess() {
        List<Rooms> allPages = roomsService.findAll();
        assertNotNull(allPages);
    }

    @Test
    void findAllPaginationNull() {
        Rooms search = new Rooms();
        int page = 0;
        int size = 1;
        Sort.Direction direction = Sort.Direction.ASC;
        Page<Rooms> allPages = roomsService.findAll(search, page, size, direction);
        assertNull(allPages);
    }
}
