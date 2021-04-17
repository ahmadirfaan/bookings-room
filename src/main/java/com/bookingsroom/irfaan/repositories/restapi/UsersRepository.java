package com.bookingsroom.irfaan.repositories.restapi;

import com.bookingsroom.irfaan.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
