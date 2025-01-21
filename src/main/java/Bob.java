public class Bob {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = """
                         ____     _____    ____
                        |  _  \\\s |     |  |  _  \\
                        | |_| /  |  _  |  | |_| /      
                        |____/   | | | |  |____/      / \\/ \\
                        |  _  \\\s | |_| |  |  _  \\     \\    /
                        | |_| /  |     |  | |_| /      \\  /
                        |____/   |_____|  |____/        \\/
                        """;

        System.out.println(logo);
        System.out.println(line + "\nHello! I'm Bob ♥ \nWhat can I do for you?");
        System.out.println(line + "\nThank you and goodbye!\n" + line);
    }
}
