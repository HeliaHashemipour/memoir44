/**
 * Every instance of this class is a jungle on the game ground
 */
public class Jungle extends Field {

    /**
     * This is the constructor of this class
     * @param x
     * @param y
     */
    public Jungle(int x, int y) {
        super(x, y);
    }

    /**
     * this implements the limitations Tank that wants to attack this field
     * @return
     */
    @Override
    public int limitationOfTankOutside() {
        return 2;
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
        return "JU";
    }
}
