import java.io.IOException;

public class Bob {
    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.printWelcome();

        while (true) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(ui);
                
            } catch (WrongCommandException e) {
                ui.print(e.getMessage(), "Please try again!");
            } catch (IndexOutOfBoundsException e) {
                ui.print("Uh oh! Bob says...I'm sorry, there is no such task :(");
            } catch (NumberFormatException e) {
                ui.print("Uh oh! Bob says...I'm sorry, there is no such task :(");
            } catch (IOException e) {
                ui.print("Uh oh! Bob says...I'm sorry, there was an error saving the task :(");
            }   
        }
    }
}