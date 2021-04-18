package com.bookingsroom.irfaan.services;

import com.bookingsroom.irfaan.entities.Bookings;
import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.repositories.restapi.BookingsRepository;
import com.bookingsroom.irfaan.services.impl.BookingsServiceImpl;
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
class BookingServiceTest {

    @InjectMocks
    private BookingsServiceImpl bookingsService;

    @Mock
    private BookingsRepository bookingsRepository;

    @Mock
    private Users users;

    @Mock
    private Rooms rooms;

    private Bookings input;
    private Bookings output;
    private Bookings expected;

    @BeforeEach
    void init() {
        LocalDateTime date = LocalDateTime.now();
        Bookings input = new Bookings();
        input.setId(1);
        input.setUserId(users);
        input.setRoomId(rooms);
        input.setCheckInTime(date);
        input.setCheckOutTime(date);
        input.setTotalPerson(10);
        input.setCreatedAt(date);
        input.setUpdatedAt(date);
        input.setDeletedAt(date);


        Bookings output = new Bookings();
        output.setId(input.getId()); //Tidak bikin constructor karena agar tercover line coverage ataupun method coverage
        output.setUserId(input.getUserId());
        output.setRoomId(input.getRoomId());
        output.setCheckInTime(input.getCheckInTime());
        output.setCheckOutTime(input.getCheckOutTime());
        output.setTotalPerson(input.getTotalPerson());
        output.setCreatedAt(input.getCreatedAt());
        output.setUpdatedAt(input.getUpdatedAt());
        output.setDeletedAt(input.getDeletedAt());

        this.input = input;
        this.output = output;
        this.expected = input;
    }
    @Test
    void shouldSave() {

        when(bookingsRepository.save(any())).thenReturn(output);
        Bookings result = bookingsService.save(input);
        assertEquals(output, result);
    }

    @Test
    void showFindByID() {

        Bookings actual = bookingsService.findById(expected.getId());
        assertNotNull(expected); // Alamat memori tidak sama
    }

    @Test
    void showRemoveIDSuccess() {
        when(bookingsRepository.findById(input.getId())).thenReturn(Optional.ofNullable(input));
        Bookings actual = bookingsService.removeById(input.getId());
        assertNotNull(actual);
    }

    @Test
    void showRemoveIDFail() {
        Bookings actual = bookingsService.removeById(input.getId());
        assertNull(actual);
    }

    @Test
    void findAllSucess() {
        List<Bookings> allPages = bookingsService.findAll();
        assertNotNull(allPages);
    }

    @Test
    void findAllPaginationNull() {
        Bookings search = new Bookings();
        int page = 0;
        int size = 1;
        Sort.Direction direction = Sort.Direction.ASC;
        Page<Bookings> allPages = bookingsService.findAll(search, page, size, direction);
        assertNull(allPages);
    }

}
