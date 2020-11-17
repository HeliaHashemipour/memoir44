import java.util.List;

/**
 * Force interface that every instance of this class is a attacking force of each team
 */
public interface Force {

    /**
     * The move method that will be called when we want to move a force
     * @return
     */
    boolean move();

    /**
     * The attack method that will be called when we want to attack with a force
     */
    void attack();

    /**
     * A unit moves up when this method is called on one of it's Force
     * @return
     */
    default boolean[] moveUp() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x, y + 1)) {
            Field destination = GameArena.getField(x, y + 1);
            return getBooleanss(field, unit, destination);
        }
        return new boolean[]{false, false};
    }

    /**
     * A unit moves down when this method is called on one of it's Force
     * @return
     */
    default boolean[] moveDown() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x, y - 1)) {
            Field destination = GameArena.getField(x, y - 1);
            return getBooleanss(field, unit, destination);
        }
        return new boolean[] {false, false};
    }

    private boolean[] getBooleanss(Field field, Unit unit, Field destination) {
        if (!destination.isAvailable(unit))
            return new boolean[] {false, false};
        destination.setUnit(unit);
        field.setUnit(null);
        List<Force> forces = unit.getForces();
        for (Force force : forces) {
            force.setField(destination);
        }
        if (destination instanceof Jungle || destination instanceof Village)
            return new boolean[] {true, false};
        return new boolean[] {true, true};
    }

    /**
     * A unit moves up right when this method is called on one of it's Force
     * @return
     */
    default boolean[] moveUpRight() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x + 1, x % 2 == 0 ? y : y + 1)) {
            Field destination = GameArena.getField(x + 1, x % 2 == 0 ? y : y + 1);
            return getBooleans(field, unit, destination);
        }
        return new boolean[] {false, false};
    }

    /**
     * A unit moves up left when this method is called on one of it's Force
     * @return
     */
    default boolean[] moveUpLeft() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x - 1, x % 2 == 0 ? y : y + 1)) {
            Field destination = GameArena.getField(x - 1, x % 2 == 0 ? y : y + 1);
            return getBooleans(field, unit, destination);
        }
        return new boolean[] {false, false};
    }

    /**
     * A unit moves down left when this method is called on one of it's Force
     * @return
     */
    default boolean[] moveDownLeft() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x - 1, x % 2 == 0 ? y - 1 : y)) {
            Field destination = GameArena.getField(x - 1, x % 2 == 0 ? y - 1 : y);
            return getBooleans(field, unit, destination);
        }
        return new boolean[] {false, false};
    }

    /**
     * A unit moves down right when this method is called on one of it's Force
     * @return
     */
    default boolean[] moveDownRight() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x - 1, x % 2 == 0 ? y - 1 : y)) {
            Field destination = GameArena.getField(x + 1, x % 2 == 0 ? y - 1 : y);
            return getBooleans(field, unit, destination);
        }
        return new boolean[] {false, false};
    }

    private boolean[] getBooleans(Field field, Unit unit, Field destination) {
        if (!destination.isAvailable(unit))
            return new boolean[] {false, false};
        destination.setUnit(unit);
        field.setUnit(null);
        for (Force force : unit.getForces()) {
            force.setField(destination);
        }
        if (destination instanceof Jungle || destination instanceof Village)
            return new boolean[] {true, false};
        if (unit.getTeam() == Team.AXIS && destination.getX() == 11 && destination.getY() == 0) {
            Axis.scored();
        }
        if (unit.getTeam() == Team.ALLIED && destination.getX() == 0 && destination.getY() == 6) {
            Allied.scored();
        }
        return new boolean[] {true, true};
    }

    /**
     * This method returns true if inputs of user is valid
     * @param amount
     * @param inputs
     * @return
     */
    default boolean isValidInput(int amount, String ... inputs) {
        boolean isValid = true;
        for (String sample : inputs) {
            isValid = isValid && sample.matches("[1-3][UD][RL]?");
        }

        if (isValid){
            int sum = 0;
            for (String sample: inputs) {
                sum += sample.charAt(0) - '0';
            }
            return sum == amount;
        }
        return false;
    }

    /**
     * The getter method of field
     * @return
     */
    Field getField();

    /**
     * setter method of field
     * @param field
     */
    void setField(Field field);

    /**
     * It's an refactored method that we'll use in move method
     * @param move
     * @return
     */
    default boolean[] switchCaseOfOneMove(String move) {
        boolean[] array = null;
        switch (move) {
            case "U":
                array = moveUp();

                break;
            case "D":
                array = moveDown();

                break;
            case "UR":
                array = moveUpRight();

                break;
            case "UL":
                array = moveUpLeft();

                break;
            case "DR":
                array = moveDownRight();

                break;
            case "DL":
                array = moveDownLeft();

                break;
            default:
                System.out.println("Invalid input!");

        }
        return array;
    }
    default boolean[] switchCaseOfMoreMove(String move, String amount) {
        boolean[] array = null;
        boolean continuing = true;
        switchMovement : switch (move) {
            case "U":
                for (int i = 0; i < Integer.parseInt(amount) && continuing; i++) {
                    array = moveUp();
                    if (array[0]) {
                        continuing = array[1];
                    } else {
                        break switchMovement;
                    }
                }
                break;
            case "D":
                for (int i = 0; i < Integer.parseInt(amount) && continuing; i++) {
                    array = moveDown();
                    if (array[0]) {
                        continuing = array[1];
                    } else {
                        break switchMovement;
                    }
                }
                break;
            case "UR":
                for (int i = 0; i < Integer.parseInt(amount) && continuing; i++) {
                    array = moveUpRight();
                    if (array[0]) {
                        continuing = array[1];
                    } else {
                        break switchMovement;
                    }
                }
                break;
            case "UL":
                for (int i = 0; i < Integer.parseInt(amount) && continuing; i++) {
                    array = moveUpLeft();
                    if (array[0]) {
                        continuing = array[1];
                    } else {
                        break switchMovement;
                    }
                }
                break;
            case "DR":
                for (int i = 0; i < Integer.parseInt(amount) && continuing; i++) {
                    array = moveDownRight();
                    if (array[0]) {
                        continuing = array[1];
                    } else {
                        break switchMovement;
                    }
                }
                break;
            case "DL":
                for (int i = 0; i < Integer.parseInt(amount) && continuing; i++) {
                    array = moveDownLeft();
                    if (array[0]) {
                        continuing = array[1];
                    } else {
                        break switchMovement;
                    }
                }
                break;
            default:
                System.out.println("Invalid input!");
        }
        return array;
    }

}
