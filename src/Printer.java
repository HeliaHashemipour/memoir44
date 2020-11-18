/**
 * An interface to print colors
 * @author Hashemipour
 * @since 2020
 */
public interface Printer {

    String[] colors = {"\u001B[37m", "\u001B[30m"}; //White & Black

    String AXIS = "\u001B[33m"; // Yellow
    String ALLIED = "\u001B[31m";//Red

    String RESET = "\u001B[0m"; //Reset

    static String getColor(int index) {
        return colors[index % 2];
    }
}
