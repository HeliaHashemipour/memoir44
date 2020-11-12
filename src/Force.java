public interface Force {

    boolean move();
    void attack();

    default void moveUp() {}
    default void moveDown() {}
    default void moveUpRight() {}
    default void moveUpLeft() {}
    default void moveDownLeft() {}
    default void moveDownRight() {}

}
