package com.bookingsroom.irfaan.services.impl;

import com.bookingsroom.irfaan.entities.Bookings;
import com.bookingsroom.irfaan.repositories.restapi.BookingsRepository;
import com.bookingsroom.irfaan.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingsServiceImpl extends AbstractService<Bookings,Integer> implements BookingsService {

    @Autowired
    public BookingsServiceImpl(BookingsRepository repository) {
        super(repository);
    }

}
