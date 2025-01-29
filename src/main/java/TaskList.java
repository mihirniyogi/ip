import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskList {
    private static final String FILE_NAME = "tasks.csv";
    private static final Path FILE_PATH = Paths.get(System.getProperty("user.home"), FILE_NAME);
    private static final String HEADER = "id,type,description,done,by,from,to";

    private static List<Task> tasks;

    static {
        createFileIfNotExist();
        tasks = readTasksFromFile();
    }

    private static void createFileIfNotExist() {
        if (!Files.exists(FILE_PATH)) {
            try {
                Files.createFile(FILE_PATH);
                
                Files.write(FILE_PATH, HEADER.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    private static List<Task> readTasksFromFile() {
        try {
            List<Task> tasks = Files.lines(FILE_PATH)
                    .skip(1)
                    .map(TaskList::convertLineToTask)
                    .collect(Collectors.toList());
            return tasks;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return List.of();
        }
    }

    private static Task convertLineToTask(String line) throws IllegalArgumentException {
        String[] fields = line.split(",");
        String type = fields[1];
        String description = fields[2];
        boolean done = Boolean.parseBoolean(fields[3]);

        switch (type) {
        case "T":
            return new Todo(description, done);
        case "D":
            String byString = fields[4];
            LocalDateTime by = byString.isBlank() 
                    ? null 
                    : LocalDateTime.parse(byString, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            return new Deadline(description, by, done);
        case "E":
            String fromString = fields[5];
            String toString = fields[6];
            LocalDateTime from = fromString.isBlank() 
                    ? null 
                    : LocalDateTime.parse(fromString, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            LocalDateTime to = toString.isBlank() 
                    ? null 
                    : LocalDateTime.parse(toString, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            return new Event(description, from, to, done);
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

    private static void saveTasksToFile() throws IOException {
        List<Task> copyTasks = List.copyOf(tasks);
        try {
            String lines = Stream.concat(Stream.of(HEADER), 
                    tasks
                        .stream()
                        .map(task -> (tasks.indexOf(task) + 1) + "," + task.toCsv()))
                    .collect(Collectors.joining("\n"));
            Files.write(FILE_PATH, lines.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            // if operation fails, revert to previous version of tasks
            tasks = copyTasks;            
            throw e;
        }
    }

    public static void printTasks() {
        if (tasks.isEmpty()) {
            Helper.print("No tasks yet!");
            return;
        }
        int n = tasks.size();
        String[] taskStrings = new String[n];
        for (int i = 0; i < n; i++) {
            taskStrings[i] = (i + 1) + ". " + tasks.get(i).toString();
        }
        Helper.print("Here are your tasks:", String.join("\n\t", taskStrings));
    }

    public static int getCount() {
        return tasks.size();
    }

    public static Task getTask(int number) {
        return tasks.get(number - 1);
    }

    public static void addTask(Task task) throws IOException{
        tasks.add(task);
        saveTasksToFile();
    }

    public static void deleteTask(int number) throws IOException {
        tasks.remove(number - 1);
        saveTasksToFile();
    }

    public static void markTask(int number) throws IOException {
        tasks.get(number - 1).mark();
        saveTasksToFile();
    }

    public static void unmarkTask(int number) throws IOException {
        tasks.get(number - 1).unmark();
        saveTasksToFile();
    }
}
