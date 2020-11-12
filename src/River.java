public class River extends Field {
    public River(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isAvailable(Unit unit) {
        return false;
    }

    @Override
    public String toString() {
        return "RI";
    }
}
