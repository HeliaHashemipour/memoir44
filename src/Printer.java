/**
 * An interface to print colors
 * @author Hashemipour
 * @since 2020
 */
public interface Printer {

    String[] colors = {"\u001B[37m", "\u001B[30m"}; //White & Black
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    String AXIS = "\033[1;96m"; //Bright Cyan
    String ALLIED = "\u001B[31m";//Red
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    String RESET = "\u001B[0m"; //Reset

    static String getColor(int index) {
        return colors[index % 2];
    }
}
