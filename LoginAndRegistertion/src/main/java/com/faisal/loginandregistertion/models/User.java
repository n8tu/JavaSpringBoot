package com.faisal.loginandregistertion.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name="user")
public class User {

    public User() {
    }

    public User(String username, String email, String password, String confirm_password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z]*" , message = "username has to be letters only")
    @Size(min = 3,message = "User name must be at least 3 characters")
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Please write a valid Email format")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8,message = "Password must be at least 8 characters")
    private String password;

    @Transient
    @NotEmpty(message = "Confirm password is required")
    @Size(min = 8,message = "Confirm password must be at least 8 characters and Match the password")
    private String confirm_password;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd:hh:mm:ss")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd:hh:mm:ss")
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
