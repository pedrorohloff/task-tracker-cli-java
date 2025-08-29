package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final int id;
    private String description;
    private Status status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private static int counter = 1;

    public Task(String description) {
        this.id = counter++;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Task(String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = counter++;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toJson() {
        return String.format("{\"id\":%d,\"description\":\"%s\",\"status\":\"%s\",\"createdAt\":%s,\"updatedAt\":%s}",
                id,
                escapeString(description),
                status,
                createdAt.toString(),
                updatedAt.toString());
    }

    public String escapeString(String s) {
        if (s == null) return null;
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Task\n{" +
                "\nid=" + id +
                "\n, description='" + description + '\'' +
                "\n, status=" + status +
                "\n, createdAt=" + createdAt.format(formatter) +
                "\n, updatedAt=" + updatedAt.format(formatter) +
                "\n}";
    }
}
