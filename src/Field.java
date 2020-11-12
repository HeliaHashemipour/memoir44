public class Field {

    private final int x;
    private final int y;
    private Unit<Force> unit;

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

    public Unit<Force> getUnit() {
        return unit;
    }

    public void setUnit(Unit<Force> unit) {
        this.unit = unit;
    }

    public int limitationOfSolderInside() {
        return 0;
    }

    public int limitationOfTankInside() {
        return 0;
    }

    public int limitationOfArtilleryInside() {
        return 0;
    }

    public int limitationOfArtillaryOutside() {
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
        if (size == 0)
            unit = null;
    }

    public Class getType() {
        return unit.getType();
    }
}
