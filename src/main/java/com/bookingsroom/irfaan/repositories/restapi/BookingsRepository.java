package com.bookingsroom.irfaan.repositories.restapi;

import com.bookingsroom.irfaan.entities.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {
}
