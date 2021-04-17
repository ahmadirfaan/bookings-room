package com.bookingsroom.irfaan.controllers;

import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.exceptions.EntityNotFoundException;
import com.bookingsroom.irfaan.models.PageSearch;
import com.bookingsroom.irfaan.models.PagedList;
import com.bookingsroom.irfaan.models.ResponseMessage;
import com.bookingsroom.irfaan.models.request.RoomsRequest;
import com.bookingsroom.irfaan.models.response.RoomsResponse;
import com.bookingsroom.irfaan.services.RoomsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/rooms")
@RestController
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping //Controller mendaftarkan atau menambahkan data tentang ruangan
    public ResponseMessage<Rooms> registerRooms(@RequestBody @Valid RoomsRequest model) {
        Rooms entity = modelMapper.map(model, Rooms.class);
        entity.setPhoto("Link Photo terbaru : wkaowkoakew");
        entity = roomsService.save(entity);
        return ResponseMessage.success(entity);
    }

    @PutMapping("/{id}") //Controller untuk merubah data dari ruangan yaitu nama ruangan dan kapasitas dari ruangan
    public ResponseMessage<Rooms> editDataRoom(@PathVariable Integer id, @RequestBody @Valid RoomsRequest model) {
        Rooms entity = roomsService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        entity.setRoomName(model.getRoomName());
        entity.setRoomCapacity(model.getRoomCapacity());
        entity = roomsService.save(entity);
        return ResponseMessage.success(entity);
    }

    @GetMapping("/{id}")
    public ResponseMessage<Rooms> findRoomById(@PathVariable Integer id) {
        Rooms entity = roomsService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }

    @PutMapping("/delete/{id}")
    public ResponseMessage<String> softDeleteRoomById(@PathVariable Integer id) {
        Rooms entity = roomsService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        entity.setDeletedAt(LocalDateTime.now());
        entity = roomsService.save(entity);
        return ResponseMessage.success("Successfully deleted room data");
    }


    @GetMapping()
    public ResponseMessage<PagedList<RoomsResponse>> findAllRoomsPagination(
            @Valid PageSearch pageSearch
    ) {
        Rooms search = new Rooms();
        Page<Rooms> entityPage = roomsService.findAll(search, pageSearch.getPage(), pageSearch.getSize(),
                pageSearch.getSort());
        List<Rooms> entities = entityPage.toList();
        //Membuat response pagination dengan data yang ditampilkan hanya nama room, kapasitas room, dan url photo
        List<RoomsResponse> responses = entities.stream()
                .map(e -> modelMapper.map(e, RoomsResponse.class))
                .collect(Collectors.toList());
        PagedList<RoomsResponse> data = new PagedList<>(    //Berfungsi untuk mengeliminasi data" pada JSON yang ditampilkan agar lebih informatif
                responses,                                 // Dengan menyeleksi kebutuhan field data pada JSON yang diambil adalah page, size dan
                entityPage.getNumber(),                // total elements
                entityPage.getSize(),
                entityPage.getTotalElements());
        return ResponseMessage.success(data);
    }
}
