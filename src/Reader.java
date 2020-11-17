import java.util.Scanner;

/**
 * An interface to get inputs from players
 */
public interface Reader {

    Scanner scanner = new Scanner(System.in);

    /**
     * this method returns what player entered on command line
     * @return
     */
    static String input() {
        return scanner.nextLine();
    }

    /**
     * Exactly the same method as above
     * but it just prints a message before getting input
     * @param message
     * @return
     */
    static String input(String message) {
        System.out.print(message);
        return input();
    }

    /**
     * This method gets an integer from user
     * @return
     */
    static int integerInput() {
        try {
            return Integer.parseInt(input());
        } catch (Exception e) {
            System.out.println("Invalid input!");
            return integerInput();
        }
    }

    /**
     * before getting input prints a message
     * @param message
     * @return
     */
    static int integerInput(String message) {
        System.out.print(message);
        return integerInput();
    }
}
