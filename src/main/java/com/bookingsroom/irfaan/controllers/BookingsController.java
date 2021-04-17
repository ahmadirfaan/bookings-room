package com.bookingsroom.irfaan.controllers;

import com.bookingsroom.irfaan.entities.Bookings;
import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.exceptions.EntityNotFoundException;
import com.bookingsroom.irfaan.models.PageSearch;
import com.bookingsroom.irfaan.models.PagedList;
import com.bookingsroom.irfaan.models.ResponseMessage;
import com.bookingsroom.irfaan.models.request.BookingsRequest;
import com.bookingsroom.irfaan.models.response.BookingsResponse;
import com.bookingsroom.irfaan.services.BookingsService;
import com.bookingsroom.irfaan.services.MailService;
import com.bookingsroom.irfaan.services.RoomsService;
import com.bookingsroom.irfaan.services.UsersService;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/bookings")
@RestController
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailService mailService;

    //Format untuk parse dari string ke LocalDateTime adalah yyyy--MM--DD HH:mm
    //yyyy-MM-dd menandakan tahun-bulan-hari
    //HH:mm menandakan jam:menit
    private final DateTimeFormatter formatterBooking = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();


    @PostMapping //Controller membuat transaksi booking
    public ResponseMessage<BookingsResponse> addBookings(@RequestBody @Valid BookingsRequest model) throws ParseException {
        Users user = usersService.findById(model.getUserId());
        Rooms room = roomsService.findById(model.getRoomId());
        if (model.getTotalPerson() > room.getRoomCapacity()) {
            return ResponseMessage.error(200, "Insufficient Room Capacity", null);
        }
        LocalDateTime bookingDate = LocalDateTime.parse(model.getBookingTime(), formatterBooking);
        Bookings entity = new Bookings();
        entity.setUserId(user);
        entity.setRoomId(room);
        entity.setTotalPerson(model.getTotalPerson());
        entity.setBookingTime(bookingDate);
        entity = bookingsService.save(entity);
        BookingsResponse data = modelMapper.map(entity, BookingsResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}") //Controller untuk merubah data bookings pada database
    public ResponseMessage<BookingsResponse> editDataBooking(@PathVariable Integer id, @RequestBody @Valid BookingsRequest model) throws ParseException, MessagingException {
        Bookings entity = bookingsService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        Users user = usersService.findById(model.getUserId());
        Rooms room = roomsService.findById(model.getRoomId());
        if (model.getTotalPerson() > room.getRoomCapacity()) {
            return ResponseMessage.error(200, "Insufficient Room Capacity", null);
        }
        LocalDateTime bookingDate = LocalDateTime.parse(model.getBookingTime(), formatterBooking);
        entity.setUserId(user);
        entity.setRoomId(room);
        entity.setTotalPerson(model.getTotalPerson());
        entity.setBookingTime(bookingDate);
        entity = bookingsService.save(entity);
        mailService.sendEmailBookings(entity);
        mailService.sendEmailMustCheckIn(entity); //Mengirim email ketika tanggal system sesuai dengan tanggal booking-nya
        BookingsResponse data = modelMapper.map(entity, BookingsResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<BookingsResponse> findBookingbyId(@PathVariable Integer id) {
        Bookings entity = bookingsService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        BookingsResponse data = modelMapper.map(entity, BookingsResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/delete/{id}")
    public ResponseMessage<String> softDeleteBookingsById(@PathVariable Integer id) {
        Bookings entity = bookingsService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        entity.setDeletedAt(LocalDateTime.now());
        entity = bookingsService.save(entity);
        return ResponseMessage.success("Successfully deleted booking");
    }

    @PutMapping("/{id}/check-in")
    //Merupakan suatu API yang berfungsi untuk mengupdate database check-in sesuai dengan id bookings
    public ResponseMessage<BookingsResponse> guestCheckIn(@PathVariable Integer id) throws MessagingException {
        Bookings entity = bookingsService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        entity.setCheckInTime(LocalDateTime.now());
        entity = bookingsService.save(entity);

        //Send Email check in pada alamat email Guest
        mailService.sendEmailCheckIn(entity);

        BookingsResponse data = modelMapper.map(entity, BookingsResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}/check-out")
    //Merupakan suatu API yang berfungsi untuk mengupdate database check-out sesuai dengan id bookings
    public ResponseMessage<BookingsResponse> guestCheckOut(@PathVariable Integer id) {
        Bookings entity = bookingsService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        entity.setCheckOutTime(LocalDateTime.now());
        entity = bookingsService.save(entity);
        BookingsResponse data = modelMapper.map(entity, BookingsResponse.class);
        return ResponseMessage.success(data);
    }


    @GetMapping()
    public ResponseMessage<PagedList<BookingsResponse>> findAllBookingsPagination(
            @Valid PageSearch pageSearch
    ) {
        Bookings search = new Bookings();
        Page<Bookings> entityPage = bookingsService.findAll(search, pageSearch.getPage(), pageSearch.getSize(),
                pageSearch.getSort());
        List<Bookings> entities = entityPage.toList();
        List<BookingsResponse> responses = entities.stream()
                .map(e -> modelMapper.map(e, BookingsResponse.class))
                .collect(Collectors.toList());
        PagedList<BookingsResponse> data = new PagedList<>(    //Berfungsi untuk mengeliminasi data" pada JSON yang ditampilkan agar lebih informatif
                responses,                                 // Dengan menyeleksi kebutuhan field data pada JSON yang diambil adalah page, size dan
                entityPage.getNumber(),                // total elements
                entityPage.getSize(),
                entityPage.getTotalElements());
        return ResponseMessage.success(data);
    }
}
