import java.util.Scanner;

public interface Reader {

    Scanner scanner = new Scanner(System.in);

    static String input() {
        return scanner.nextLine();
    }

    static String input(String message) {
        System.out.print(message);
        return input();
    }

    static int integerInput() {
        try {
            return Integer.parseInt(input());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return integerInput();
        }
    }

    static int integerInput(String message) {
        System.out.print(message);
        return integerInput();
    }
}
