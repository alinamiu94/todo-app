package org.example.model;

import org.example.enums.Status;

import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private int userId;
    private Date dueDate;
    private Status status;
    private Date createdAt;
    private Date finishedAt;

    public Task(int id, String name, String description, int userId, Date dueDate, Status status, Date createdAt, Date finishedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.dueDate = dueDate;
        this.status = status;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }

    public Task(String name, String description, int userId, Date dueDate, Status status, Date createdAt, Date finishedAt) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.dueDate = dueDate;
        this.status = status;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", finishedAt=" + finishedAt +
                '}';
    }
}
