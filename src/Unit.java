import java.util.ArrayList;
import java.util.List;

public class Unit {

    private List<Force> forces;
    private Class type;
    private Team team;

    public Unit(Class type, Team team, Field field) {
        this.type = type;
        this.team = team;
        forces = new ArrayList<>();
        if (type == Tank.class) {
            if (team == Team.ALLIED) {
                for (int i = 0; i < 3; i++) {
                    Tank tank = new Tank();
                    tank.setField(field);
                    forces.add(tank);
                }
            } else if (team == Team.AXIS) {
                for (int i = 0; i < 4; i++) {
                    Tank tank = new Tank();
                    tank.setField(field);
                    forces.add(tank);
                }
            }
        } else if (type == Soldier.class) {
            for (int i = 0; i < 4; i++) {
                Soldier soldier = new Soldier();
                soldier.setField(field);
                forces.add(soldier);
            }
        } else if (type == Artillery.class) {
            for (int i = 0; i < 2; i++) {
                Artillery artillery = new Artillery();
                artillery.setField(field);
                forces.add(artillery);
            }
        }
        field.setUnit(this);
    }

    public List<Force> getForces() {
        return forces;
    }

    public int gotAttacked() {
        forces.remove(forces.size() - 1);
        return forces.size();
    }

    public Class getType() {
        return type;
    }

    public boolean canGotAttacked(List<Integer> dices) {
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

    @Override
    public String toString() {
        return forces.size() + (type == Soldier.class ? "S" :
                type == Tank.class ? "T" :
                        type == Artillery.class ? "A" : "");
    }
}
