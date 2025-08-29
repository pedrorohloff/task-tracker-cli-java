package tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskHandler {
    private final String filepath = "tasks.json";
    private List<Task> tasks;

    public TaskHandler() throws IOException {
        try {
            if(Files.exists(Paths.get(filepath))){
                tasks = TaskJsonReader.read(filepath);
            } else {
                tasks = new ArrayList<>();
                TaskJsonWriter.write(tasks, filepath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String description) throws IOException {
        tasks.add(new Task(description));
        TaskJsonWriter.write(tasks, filepath);
    }

    public void removeTask(int id) throws IOException {
        tasks.remove(id);
        TaskJsonWriter.write(tasks, filepath);
    }

    public void updateTask(int id, String description) throws IOException {
        tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresent(t -> {
                    t.setDescription(description);
                    t.setUpdatedAt(LocalDateTime.now());
                });
        TaskJsonWriter.write(tasks, filepath);
    }

    public void updateTask(int id, Status status) throws IOException {
        tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresent(t -> {
                    t.setStatus(status);
                    t.setUpdatedAt(LocalDateTime.now());
                });
        TaskJsonWriter.write(tasks, filepath);
    }

    public void listTasks() {
        tasks.forEach(System.out::println);
    }

    public void listTasks(Status status) {
        tasks.stream()
                .filter(t -> t.getStatus().equals(status))
                .forEach(System.out::println);
    }
}
