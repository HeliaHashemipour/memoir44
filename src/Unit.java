import java.util.ArrayList;
import java.util.List;

/**
 * The class of units that have some Forces on itself
 */
public class Unit {

    // this field contains the forces
    private List<Force> forces;

    // This field contains the type of forces
    private Class type;

    // This field contains the team of this Unit
    private Team team;

    /**
     * The constructor of this class
     * @param type
     * @param team
     * @param field
     */
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

    /**
     * getter method of forces
     * @return
     */
    public List<Force> getForces() {
        return forces;
    }

    /**
     * this method will get called when this field gets attacked
     * @return
     */
    public int gotAttacked() {
        forces.remove(forces.size() - 1);
        return forces.size();
    }

    /**
     * the getter method of type
     * @return
     */
    public Class getType() {
        return type;
    }

    /**
     * this method returns true if player can attack this field with his dices
     * @param dices
     * @return
     */
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

    /**
     * getter method of team
     * @return
     */
    public Team getTeam() {
        return team;
    }

    /**
     * toString method of this class
     * @return
     */
    @Override
    public String toString() {
        return forces.size() + (type == Soldier.class ? "S" :
                type == Tank.class ? "T" :
                        type == Artillery.class ? "A" : "");
    }
}
