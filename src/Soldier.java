import java.util.ArrayList;
import java.util.List;

/**
 * Every instance of this class is a Soldier on the game arena
 * @author Hashemipour
 * @since 2020
 */
public class Soldier implements Force {

    // This field contains the field that solder is standing on
    private Field field;

    /**
     * We call this method when ever we want to move a solder
     * @return
     */
    @Override
    public boolean move() {
        Field reset = field;
        System.out.println("0. Don't move");
        System.out.println("1. Move 1 step");
        System.out.println("2. Move 2 steps (But can't attack after)");
        boolean flag = true;
        while (flag) {
            String input = Reader.input("Please enter one of the options: ");
            if (input.equalsIgnoreCase("0")) {
                return true;
            } else if (input.equalsIgnoreCase("1")) {
                while (flag) {
                    input = Reader.input("Please enter where you wanna' move the soldier with its amount: ");
                    if (isValidInput(1, input)) {
                        String firstMove = input.substring(1);
                        boolean[] array = switchCaseOfOneMove(firstMove);
                        if(array[0])
                            flag = false;
                    } else
                        System.out.println("Invalid input!");
                }
                return true;
            } else if (input.equalsIgnoreCase("2")) {
                while (flag) {
                    input = Reader.input("Please enter where you wanna' move the soldier with its amount : ");
                    input = input.replace("  ", " ");

                    String[] inputs = input.split(" ");
                    if (isValidInput(2, inputs)) {
                        String firstAmount = inputs[0].substring(0, 1);
                        String firstMove = inputs[0].substring(1);
                        String secondMove = null;
                        if (inputs.length > 1) {
                            secondMove = inputs[1].substring(1);
                        }
                        boolean[] array = switchCaseOfMoreMove(firstMove, firstAmount);
                        boolean continuing = array[1];
                        if (inputs.length > 1 && array[0] && continuing) {
                            switchCaseOfOneMove(secondMove);
                        }
                        if (array[0])
                            flag = false;

                        else
                            field = reset;

                    } else
                        System.out.println("Invalid input!");
                }
                return false;
            } else {
                System.out.println("Invalid input!");
            }
        }
        return false;
    }

    /**
     * We will call this method when a player wants its soldiers to attack
     */
    @Override
    public void attack() {
        boolean flag = true;
        int x = 0;
        int y = 0;
        while (flag) {
            while (flag) {
                try {
                    String input = Reader.input("Please enter the coordinates that you want to attack: ");
                    input = input.replace(" ", "");
                    String[] parameters = input.split(",");
                    x = Integer.parseInt(parameters[0]);
                    y = Integer.parseInt(parameters[1]);
                    flag = false;
                } catch (Exception e) {
                    System.out.println("Invalid input");
                }
            }
            flag = true;
            try {
                if (!GameArena.isValidCoordinate(x, y))
                    throw new Exception();
                int distance = GameArena.getDistance(field.getX(), field.getY(), x, y);
                Field field = GameArena.getField(x, y);

                if (distance == 0) {
                    System.out.println("Invalid input!");

                } else if (distance == 1) {
                    List<Integer> dices = new ArrayList<>();
                    int limitation = field.limitationOfSolderOutside();
                    for (int i = 0; i < 3 - field.limitationOfSolderOutside(); i++) {
                        int dice = (int) (Math.random() * 6 + 1);
                        dices.add(dice);
                        System.out.println("dice" + i + 1 + dice);
                    }
                    if (field.getUnit().canGotAttacked(dices))
                        field.gotAttacked();
                } else if (distance == 2) {
                    List<Integer> dices = new ArrayList<>();
                    for (int i = 0; i < 2 - field.limitationOfSolderOutside(); i++) {
                        int dice = (int) (Math.random() * 6 + 1);
                        dices.add(dice);
                        System.out.println("dice" + i + 1 + dice);
                    }
                    if (field.getUnit().canGotAttacked(dices))
                        field.gotAttacked();
                } else if (distance == 3) {
                    List<Integer> dices = new ArrayList<>();
                    for (int i = 0; i < 1 - field.limitationOfSolderOutside(); i++) {
                        int dice = (int) (Math.random() * 6 + 1);
                        dices.add(dice);
                        System.out.printf("%s. dice: %s%n", i + 1, dice);
                    }
                    if (field.getUnit().canGotAttacked(dices)) {
                        field.gotAttacked();
                        System.out.println("Attacking ... ");
                    } else {
                        System.out.println("Sorry; You can't attack that unit this round.\nTry it later rounds");
                    }
                } else
                    System.out.println("You can't attack to this field");
                flag = false;
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    /**
     * the setter method of field
     * @param field
     */
    @Override
    public void setField(Field field) {
        this.field = field;
    }

    /**
     * The getter method of field
     * @return
     */
    public Field getField() {
        return field;
    }

}
