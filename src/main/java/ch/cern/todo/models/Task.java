package ch.cern.todo.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String taskName;

    private String taskDescription;

    @Column(columnDefinition = "DATE")
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Task() {}

    public Task(String name, String description, LocalDate deadline, Category category, User user) {
        this.taskName = name;
        this.taskDescription = description;
        this.deadline = deadline;
        this.user = user;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Task [taskId=" + id + ", taskName=" + taskName + ", taskDescription=" + taskDescription + "]";
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
