package com.bookingsroom.irfaan.services.impl;

import com.bookingsroom.irfaan.entities.Bookings;
import com.bookingsroom.irfaan.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.ZoneId;
import java.util.Date;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailCheckIn(Bookings booking) throws MessagingException {
        String email = booking.getUserId().getEmail();
        String bookingTime = booking.getBookingTime().toString().substring(0,10);
        String checkInTime = booking.getCheckInTime().toString().substring(0,10);
        String checkInHour = booking.getCheckInTime().toString().substring(11,16);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("vargoadventure@gmail.com");
        mimeMessageHelper.setSubject("Check in Pada Tanggal " + bookingTime);
        mimeMessageHelper.setTo(email);
        String content = "<h3>Anda Berhasil Check-In, Data Anda adalah sebagai berikut : </h3>" +
                "<p>" + "Kode Booking : <b>" + booking.getId() + "</b>" + "</p>" +
                "<p>" + "Email : <b>" + email + "</b>" + "</p>" +
                "<p>" + "Nama Ruangan : <b>" + booking.getRoomId().getRoomName() + "</b>" + "</p>" +
                "<p>" + "Waktu Check-In : <b>" + checkInTime + " Jam " + checkInHour + "</b>" + "</p>";
        mimeMessageHelper.setText(content, true);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailBookings(Bookings booking) throws MessagingException {
        String email = booking.getUserId().getEmail();
        String bookingTime = booking.getBookingTime().toString().substring(0,10);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("vargoadventure@gmail.com");
        mimeMessageHelper.setSubject("Selamat pesanan anda : dengan nomor " + booking.getId() + ", sudah dikonfirmasi");
        mimeMessageHelper.setTo(email);
        String content = "<h3>Data Pesanan Anda adalah sebagai berikut : </h3>" +
                "<p>" + "Kode Booking : <b>" + booking.getId() + "</b>" + "</p>" +
                "<p>" + "Email : <b>" + email + "</b>" + "</p>" +
                "<p>" + "Nama Ruangan : <b>" + booking.getRoomId().getRoomName() + "</b>" + "</p>" +
                "<p>" + "Tanggal Booking : <b>" + bookingTime + "</b>" + "</p>" +
                "<p>" + "Total Orang : <b>" + booking.getTotalPerson() + "</b>" + "</p>";
        mimeMessageHelper.setText(content, true);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailMustCheckIn(Bookings booking) throws MessagingException {
        String email = booking.getUserId().getEmail();
        String bookingTime = booking.getBookingTime().toString().substring(0,10);
        Date dateSentEmailNotification = Date.from(booking.getBookingTime().atZone(ZoneId.systemDefault()).toInstant());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("vargoadventure@gmail.com");
        mimeMessageHelper.setSubject("Pemberitahuan untuk Check-In dengan kode pesanana :  " + booking.getId());
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSentDate(dateSentEmailNotification); //Set Pengiriman Email sesuai dengan waktu pemesanan
        String content = "<h3>Data Pesanan Anda adalah sebagai berikut : </h3>" +
                "<p>" + "Kode Booking : <b>" + booking.getId() + "</b>" + "</p>" +
                "<p>" + "Email : <b>" + email + "</b>" + "</p>" +
                "<p>" + "Nama Ruangan : <b>" + booking.getRoomId().getRoomName() + "</b>" + "</p>" +
                "<p>" + "Tanggal Booking : <b>" + bookingTime + "</b>" + "</p>" +
                "<p>" + "Total Orang : <b>" + booking.getTotalPerson() + "</b>" + "</p>";
        mimeMessageHelper.setText(content, true);
        javaMailSender.send(mimeMessage);
    }


}
