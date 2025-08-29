package tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TaskJsonWriter {
    /**
     * Write content of a list of Task to a json like file.
     * @param tasks List of Tasks.
     * @param filepath Path to file.
     * @throws IOException Thrown if any problem occur while saving to file.
     */
    public static void write(List<Task> tasks, String filepath) throws IOException {
        String jsonArray = tasks.stream()
                .map(Task::toJson)
                .collect(Collectors.joining(",", "[", "]"));

        Files.writeString(Paths.get(filepath), jsonArray);
    }
}
