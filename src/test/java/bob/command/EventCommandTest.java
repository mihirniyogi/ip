package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import bob.task.TaskList;
import bob.ui.Ui;

public class EventCommandTest {

    private int taskNumberAdded;

    @BeforeEach
    public void setUp() {
        this.taskNumberAdded = TaskList.getCount() + 1;
    }

    @Test
    public void testEventCommand_validCommand_taskAdded() {
        EventCommand c = new EventCommand("Study Session @ CLB", 
                LocalDateTime.of(2025, 3, 15, 14, 00), 
                LocalDateTime.of(2025, 3, 15, 16, 00));
        c.execute(new Ui());
        assert TaskList.getCount() == taskNumberAdded;
        String expected = String.format("[E][ ] %s", "Study Session @ CLB (from: Mar 15 2025 2:00pm to: Mar 15 2025 4:00pm)");
        String actual = TaskList.getTask(taskNumberAdded).toString();
        assertEquals(expected, actual);
    }

    @AfterEach
    public void tearDown() throws IOException {
        TaskList.deleteTask(taskNumberAdded);
    }
}
