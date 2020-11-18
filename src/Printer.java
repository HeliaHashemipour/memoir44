/**
 * An interface to print colors
 * @author Hashemipour
 * @since 2020
 */
public interface Printer {

    String[] colors = {"\u001B[37m", "\u001B[30m"}; //White & Black

    String AXIS = "\033[1;96m"; //Bright Cyan
    String ALLIED = "\u001B[31m";//Red

    String RESET = "\u001B[0m"; //Reset

    static String getColor(int index) {
        return colors[index % 2];
    }
}
