package com.faisal.projectmanager.models;

import javax.validation.constraints.*;

public class Login {

    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @NotEmpty(message = "Email is required")
    @Email(message = "Enter a valid email!")
    private String email;

    @NotEmpty(message = "password is required")
    @Size(min = 8 , max = 128,message = "password must be at least 8 characters")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
