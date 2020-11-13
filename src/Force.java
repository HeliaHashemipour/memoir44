import java.util.List;

public interface Force {

    boolean move();

    void attack();

    default boolean[] moveUp() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x, y + 1)) {
            Field destination = GameArena.getField(x, y + 1);
            destination.setUnit(unit);
            field.setUnit(null);
            List<Force> forces = unit.getForces();
            for (Force force : forces) {
                force.setField(destination);
            }
            if (destination instanceof Jungle || destination instanceof Village) {
                return new boolean[]{true, false};
            }
            return new boolean[]{true, true};
        }
        return new boolean[]{false, false};
    }

    default boolean[] moveDown() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x, y - 1)) {
            Field destination = GameArena.getField(x, y - 1);
            destination.setUnit(unit);
            field.setUnit(null);
            List<Force> forces = unit.getForces();
            for (Force force : forces) {
                force.setField(destination);
            }
            if (destination instanceof Jungle || destination instanceof Village)
                return new boolean[]{true, false};
            return new boolean[]{true, true};
        }
        return new boolean[]{false, false};
    }

    default boolean[] moveUpRight() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x + 1, x % 2 == 0 ? y : y + 1)) {
            Field destination = GameArena.getField(x + 1, x % 2 == 0 ? y : y + 1);
            destination.setUnit(unit);
            field.setUnit(null);
            for (Force force : unit.getForces()) {
                force.setField(destination);
            }
            if (destination instanceof Jungle || destination instanceof Village)
                return new boolean[]{true, false};
            return new boolean[]{true, true};
        }
        return new boolean[]{false, false};
    }

    default boolean[] moveUpLeft() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x - 1, x % 2 == 0 ? y : y + 1)) {
            Field destination = GameArena.getField(x - 1, x % 2 == 0 ? y : y + 1);
            destination.setUnit(unit);
            field.setUnit(null);
            for (Force force : unit.getForces()) {
                force.setField(destination);
            }
            if (destination instanceof Jungle || destination instanceof Village)
                return new boolean[]{true, false};
            return new boolean[]{true, true};
        }
        return new boolean[]{false, false};
    }

    default boolean[] moveDownLeft() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x - 1, x % 2 == 0 ? y - 1 : y)) {
            Field destination = GameArena.getField(x - 1, x % 2 == 0 ? y - 1 : y);
            destination.setUnit(unit);
            field.setUnit(null);
            for (Force force : unit.getForces()) {
                force.setField(destination);
            }
            if (destination instanceof Jungle || destination instanceof Village)
                return new boolean[]{true, false};
            return new boolean[]{true, true};
        }
        return new boolean[]{false, false};
    }

    default boolean[] moveDownRight() {
        Field field = getField();
        int x = field.getX();
        int y = field.getY();
        Unit unit = field.getUnit();
        if (GameArena.isAvailable(x - 1, x % 2 == 0 ? y - 1 : y)) {
            Field destination = GameArena.getField(x + 1, x % 2 == 0 ? y - 1 : y);
            destination.setUnit(unit);
            field.setUnit(null);
            for (Force force : unit.getForces()) {
                force.setField(destination);
            }
            if (destination instanceof Jungle || destination instanceof Village)
                return new boolean[]{true, false};
            return new boolean[]{true, true};
        }
        return new boolean[]{false, false};
    }

    default boolean isValidInput(int amount, String... inputs) {
        boolean isValid = true;
        for (String sample : inputs) {
            isValid = isValid && sample.matches("[1-3][UD][RL]?");
        }

        if (isValid) {
            int sum = 0;
            for (String sample : inputs) {
                sum += sample.charAt(0) - '0';
            }
            return sum == amount;
        }
        return false;
    }

    Field getField();

    void setField(Field field);

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
}
