/**
 * Every instance of this class is an asylum in ground
 * this class also  contains the limitations of asylums
 * @author Hashemipour
 * @since 2020
 */
public class Asylum extends Field {

    /**
     * Constructor of this class that sets it's coordination
     * @param x
     * @param y
     */
    public Asylum(int x, int y) {
        super(x, y);
    }

    /**
     * Overrides the limitations of tank and returns how much dices should this player takes less
     * @return
     */
    @Override
    public int limitationOfTankOutside() {
        return getUnit().getTeam() == Team.AXIS ? 2 : 0;
    }

    /**
     * Overrides the limitations of solder
     * @return
     */
    @Override
    public int limitationOfSolderOutside() {
        return getUnit().getTeam() == Team.AXIS ? 1 : 0;
    }

    /**
     * This method shows if this field is available for it's unit or not
     * @param unit
     * @return
     */
    @Override
    public boolean isAvailable(Unit unit) {
        if (unit.getType() == Tank.class) {
            System.out.println("Tank can't get inside of asylum!");
            return false;
        } else if (unit.getType() == Artillery.class) {
            System.out.println("Artillery can't get inside of asylum!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * toString method of this class.
     * @return
     */
    @Override
    public String toString() {
        return "AS";
    }
}
