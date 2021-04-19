package com.bookingsroom.irfaan.resolvers;

import com.bookingsroom.irfaan.entities.Bookings;
import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.exceptions.EntityNotFoundException;
import com.bookingsroom.irfaan.services.BookingsService;
import com.bookingsroom.irfaan.services.RoomsService;
import com.bookingsroom.irfaan.services.UsersService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@Component
public class GraphQLMutationResolvers implements GraphQLMutationResolver {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private BookingsService bookingsService;

    private final DateTimeFormatter formatterBooking = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    public Users createUser(String email, String password) {
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(password);
        user = usersService.save(user);
        return user;
    }

    public Rooms createRoom(String roomName, Integer roomCapacity) {
        Rooms room = new Rooms();
        room.setRoomName(roomName);
        room.setRoomCapacity(roomCapacity);
        room = roomsService.save(room);
        return room;
    }

    public Bookings createBooking(Integer userId, Integer roomId, Integer totalPerson, String bookingTime, String noted) {
        Bookings bookings = new Bookings();
        Users users = usersService.findById(userId);
        Rooms rooms = roomsService.findById(roomId);
        if (users == null || rooms == null) {
            throw new EntityNotFoundException();
        }
        LocalDateTime bookingDate = LocalDateTime.parse(bookingTime, formatterBooking);
        bookings.setRoomId(rooms);
        bookings.setUserId(users);
        bookings.setTotalPerson(totalPerson);
        bookings.setBookingTime(bookingDate);
        bookings.setNoted(noted);
        bookings = bookingsService.save(bookings);
        return bookings;
    }

    public Users updateUser(Integer id, String email, String password) {
        Users user = usersService.findById(id);
        if (user == null) {
            throw new EntityNotFoundException();
        }
        user.setEmail(email);
        user.setPassword(password);
        user = usersService.save(user);
        return user;
    }

    public Rooms updateRoom(Integer id, String roomName, Integer roomCapacity) {
        Rooms room = roomsService.findById(id);
        if (room == null) {
            throw new EntityNotFoundException();
        }
        room.setRoomName(roomName);
        room.setRoomCapacity(roomCapacity);
        room = roomsService.save(room);
        return room;
    }

    public Bookings updateBooking(Integer id, Integer userId, Integer roomId, Integer totalPerson, String bookingTime, String noted) {
        Bookings bookings = bookingsService.findById(id);
        Users users = usersService.findById(userId);
        Rooms rooms = roomsService.findById(roomId);
        if (users == null || rooms == null) {
            throw new EntityNotFoundException();
        }
        LocalDateTime bookingDate = LocalDateTime.parse(bookingTime, formatterBooking);
        bookings.setRoomId(rooms);
        bookings.setUserId(users);
        bookings.setTotalPerson(totalPerson);
        bookings.setBookingTime(bookingDate);
        bookings.setNoted(noted);
        bookings = bookingsService.save(bookings);
        return bookings;
    }

    public Users getUserById(Integer id) {
        return usersService.findById(id);
    }

    public Rooms getRoomById(Integer id) {
        return roomsService.findById(id);
    }

    public Bookings getBookingById(Integer id) {
        return bookingsService.findById(id);
    }

    public Users softDeleteUserById(Integer id) {
        Users user = usersService.findById(id);
        if(user == null) {
            throw new EntityNotFoundException();
        }
        user.setDeletedAt(LocalDateTime.now());
        user = usersService.save(user);
        return user;
    }

    public Rooms softDeleteRoomById(Integer id) {
        Rooms room = roomsService.findById(id);
        if(room == null) {
            throw new EntityNotFoundException();
        }
        room.setDeletedAt(LocalDateTime.now());
        room = roomsService.save(room);
        return room;
    }

    public Bookings softDeleteBookingById(Integer id) {
        Bookings booking = bookingsService.findById(id);
        if(booking == null) {
            throw new EntityNotFoundException();
        }
        booking.setDeletedAt(LocalDateTime.now());
        booking = bookingsService.save(booking);
        return booking;
    }
}
