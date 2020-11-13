public class Asylum extends Field {

    public Asylum(int x, int y) {
        super(x, y);
    }

    @Override
    public int limitationOfTankOutside() {
        return getUnit().getTeam() == Team.AXIS ? 2 : 0;
    }

    @Override
    public int limitationOfSolderOutside() {
        return getUnit().getTeam() == Team.AXIS ? 1 : 0;
    }

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

    @Override
    public String toString() {
        return "AS";
    }
}
