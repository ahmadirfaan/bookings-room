package com.bookingsroom.irfaan.resolvers;

import com.bookingsroom.irfaan.entities.Bookings;
import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.services.BookingsService;
import com.bookingsroom.irfaan.services.RoomsService;
import com.bookingsroom.irfaan.services.UsersService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphqlQueryResolvers implements GraphQLQueryResolver {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private BookingsService bookingsService;

    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    public List<Rooms> getAllRooms() {
        return roomsService.findAll();
    }

    public List<Bookings> getAllBookings() {
        return bookingsService.findAll();
    }
}
