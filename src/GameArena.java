import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the game arena of the game
 * @author Hashemipour
 * @since 2020
 */
public class GameArena {

    // this field is the arena of the game
    private static final List<List<Field>> arena;

    // static block that sets fields and forces on arena
    static {
        arena = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            List<Field> row = new ArrayList<>();
            for (int j = 0; j < (i % 2 == 0 ? 9 : 8); j++) {
                row.add(new Field(i, j));
            }
            arena.add(row);
        }

        List<Field> temp = arena.get(0);
        temp.remove(8);
        temp.add(8, new Hill(0, 8));
        temp.remove(5);
        temp.add(5, new River(0, 5));
        temp.remove(6);
        temp.add(6, new Asylum(0, 6));

        temp = arena.get(1);
        temp.remove(7);
        temp.add(7, new Hill(1, 7));
        temp.remove(5);
        temp.add(5, new Bridge(1, 5));
        temp.remove(4);
        temp.add(4, new Jungle(1, 4));

        temp = arena.get(2);
        temp.remove(6);
        temp.add(6, new River(2, 6));
        temp.remove(7);
        temp.add(6, new River(2, 7));
        temp.remove(2);
        temp.add(2, new Asylum(2, 2));
        temp.remove(5);
        temp.add(5, new Jungle(2, 5));

        temp = arena.get(3);
        temp.remove(6);
        temp.add(6, new River(3, 6));
        temp.remove(7);
        temp.add(7, new Village(3, 7));

        temp = arena.get(4);
        temp.remove(7);
        temp.add(7, new River(4, 7));
        temp.remove(8);
        temp.add(8, new Bridge(4, 8));
        temp.remove(1);
        temp.add(1, new Jungle(4, 1));
        temp.remove(3);
        temp.add(1, new Jungle(4, 3));
        temp.remove(5);
        temp.add(1, new Jungle(4, 5));

        temp = arena.get(5);
        temp.remove(3);
        temp.add(3, new Hill(5, 3));
        temp.remove(6);
        temp.add(6, new Village(5, 6));
        temp.remove(1);
        temp.add(1, new Jungle(5, 1));

        temp = arena.get(6);
        temp.remove(3);
        temp.add(3, new Hill(6, 3));

        temp = arena.get(7);
        temp.remove(5);
        temp.add(5, new Hill(7, 5));
        temp.remove(6);
        temp.add(6, new Hill(7, 6));
        temp.remove(3);
        temp.add(3, new Asylum(7, 3));

        temp = arena.get(8);
        temp.remove(2);
        temp.add(2, new Jungle(8, 2));

        temp = arena.get(9);
        temp.remove(2);
        temp.add(2, new Jungle(9, 2));
        temp.remove(4);
        temp.add(4, new Jungle(9, 4));

        temp = arena.get(10);
        temp.remove(4);
        temp.add(4, new Asylum(10, 4));
        temp.remove(2);
        temp.add(2, new Jungle(10, 2));
        temp.remove(7);
        temp.add(7, new Jungle(10, 7));

        temp = arena.get(11);
        temp.remove(0);
        temp.add(0, new Asylum(11, 0));
        temp.remove(3);
        temp.add(3, new Hill(11, 3));
        temp.remove(4);
        temp.add(4, new Hill(11, 4));

        temp = arena.get(12);
        for (int i = 4; i < 8; i++) {
            temp.remove(i);
            temp.add(i, new Jungle(12, i));
        }

        new Unit(Tank.class, Team.ALLIED, arena.get(0).get(0));
        new Unit(Tank.class, Team.ALLIED, arena.get(1).get(0));
        new Unit(Tank.class, Team.ALLIED, arena.get(12).get(0));

        new Unit(Soldier.class, Team.ALLIED, arena.get(0).get(1));
        new Unit(Soldier.class, Team.ALLIED, arena.get(1).get(4));
        new Unit(Soldier.class, Team.ALLIED, arena.get(4).get(3));
        new Unit(Soldier.class, Team.ALLIED, arena.get(7).get(3));
        new Unit(Soldier.class, Team.ALLIED, arena.get(8).get(2));
        new Unit(Soldier.class, Team.ALLIED, arena.get(9).get(4));
        new Unit(Soldier.class, Team.ALLIED, arena.get(10).get(1));
        new Unit(Soldier.class, Team.ALLIED, arena.get(11).get(4));
        new Unit(Soldier.class, Team.ALLIED, arena.get(0).get(8));

        new Unit(Artillery.class, Team.ALLIED, arena.get(1).get(1));
        new Unit(Artillery.class, Team.ALLIED, arena.get(6).get(1));

        new Unit(Soldier.class, Team.AXIS, arena.get(1).get(7));
        new Unit(Soldier.class, Team.AXIS, arena.get(2).get(8));
        new Unit(Soldier.class, Team.AXIS, arena.get(5).get(7));
        new Unit(Soldier.class, Team.AXIS, arena.get(8).get(7));
        new Unit(Soldier.class, Team.AXIS, arena.get(9).get(7));
        new Unit(Soldier.class, Team.AXIS, arena.get(10).get(8));
        new Unit(Soldier.class, Team.AXIS, arena.get(12).get(8));

        new Unit(Tank.class, Team.AXIS, arena.get(0).get(8));
        new Unit(Tank.class, Team.AXIS, arena.get(6).get(8));
        new Unit(Tank.class, Team.AXIS, arena.get(6).get(7));
        new Unit(Tank.class, Team.AXIS, arena.get(8).get(8));
        new Unit(Tank.class, Team.AXIS, arena.get(11).get(7));
        new Unit(Tank.class, Team.AXIS, arena.get(11).get(6));

    }

    /**
     * this method gets two coordination and returns their distance on the ground
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return
     */
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

    /**
     * This method prints the arena
     */
    public static void printArena() {
        for (int i = 0; i < 7; i++) {
            System.out.print((arena.get(2 * i).get(8).getUnit() == null ? Printer.getColor(2 * i) :
                    arena.get(2 * i).get(8).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED)
                    + "  * ** *          " + Printer.RESET);
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.printf((arena.get(2 * i).get(8).getUnit() == null ? Printer.getColor(2 * i) :
                    arena.get(2 * i).get(8).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED)
                    + " *  %s  *         " + Printer.RESET, arena.get(2 * i).get(8));
        }
        System.out.println();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 7; i++) {
                System.out.print((arena.get(2 * i).get(8 - j).getUnit() == null ? Printer.getColor(2 * i) :
                        arena.get(2 * i).get(8 - j).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED)
                        + String.format("* (%s,%s) * ", (2 * i < 10 ? "0" + 2 * i : 2 * i), 8 - j)
                        + (i < 6 ? arena.get(2 * i + 1).get(8 - j - 1).getUnit() == null ? Printer.getColor(2 * i + 1) :
                        arena.get(2 * i + 1).get(8 - j - 1).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED : "")
                        + (i < 6 ? "* ** * " : "") + Printer.RESET);
            }
            System.out.println();
            for (int i = 0; i < 7; i++) {
                System.out.print((arena.get(2 * i).get(8 - j).getUnit() == null ? Printer.getColor(2 * i) :
                        arena.get(2 * i).get(8 - j).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED) + String.format(" *  %s  * " ,
                        arena.get(2 * i).get(8 - j).getUnit() == null ? "  " : arena.get(2 * i).get(8 - j).getUnit())
                        + (i < 6 ? arena.get(2 * i + 1).get(8 - j - 1).getUnit() == null ? Printer.getColor(2 * i + 1) :
                        arena.get(2 * i + 1).get(8 - j - 1).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED : "")
                        + (i < 6 ? String.format("*  %s  *", arena.get(2 * i + 1).get(8 - j - 1)) : "") + Printer.RESET);
            }
            System.out.println();
            System.out.print(" ");
            for (int i = 0; i < 7; i++) {
                System.out.print((arena.get(2 * i).get(8 - j).getUnit() == null ? Printer.getColor(2 * i) :
                        arena.get(2 * i).get(8 - j).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED)
                        + " * ** * " + (i < 6 ? arena.get(2 * i + 1).get(8 - j - 1).getUnit() == null ? Printer.getColor(2 * i + 1) :
                        arena.get(2 * i + 1).get(8 - j - 1).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED : "")
                        + (i < 6 ? String.format("* (%s,%s) *", (2 * i + 1 < 10 ? "0" + (2 * i + 1) : 2 * i + 1), 8 - j - 1) : "") + Printer.RESET);
            }
            System.out.println();
            for (int i = 0; i < 7; i++) {
                System.out.print((arena.get(2 * i).get(8 - j - 1).getUnit() == null ? Printer.getColor(2 * i) :
                        arena.get(2 * i).get(8 - j - 1).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED)
                        + "  * ** * " + (i < 6 ? arena.get(2 * i + 1).get(8 - j - 1).getUnit() == null ? Printer.getColor(2 * i + 1) :
                        arena.get(2 * i + 1).get(8 - j - 1).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED : "")
                        + (i < 6 ? String.format(" *  %s  *", arena.get(2 * i + 1).get(8 - j - 1).getUnit() == null ? "  " : arena.get(2 * i + 1).get(8 - j - 1).getUnit()) : "") + Printer.RESET);
            }
            System.out.println();
            for (int i = 0; i < 7; i++) {
                System.out.print((arena.get(2 * i).get(8 - j - 1).getUnit() == null ? Printer.getColor(2 * i) :
                        arena.get(2 * i).get(8 - j - 1).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED)
                        + String.format(" *  %s  * ", arena.get(2 * i).get(8 - j - 1))
                        + (i < 6 ? arena.get(2 * i + 1).get(8 - j - 1).getUnit() == null ? Printer.getColor(2 * i + 1) :
                        arena.get(2 * i + 1).get(8 - j - 1).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED : "")
                        + (i < 6 ? " * ** * " : "") + Printer.RESET);
            }
            System.out.println();
        }
        for (int i = 0; i < 7; i++) {
            System.out.printf((arena.get(2 * i).get(0).getUnit() == null ? Printer.getColor(2 * i) :
                            arena.get(2 * i).get(0).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED)
                            + "* (%s,%s) *        " + Printer.RESET,
                    (2 * i < 10 ? "0" + 2 * i : 2 * i), "0");
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.printf((arena.get(2 * i).get(0).getUnit() == null ? Printer.getColor(2 * i) :
                            arena.get(2 * i).get(0).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED)
                            + " *  %s  *         " + Printer.RESET,
                    arena.get(2 * i).get(0).getUnit() == null ? "  " : arena.get(2 * i).get(0).getUnit());
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print((arena.get(2 * i).get(0).getUnit() == null ? Printer.getColor(2 * i) :
                    arena.get(2 * i).get(0).getUnit().getTeam() == Team.AXIS ? Printer.AXIS : Printer.ALLIED)
                    + "  * ** *          " + Printer.RESET);
        }
        System.out.println();
    }

    /**
     * This method gets a coordination and returns the field on that ground
     * @param x
     * @param y
     * @return
     */
    public static Field getField(int x, int y) {
        return arena.get(x).get(y);
    }

    /**
     * this method gets a coordination and returns true if it's valid
     * @param x
     * @param y
     * @return
     */
    public static boolean isValidCoordinate(int x, int y) {
        if (0 <= x && x <= 12) {
            if (x % 2 == 0) {
                return 0 <= y && y <= 8;
            } else {
                return 0 <= y && y <= 7;
            }
        }
        return false;
    }

    /**
     * This method shows if a coordination is available or not
     * @param x
     * @param y
     * @return
     */
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
