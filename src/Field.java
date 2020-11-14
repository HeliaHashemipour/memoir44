import java.util.Objects;

public class Field {

    private final int x;
    private final int y;
    private Unit unit;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int limitationOfTankInside() {
        return 0;
    }

    public int limitationOfTankOutside() {
        return 0;
    }

    public int limitationOfSolderOutside() {
        return 0;
    }

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

    public Class getType() {
        return unit.getType();
    }

    public boolean isAvailable(Unit unit) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return x == field.x &&
                y == field.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "  ";
    }
}
