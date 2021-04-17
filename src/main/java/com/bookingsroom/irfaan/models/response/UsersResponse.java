package com.bookingsroom.irfaan.models.response;

public class UsersResponse {

    private String email;
    private String photo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UsersResponse{" +
                "email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
