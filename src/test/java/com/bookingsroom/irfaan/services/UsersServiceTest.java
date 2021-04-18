package com.bookingsroom.irfaan.services;

import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.repositories.restapi.UsersRepository;
import com.bookingsroom.irfaan.services.impl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {


    @InjectMocks
    private UsersServiceImpl usersService;

    @Mock
    private UsersRepository usersRepository;



    private Users input;
    private Users output;
    private Users expected;

    @BeforeEach
    void init() {
        LocalDateTime date = LocalDateTime.now();
        Users input = new Users();
        input.setId(1);
        input.setEmail("test@gmail.com");
        input.setPhoto("asodko");
        input.setPassword("aodwk");
        input.setCreatedAt(date);
        input.setUpdatedAt(date);
        input.setDeletedAt(date);

        Users output = new Users();
        output.setId(input.getId());
        output.setEmail(input.getEmail());
        output.setPhoto(input.getPhoto());
        output.setPassword(input.getPassword());
        output.setCreatedAt(input.getCreatedAt());
        output.setUpdatedAt(input.getUpdatedAt());
        output.setDeletedAt(input.getDeletedAt());

        this.input = input;
        this.output = output;
        this.expected = input;
    }
    @Test
    void shouldSave() {

        when(usersRepository.save(any())).thenReturn(output);
        Users result = usersService.save(input);
        assertEquals(output, result);
    }

    @Test
    void showFindByID() {

        Users actual = usersService.findById(expected.getId());
        assertNotNull(expected); // Alamat memori tidak sama
    }

    @Test
    void showRemoveIDSuccess() {
        when(usersRepository.findById(input.getId())).thenReturn(Optional.ofNullable(input));
        Users actual = usersService.removeById(input.getId());
        assertNotNull(actual);
    }

    @Test
    void showRemoveIDFail() {
        Users actual = usersService.removeById(input.getId());
        assertNull(actual);
    }

    @Test
    void findAllSucess() {
        List<Users> allPages = usersService.findAll();
        assertNotNull(allPages);
    }

    @Test
    void findAllPaginationNull() {
        Users search = new Users();
        int page = 0;
        int size = 1;
        Sort.Direction direction = Sort.Direction.ASC;
        Page<Users> allPages = usersService.findAll(search, page, size, direction);
        assertNull(allPages);
    }
}
