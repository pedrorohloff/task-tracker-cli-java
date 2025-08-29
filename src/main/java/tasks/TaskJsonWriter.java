package tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TaskJsonWriter {
    public static void write(List<Task> tasks, String filepath) throws IOException {
        String jsonArray = tasks.stream()
                .map(Task::toJson)
                .collect(Collectors.joining(",", "[", "]"));

        Files.writeString(Paths.get(filepath), jsonArray);
    }
}
