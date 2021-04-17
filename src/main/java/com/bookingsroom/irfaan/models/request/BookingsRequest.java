package com.bookingsroom.irfaan.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookingsRequest {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer roomId;

    @NotNull
    private Integer totalPerson;

    @NotBlank
    private String bookingTime;

    @NotBlank
    private String noted;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getTotalPerson() {
        return totalPerson;
    }

    public void setTotalPerson(Integer totalPerson) {
        this.totalPerson = totalPerson;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getNoted() {
        return noted;
    }

    public void setNoted(String noted) {
        this.noted = noted;
    }

    @Override
    public String toString() {
        return "BookingsRequest{" +
                "userId=" + userId +
                ", roomId=" + roomId +
                ", totalPerson=" + totalPerson +
                ", bookingTime='" + bookingTime + '\'' +
                ", noted='" + noted + '\'' +
                '}';
    }
}
