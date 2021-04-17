package com.bookingsroom.irfaan.services.impl;

import com.bookingsroom.irfaan.entities.Rooms;
import com.bookingsroom.irfaan.repositories.restapi.RoomsRepository;
import com.bookingsroom.irfaan.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomsServiceImpl extends AbstractService<Rooms, Integer> implements RoomsService {

    @Autowired
    public RoomsServiceImpl(RoomsRepository repository) {
        super(repository);
    }
}
