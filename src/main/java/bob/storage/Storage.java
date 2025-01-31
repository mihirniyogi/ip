package bob.storage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

public class Storage {
    private static final String FILE_NAME = "tasks.csv";
    private static final Path FILE_PATH = Paths.get(System.getProperty("user.home"), FILE_NAME);
    private static final String HEADER = "id,type,description,done,by,from,to";

    static {
        createFileIfNotExist();
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
                    : LocalDateTime.parse(byString);
            return new Deadline(description, by, done);
        case "E":
            String fromString = fields[5];
            String toString = fields[6];
            LocalDateTime from = fromString.isBlank() 
                    ? null 
                    : LocalDateTime.parse(fromString);
            LocalDateTime to = toString.isBlank() 
                    ? null 
                    : LocalDateTime.parse(toString);
            return new Event(description, from, to, done);
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

    public static List<Task> fetchTasksFromFile() {
        try {
            List<Task> tasks = Files.lines(FILE_PATH)
                    .skip(1)
                    .map(Storage::convertLineToTask)
                    .collect(Collectors.toList());
            return tasks;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return List.of();
        }
    }
    
    public static void saveTasksToFile(List<Task> tasks) throws IOException {
        String lines = Stream.concat(Stream.of(HEADER), 
                tasks
                    .stream()
                    .map(task -> (tasks.indexOf(task) + 1) + "," + task.toCsv()))
                .collect(Collectors.joining("\n"));
        Files.write(FILE_PATH, lines.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
    }
}
