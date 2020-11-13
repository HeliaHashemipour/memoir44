public class Village extends Field {
    public Village(int x, int y) {
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
    public int limitationOfTankInside() {
        return 2;
    }
}
