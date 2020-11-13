import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GameArena {

    private static List<List<Field>> arena;

    public GameArena() {
        arena = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            List<Field> row = new ArrayList<>();
            for (int j = 0; j < (i % 2 == 0 ? 9 : 8); j++) {
                row.add(new Field(i, j));
            }
            arena.add(row);
        }
    }

    public static int getDistance(int oldX, int oldY, int newX, int newY) {
        int distance = 0;
        if (oldX % 2 == newX % 2) {
            distance = Math.abs(newX - oldX);
            if (newY <= oldY + distance / 2 && newY >= oldY - distance / 2) {
                return distance;
            } else if (newY > oldY + distance / 2) {
                distance += (newY - (oldY + distance / 2));
            } else {
                distance += ((oldY - distance / 2) - newY);
            }
        } else if (oldX % 2 == 0) {
            distance = Math.abs(oldX - newX);
            if (newY <= oldY + distance / 2 && newY >= oldY - (distance / 2 + 1)) {
                return distance;
            } else if (newY > oldY + distance / 2) {
                distance += newY - (oldY + distance / 2);
            } else {
                distance += (oldY - distance / 2 - 1) - newY;
            }
        } else {
            distance = Math.abs(oldX - newX);
            if (newY <= oldY + (distance / 2 + 1) && newY >= oldY - (distance / 2)) {
                return distance;
            } else if (newY > oldY + distance / 2 + 1) {
                distance += newY - (oldY + distance / 2 + 1);
            } else {
                distance += (oldY - distance / 2) - newY;
            }
        }
        return distance;
    }

    public static void printArena() {
        for (int i = 0; i < 7; i++) {
            System.out.print(Printer.getColor(2 * i) + "  * ** *          " + Printer.RESET);
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print(Printer.getColor(2 * i) + " *      *         " + Printer.RESET);
        }
        System.out.println();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 7; i++) {
                System.out.print(Printer.getColor(2 * i) + "*        * " + Printer.getColor(2 * i + 1) + (i < 6 ? "* ** * " : "") + Printer.RESET);
            }
            System.out.println();
            for (int i = 0; i < 7; i++) {
                System.out.print(Printer.getColor(2 * i) + " *      * " + Printer.getColor(2 * i + 1) + (i < 6 ? "*      *" : "") + Printer.RESET);
            }
            System.out.println();
            System.out.print(" ");
            for (int i = 0; i < 7; i++) {
                System.out.print(Printer.getColor(2 * i) + " * ** * " + Printer.getColor(2 * i + 1) + (i < 6 ? "*        *" : "") + Printer.RESET);
            }
            System.out.println();
            for (int i = 0; i < 7; i++) {
                System.out.print(Printer.getColor(2 * i) + "  * ** * " + Printer.getColor(2 * i + 1) + (i < 6 ? " *      *" : "") + Printer.RESET);
            }
            System.out.println();
            for (int i = 0; i < 7; i++) {
                System.out.print(Printer.getColor(2 * i) + " *      * " + Printer.getColor(2 * i + 1) + (i < 6 ? " * ** * " : "") + Printer.RESET);
            }
            System.out.println();
        }
        for (int i = 0; i < 7; i++) {
            System.out.print(Printer.getColor(2 * i) + "*        *        " + Printer.RESET);
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print(Printer.getColor(2 * i) + " *      *         " + Printer.RESET);
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print(Printer.getColor(2 * i) + "  * ** *          " + Printer.RESET);
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }

    public static Field getField(int x, int y) {
        return null;
    }

    private static boolean isValidCoordinate(int x, int y) {
        if (0 <= x && x <= 12) {
            if (x % 2 == 0) {
                return 0 <= y && y <= 8;
            } else {
                return 0 <= y && y <= 7;
            }
        }
        return false;
    }

    public static boolean isAvailable(int x, int y) {
        if (isValidCoordinate(x, y)) {
            Field field = arena.get(x).get(y);
            if (field.getUnit() != null) {
                System.out.println("There is other unit located on this coordination.");
            } else if (field instanceof River) {
                System.out.println("You can't move your unit towards river");
            } else {
                return true;
            }
        } else {
            System.out.println("Invalid coordinates!!");
        }
        return false;
    }

}
