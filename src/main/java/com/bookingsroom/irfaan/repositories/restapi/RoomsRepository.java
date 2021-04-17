package com.bookingsroom.irfaan.repositories.restapi;

import com.bookingsroom.irfaan.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
}
