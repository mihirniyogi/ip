public class Helper {
  private static final String LINE = "____________________________________________________________";
  private static final String LOGO = """
                 ____     _____    ____
                |  _  \\  |     |  |  _  \\
                | |_| /  |  _  |  | |_| /
                |____/   | | | |  |____/      / \\/ \\
                |  _  \\  | |_| |  |  _  \\     \\    /
                | |_| /  |     |  | |_| /      \\  /
                |____/   |_____|  |____/        \\/
                """;
  
  public static void print(String... inputs) {
    System.out.println("\t" + LINE);
    for (String input: inputs) {
      if (input == null) {
        continue;
      }
      System.out.println("\t" + input);
    }
    System.out.println("\t" + LINE);
  }

  public static void printLogo() {
    System.out.println(LOGO);
  }

}
