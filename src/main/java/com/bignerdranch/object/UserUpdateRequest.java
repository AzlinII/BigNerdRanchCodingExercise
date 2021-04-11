package com.bignerdranch.object;

public class UserUpdateRequest {

    private String email;
    private String expertise;

    public UserUpdateRequest(){};

    public UserUpdateRequest(String email, String expertise){
        this.email = email;
        this.expertise = expertise;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

}
