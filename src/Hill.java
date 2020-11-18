/**
 * Every instance of this field is a Hill on the game ground
 * @author Hashemipour
 * @since 2020
 */
public class Hill extends Field {

    /**
     * This is the constructor of this class
     * @param x
     * @param y
     */
    public Hill(int x, int y) {
        super(x, y);
    }

    /**
     * implements the limitation of Tank that wants to attack this field
     * @return
     */
    @Override
    public int limitationOfTankOutside() {
        return 1;
    }

    /**
     * implements the limitation of Soldier that wants to attack this field
     * @return
     */
    @Override
    public int limitationOfSolderOutside() {
        return 1;
    }

    /**
     * toString method of this class
     * @return
     */
    @Override
    public String toString() {
        return "HI";
    }
}
