import java.util.Objects;

/**
 * Every instance of this class is a normal field on the ground
 * @author Hashemipour
 * @since 2020
 */
public class Field {

    // This field contains the x of coordination of this field on the ground
    private final int x;

    // This field contains the y of coordination of this field on the ground
    private final int y;

    // This field contains the unit that stays on this field
    private Unit unit;

    /**
     * The constructor of this class that gets coordination of this class and sets it
     * @param x
     * @param y
     */
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter method of x
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * getter method of y
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * getter method of the unit
     * @return
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * setter method of the unit
     * @param unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * limitation of Tank inside of a field
     * @return
     */
    public int limitationOfTankInside() {
        return 0;
    }

    /**
     * limitation of Tank outside of the field
     * @return
     */
    public int limitationOfTankOutside() {
        return 0;
    }

    /**
     * limitation of solder outside of a field
     * @return
     */
    public int limitationOfSolderOutside() {
        return 0;
    }

    /**
     * This method will be called when this field gets attacked
     */
    public void gotAttacked() {
        int size = unit.gotAttacked();
        if (size == 0) {
            Team team = unit.getTeam();
            if (team == Team.AXIS) {
                Allied.scored();
            } else {
                Axis.scored();
            }
        }
    }

    /**
     * The getter method of type
     * @return
     */
    public Class getType() {
        return unit.getType();
    }

    /**
     * show if this field is available or not
     * @param unit
     * @return
     */
    public boolean isAvailable(Unit unit) {
        return true;
    }

    /**
     * equal method of this class
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return x == field.x &&
                y == field.y;
    }

    /**
     * the hashcode of this class
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * toString method of this class
     * @return
     */
    @Override
    public String toString() {
        return "  ";
    }
}
