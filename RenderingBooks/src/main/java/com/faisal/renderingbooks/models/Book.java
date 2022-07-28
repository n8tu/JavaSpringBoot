package com.faisal.renderingbooks.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="books")
public class Book {

    public Book(String title, String description, int numOfPages) {
        this.title = title;
        this.description = description;
        this.numOfPages = numOfPages;
    }

    public Book(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5 , max = 255)
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Min(100)
    private int numOfPages;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
