package com.bookingsroom.irfaan.services;

import com.bookingsroom.irfaan.entities.Bookings;

import javax.mail.MessagingException;

public interface MailService {

    void sendEmailCheckIn(Bookings booking) throws MessagingException;
    void sendEmailBookings(Bookings booking) throws MessagingException;
    void sendEmailMustCheckIn(Bookings booking) throws MessagingException;
}
