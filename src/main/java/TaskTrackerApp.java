import tasks.Status;
import tasks.TaskHandler;

import java.io.IOException;

public class TaskTrackerApp {
    public static void run(String[] args) throws IOException {
        TaskHandler taskHandler = new TaskHandler();

        switch (args[0].toLowerCase()) {
            case "add" -> taskHandler.addTask(args[1]);
            case "update" -> taskHandler.updateTask(Integer.parseInt(args[1]), args[2]);
            case "delete" -> taskHandler.removeTask(Integer.parseInt(args[1]));
            case "mark-in-progress" -> taskHandler.updateTask(Integer.parseInt(args[1]), Status.IN_PROGRESS);
            case "mark-done" -> taskHandler.updateTask(Integer.parseInt(args[1]), Status.DONE);
            case "list" -> {
                if (args.length > 1) {
                    if (args[1].equalsIgnoreCase("done")) taskHandler.listTasks(Status.DONE);
                    else if (args[1].equalsIgnoreCase("in-progress")) taskHandler.listTasks(Status.IN_PROGRESS);
                    else if (args[1].equalsIgnoreCase("todo")) taskHandler.listTasks(Status.TODO);
                } else {
                    taskHandler.listTasks();
                }
            }
            default -> System.out.println("Insert a valid command. Valid commands:\n" +
                    "list, list <status>, update <id> <description>, delete <id>, add <description>, " +
                    "mark-in-progress <id>, mark-done <id>.");
        }
    }
}
