package ch.cern.todo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String taskName;

    private String taskDescription;

    private Instant deadline;

    public Task() {}

    public Task(String name, String description, Instant deadline) {
        this.taskName = name;
        this.taskDescription = description;
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Instant getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Task [taskId=" + id + ", taskName=" + taskName + ", taskDescription=" + taskDescription + "]";
    }

}
