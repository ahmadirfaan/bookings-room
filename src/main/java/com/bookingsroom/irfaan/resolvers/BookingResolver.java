package com.bookingsroom.irfaan.resolvers;

import com.bookingsroom.irfaan.entities.Bookings;
import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.repositories.restapi.RoomsRepository;
import com.bookingsroom.irfaan.repositories.restapi.UsersRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.awt.print.Book;

@Component
public class BookingResolver implements GraphQLResolver<Bookings> {

    private UsersRepository usersRepository;

    private RoomsRepository roomsRepository;

    public BookingResolver(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getUser(Bookings bookings) {
        return usersRepository.findById(bookings.getUserId().getId()).get();
    }

    public Rooms getRoom(Bookings bookings) {
        return roomsRepository.findById(bookings.getRoomId().getId()).get();
    }
}
