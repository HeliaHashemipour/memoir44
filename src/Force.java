public interface Force {

    boolean move();
    void attack();

    default void moveUp() {
        System.out.println("movedup");
    }
    default void moveDown() {
        System.out.println("movedDown");
    }
    default void moveUpRight() {
        System.out.println("movedUpRight");
    }
    default void moveUpLeft() {
        System.out.println("movedUpLeft");
    }
    default void moveDownLeft() {
        System.out.println("movedDownLeft");
    }
    default void moveDownRight() {
        System.out.println("movedDownRight");
    }
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
}
