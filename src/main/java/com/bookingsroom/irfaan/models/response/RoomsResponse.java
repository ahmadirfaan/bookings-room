package com.bookingsroom.irfaan.models.response;

public class RoomsResponse {

    private String roomName;
    private String roomCapacity;
    private String photo;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(String roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "RoomsResponse{" +
                "roomName='" + roomName + '\'' +
                ", roomCapacity='" + roomCapacity + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
