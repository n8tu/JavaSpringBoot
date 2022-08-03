package com.faisal.projectmanager.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    public User() {
    }

    public User(String first_name, String last_name, String email, String password,
                String confirm_password, List<Project> lead_projects,
                List<Project> joined_projects) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.lead_projects = lead_projects;
        this.joined_projects = joined_projects;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First Name is required")
    @Pattern(regexp = "^[a-zA-Z]*" , message = "First Name has to be letters only")
    @Size(min = 3,message = "First Name must be at least 3 characters")
    private String first_name;

    @NotEmpty(message = "Last Name is required")
    @Pattern(regexp = "^[a-zA-Z]*" , message = "Last Name has to be letters only")
    @Size(min = 3,message = "Last Name must be at least 3 characters")
    private String last_name;

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

    @OneToMany(mappedBy = "leader" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Project> lead_projects;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_projects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> joined_projects;

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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public List<Project> getLead_projects() {
        return lead_projects;
    }

    public void setLead_projects(List<Project> lead_projects) {
        this.lead_projects = lead_projects;
    }

    public List<Project> getJoined_projects() {
        return joined_projects;
    }

    public void setJoined_projects(List<Project> joined_projects) {
        this.joined_projects = joined_projects;
    }
}
