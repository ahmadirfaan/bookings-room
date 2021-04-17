package com.bookingsroom.irfaan.controllers;

import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.exceptions.EntityNotFoundException;
import com.bookingsroom.irfaan.models.PageSearch;
import com.bookingsroom.irfaan.models.PagedList;
import com.bookingsroom.irfaan.models.ResponseMessage;
import com.bookingsroom.irfaan.models.request.UsersRequest;
import com.bookingsroom.irfaan.models.response.UsersResponse;
import com.bookingsroom.irfaan.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/users")
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping //Controller untuk register guest baru/data baru dari tamu yang memesan kamar
    public ResponseMessage<Users> registerUsers(@RequestBody @Valid UsersRequest model) {
        Users entity = modelMapper.map(model, Users.class);
        entity.setPhoto("Link Photo terbaru : wkaowkoakew");
        entity = usersService.save(entity);
        return ResponseMessage.success(entity);
    }

    @PutMapping("/{id}") //Controller untuk merubah data yaitu email dan password
    public ResponseMessage<Users> editUsersById(@PathVariable Integer id, @RequestBody @Valid UsersRequest model) {
        Users entity = usersService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        entity = usersService.save(entity);
        return ResponseMessage.success(entity);
    }

    @GetMapping("/{id}")
    public ResponseMessage<Users> findUserById(@PathVariable Integer id) {
        Users entity = usersService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }

    @PutMapping("/delete/{id}")
    public ResponseMessage<String> softDeleteUsersById(@PathVariable Integer id) {
        Users entity = usersService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        entity.setDeletedAt(LocalDateTime.now());
        entity = usersService.save(entity);
        return ResponseMessage.success("Successfully deleted user data");
    }


    @GetMapping()
    public ResponseMessage<PagedList<UsersResponse>> findAllUsersPagination(
            @Valid PageSearch pageSearch
    ) {
        Users search = new Users();
        Page<Users> entityPage = usersService.findAll(search, pageSearch.getPage(), pageSearch.getSize(),
                pageSearch.getSort());
        List<Users> entities = entityPage.toList();
        List<UsersResponse> responses = entities.stream() //Membuat response pagination dengan data yang ditampilkan hanya Email dan URL Photo
                .map(e -> modelMapper.map(e,UsersResponse.class))
                .collect(Collectors.toList());
        PagedList<UsersResponse> data = new PagedList<>(    //Berfungsi untuk mengeliminasi data" pada JSON yang ditampilkan agar lebih informatif
                responses,                                 // Dengan menyeleksi kebutuhan field data pada JSON yang diambil adalah page, size dan
                entityPage.getNumber(),                // total elements
                entityPage.getSize(),
                entityPage.getTotalElements());
        return ResponseMessage.success(data);
    }

}
