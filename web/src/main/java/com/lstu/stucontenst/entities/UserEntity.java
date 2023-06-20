package com.lstu.stucontenst.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user")
@Table( name = "users" )
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "user_email", unique = true, nullable = false)
    protected String email;

    @Column(name = "user_password", nullable = false)
    protected String password;

    @OneToMany(targetEntity = TaskEntity.class)
    protected List<TaskEntity> tasks;

    public UserEntity() {
    }

    public UserEntity(String email, String password, List<TaskEntity> tasks) {
        this.email = email;
        this.password = password;
        this.tasks = tasks;
    }

    public UserEntity(Long id, String email, String password, List<TaskEntity> tasks) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.tasks = tasks;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
