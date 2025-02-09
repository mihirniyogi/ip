// package bob.command;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.io.IOException;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import bob.cli.Ui;
// import bob.task.TaskList;

// public class TodoCommandTest {

//     private int taskNumberAdded;

//     @BeforeEach
//     public void setUp() {
//         this.taskNumberAdded = TaskList.getCount() + 1;
//     }

//     @Test
//     public void testTodoCommand_validCommand_taskAdded() {
//         TodoCommand c = new TodoCommand("go for a run");
//         c.execute(new Ui());
//         assert TaskList.getCount() == taskNumberAdded;
//         String expected = String.format("[T][ ] %s", "go for a run");
//         String actual = TaskList.getTask(taskNumberAdded).toString();
//         assertEquals(expected, actual);
//     }

//     @AfterEach
//     public void tearDown() throws IOException {
//         TaskList.deleteTask(taskNumberAdded);
//     }
// }
