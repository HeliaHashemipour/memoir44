/**
 * every instance of this class is village on the game ground
 */
public class Village extends Field {

    /**
     * The constructor of this class
     * @param x
     * @param y
     */
    public Village(int x, int y) {
        super(x, y);
    }

    /**
     * limitation of tank that wants to attack this field
     * @return
     */
    @Override
    public int limitationOfTankOutside() {
        return 2;
    }

    /**
     * limitation of solder that wants to attack this field
     * @return
     */
    @Override
    public int limitationOfSolderOutside() {
        return 1;
    }

    /**
     * the limitation of tank that wants to attack from this field
     * @return
     */
    @Override
    public int limitationOfTankInside() {
        return 2;
    }

    /**
     * toString method of this class
     * @return
     */
    @Override
    public String toString() {
        return "VI";
    }
}
