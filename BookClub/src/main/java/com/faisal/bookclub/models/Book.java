package com.faisal.bookclub.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="books")
public class Book {

    public Book() {
    }

    public Book(String title, String author, String thoughts, User owner, User borrow) {
        this.title = title;
        this.author = author;
        this.thoughts = thoughts;
        this.owner = owner;
        this.borrow = borrow;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Author is required")
    private String author;

    @Column(length = 3000)
    @NotEmpty(message = "Thoughts is required")
    private String thoughts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrowed_id")
    private User borrow;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getBorrow() {
        return borrow;
    }

    public void setBorrow(User borrow) {
        this.borrow = borrow;
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
