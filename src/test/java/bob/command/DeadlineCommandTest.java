// package bob.command;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import java.io.IOException;
// import java.time.LocalDateTime;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import bob.cli.Ui;
// import bob.task.TaskList;

// public class DeadlineCommandTest {

//     private int taskNumberAdded;

//     @BeforeEach
//     public void setUp() {
//         this.taskNumberAdded = TaskList.getCount() + 1;
//     }

//     @Test
//     public void testDeadlineCommand_validCommand_taskAdded() {
//         DeadlineCommand c = new DeadlineCommand("Problem Set 1", LocalDateTime.of(2025, 3, 31, 23, 59));
//         c.execute(new Ui());
//         assert TaskList.getCount() == taskNumberAdded;
//         String expected = String.format("[D][ ] %s", "Problem Set 1 (by: Mar 31 2025 11:59pm)");
//         String actual = TaskList.getTask(taskNumberAdded).toString();
//         assertEquals(expected, actual);
//     }

//     @AfterEach
//     public void tearDown() throws IOException {
//         TaskList.deleteTask(taskNumberAdded);
//     }
// }
