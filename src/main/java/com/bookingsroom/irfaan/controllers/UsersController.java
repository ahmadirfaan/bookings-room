package com.bookingsroom.irfaan.controllers;

import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.exceptions.EntityNotFoundException;
import com.bookingsroom.irfaan.models.PageSearch;
import com.bookingsroom.irfaan.models.PagedList;
import com.bookingsroom.irfaan.models.ResponseMessage;
import com.bookingsroom.irfaan.models.request.UsersRequest;
import com.bookingsroom.irfaan.models.response.UsersResponse;
import com.bookingsroom.irfaan.services.FileService;
import com.bookingsroom.irfaan.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/users")
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping //Controller untuk register guest baru/data baru dari tamu yang memesan kamar
    public ResponseMessage<Users> registerUsers(@RequestBody @Valid UsersRequest model, @Valid MultipartFile file) throws IOException {
        Users entity = modelMapper.map(model, Users.class);
        entity = usersService.save(entity);
        return ResponseMessage.success(entity);
    }

    @PostMapping("/upload/{id}")
    public ResponseMessage uploadPhotoUser(@PathVariable Integer id, @Valid MultipartFile file) throws IOException {
        Users entity = usersService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        try {
            String fileName = fileService.save(file);
            String imageUrl = fileService.getImageUrl(fileName);
            entity.setPhoto(imageUrl);
            entity = usersService.save(entity);
            return new ResponseMessage(200,"Success Upload File", "url file : " + imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error(404,"Upload File Not Success");
        }

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
