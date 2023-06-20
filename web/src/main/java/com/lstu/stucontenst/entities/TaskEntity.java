package com.lstu.stucontenst.entities;


import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.List;

@Entity(name = "task")
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "task_name", nullable = false)
    protected String name;

    @Column(name = "task_desc")
    protected String description;

    @Column(name = "task_is_done")
    protected Boolean done;

    @ManyToOne(targetEntity = UserEntity.class, optional = false)
    protected UserEntity user;

    public TaskEntity() {}

    public TaskEntity(String name, String description, Boolean done, UserEntity user) {
        this.name = name;
        this.description = description;
        this.done = done;
        this.user = user;
    }

    public TaskEntity(Long id, String name, String description, Boolean done, UserEntity user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.done = done;
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
