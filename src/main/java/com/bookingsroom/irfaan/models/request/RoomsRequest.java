package com.bookingsroom.irfaan.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RoomsRequest {

    @NotBlank
    private String roomName;

    @NotNull
    private Integer roomCapacity;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    @Override
    public String toString() {
        return "RoomsRequest{" +
                "roomName='" + roomName + '\'' +
                ", roomCapacity='" + roomCapacity + '\'' +
                '}';
    }
}
