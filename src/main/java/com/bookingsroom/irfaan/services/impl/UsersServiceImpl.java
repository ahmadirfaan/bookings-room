package com.bookingsroom.irfaan.services.impl;

import com.bookingsroom.irfaan.entities.Users;
import com.bookingsroom.irfaan.repositories.restapi.UsersRepository;
import com.bookingsroom.irfaan.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends AbstractService<Users,Integer> implements UsersService {

    @Autowired
    public UsersServiceImpl(UsersRepository repository) {
        super(repository);
    }
}
