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

    /**
     * Create and add a Task to the list of Tasks tasks.
     * @param description Description of the Task.
     * @throws IOException Thrown if any problem occur while saving to file.
     */
    public void addTask(String description) throws IOException {
        tasks.add(new Task(description));
        TaskJsonWriter.write(tasks, filepath);
    }

    /**
     * Search and deletes a Task by id from the list of Tasks.
     * @param id Id of the Task.
     * @throws IOException Thrown if any problem occur while saving to file.
     */
    public void removeTask(int id) throws IOException {
        tasks.remove(id);
        TaskJsonWriter.write(tasks, filepath);
    }

    /**
     * Search and update a Task by id in the list of Tasks.
     * @param id Id of the Task.
     * @param description Description of the Task.
     * @throws IOException Thrown if any problem occur while saving to file.
     */
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

    /**
     * Search and update a Task by id in the list of Tasks.
     * @param id Id of the Task.
     * @param status Status of the Task.
     * @throws IOException Thrown if any problem occur while saving to file.
     */
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

    /**
     * List all content of the list of Tasks.
     */
    public void listTasks() {
        tasks.forEach(System.out::println);
    }

    /**
     * List all Tasks with matching status of {@code status}.
     * @param status Status of the Task.
     */
    public void listTasks(Status status) {
        tasks.stream()
                .filter(t -> t.getStatus().equals(status))
                .forEach(System.out::println);
    }
}
