import java.util.List;

public class Unit<T> {

    private List<T> forces;
    private Class type;
    private Team team;

    public Unit(Class obj, Team team) {

    }

    public List<T> getForces() {
        return forces;
    }

    public int gotAttacked() {
        forces.remove(forces.size() - 1);
        return forces.size();
    }

    public Class getType() {
        return type;
    }

    public boolean canGotAttacked(int ... dices) {
        if (type == Soldier.class) {
            for (int dice : dices) {
                if (dice == 1 || dice == 6 || dice == 5)
                    return true;
            }
        } else if (type == Tank.class) {
            for (int dice : dices) {
                if (dice == 2 || dice == 5)
                    return true;
            }
        } else if (type == Artillery.class) {
            for (int dice : dices) {
                if (dice == 5)
                    return true;
            }
        }
        return false;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
