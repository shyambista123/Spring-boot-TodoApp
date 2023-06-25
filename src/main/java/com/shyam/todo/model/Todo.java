package com.shyam.todo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "todo_app")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Date date;

    public Todo(Long id, String title, String description, boolean completed, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.date = date;
    }

    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isCompleted() {
        return completed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}