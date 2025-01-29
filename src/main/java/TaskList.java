import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private static final String FILE_NAME = "tasks.csv";
    private static final Path FILE_PATH = Paths.get(System.getProperty("user.home"), FILE_NAME);
    
    public static void createFileIfNotExist() {
        if (!Files.exists(FILE_PATH)) {
            try {
                Files.createFile(FILE_PATH);
                String header = "ID,Type,Description,Done,By,From,To,\n";
                Files.write(FILE_PATH, header.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    public static List<Task> readTasksFromFile() {
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
}
