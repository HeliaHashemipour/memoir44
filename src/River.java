/**
 * Every instance of this class is a River on the ground
 */
public class River extends Field {

    /**
     * This is the constructor of this class
     * @param x
     * @param y
     */
    public River(int x, int y) {
        super(x, y);
    }

    /**
     * this method shows if this field is available to move on it or not
     * @param unit
     * @return
     */
    @Override
    public boolean isAvailable(Unit unit) {
        return false;
    }

    /**
     * toString method of this class
     * @return
     */
    @Override
    public String toString() {
        return "RI";
    }
}
