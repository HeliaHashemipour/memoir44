public interface Printer {

    String[] colors = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m",
            "\u001B[35m", "\u001B[36m", "\u001B[37m", };

    String RESET = "\u001B[0m";

    static String getColor(int index) {
        return colors[index % 7];
    }
}
