/**
 * An interface to print colors
 */
public interface Printer {

    String[] colors = {"\u001B[37m", "\u001B[30m"};

    String AXIS = "\u001B[32m";
    String ALLIED = "\u001B[31m";

    String RESET = "\u001B[0m";

    static String getColor(int index) {
        return colors[index % 2];
    }
}
