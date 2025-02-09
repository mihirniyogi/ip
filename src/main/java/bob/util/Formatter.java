package bob.util;

import java.util.List;

import bob.task.Task;

public class Formatter {
    
    public static String format(String... inputs) {
        StringBuilder output = new StringBuilder();
        for (String input: inputs) {
            if (input == null) {
                continue;
            }
            output.append(input).append("\n");
        }
        output.setLength(output.length() - 1);
        return output.toString();
    }

    public static String formatTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks yet!";
        }

        int n = tasks.size();
        String[] taskStrings = new String[n];
        for (int i = 0; i < n; i++) {
            taskStrings[i] = (i + 1) + ". " + tasks.get(i).toString();
        }

        return format("Here are your tasks:", String.join("\n", taskStrings));
    }
}
