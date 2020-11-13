public class Jungle extends Field {
    public Jungle(int x, int y) {
        super(x, y);
    }

    @Override
    public int limitationOfTankOutside() {
        return 2;
    }

    @Override
    public int limitationOfSolderOutside() {
        return 1;
    }

    @Override
    public String toString() {
        return "JU";
    }
}
