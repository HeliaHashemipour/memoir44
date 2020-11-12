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
}
