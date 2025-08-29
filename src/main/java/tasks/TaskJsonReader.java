package tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskJsonReader {

    /**
     * Reads the designated file and save its content to the list of Tasks.
     * @param filepath Path to json file.
     * @return List of Tasks.
     */
    public static List<Task> read(String filepath) {
        try {
            String content = Files.readString(Paths.get(filepath));
            List<Task> tasks = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\{.*?\\}");
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                String jsonObject = matcher.group();
                tasks.add(parseTaskObject(jsonObject));
            }
            return tasks;
        } catch (IOException e) {
            System.err.println("Cannot read file.");
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Parse the content of the {@code jsonObject} to a Task.
     * @param jsonObject Json object that stores the content of a Task.
     * @return Returns a Task object.
     */
    private static Task parseTaskObject(String jsonObject) {
        int id = Integer.parseInt(Objects.requireNonNull(extractValue(jsonObject, "id")));
        String description = extractValue(jsonObject, "description");
        Status status = Status.valueOf(extractValue(jsonObject, "status"));
        LocalDateTime createdAt = LocalDateTime.parse(Objects.requireNonNull(extractValue(jsonObject, "createdAt")));
        LocalDateTime updatedAt = LocalDateTime.parse(Objects.requireNonNull(extractValue(jsonObject, "updatedAt")));

        return new Task(description, status, createdAt, updatedAt);
    }

    /**
     * Uses Pattern matching to extract the value of a key in a Json like String.
     * @param json String that holds the content of a json file.
     * @param key Key of the value to be found.
     * @return Returns the value of the given key. If key or value doesn't exist, returns null.
     */
    private static String extractValue(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\":\"?(.*?)\"?[,}]");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
