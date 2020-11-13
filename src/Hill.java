public class Hill extends Field {
    public Hill(int x, int y) {
        super(x, y);
    }

    @Override
    public int limitationOfTankOutside() {
        return 1;
    }

    @Override
    public int limitationOfSolderOutside() {
        return 1;
    }

    @Override
    public String toString() {
        return "HI";
    }
}
