import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
}
