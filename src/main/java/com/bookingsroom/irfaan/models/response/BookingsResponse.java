package com.bookingsroom.irfaan.models.response;

import java.time.LocalDateTime;
import java.util.Date;

public class BookingsResponse {

    private UsersResponse userId;

    private RoomsResponse roomId;

    private Integer totalPerson;

    private LocalDateTime bookingTime;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    public UsersResponse getUserId() {
        return userId;
    }

    public void setUserId(UsersResponse userId) {
        this.userId = userId;
    }

    public RoomsResponse getRoomId() {
        return roomId;
    }

    public void setRoomId(RoomsResponse roomId) {
        this.roomId = roomId;
    }

    public Integer getTotalPerson() {
        return totalPerson;
    }

    public void setTotalPerson(Integer totalPerson) {
        this.totalPerson = totalPerson;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    @Override
    public String toString() {
        return "BookingsResponse{" +
                "userId=" + userId +
                ", roomId=" + roomId +
                ", totalPerson=" + totalPerson +
                ", bookingTime=" + bookingTime +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                '}';
    }
}
