import javax.swing.plaf.IconUIResource;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Every instance of this class is a Tank on the game arena
 * @author Hashemipour
 * @since 2020
 */
public class Tank implements Force {

    // This field contains the field that tank is standing on
    private Field field;

    /**
     * This method we'll get called when we want to move a Tank
     * @return
     */
    @Override
    public boolean move() {
        Field reset = field;
        System.out.println("0.Attack");
        System.out.println("1.Move one step then attack");
        System.out.println("2.Move two steps then attack");
        System.out.println("3.Move three steps then attack");
        boolean flag = true;
        while (flag) {
            String input = Reader.input("Please enter one of the options: ");
            if (input.equalsIgnoreCase("0"))
                return true;
            if (input.equalsIgnoreCase("1")) {
                while (flag) {
                    input = Reader.input("Please enter where you wanna' move the tank with its amount: ");
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
                    input = Reader.input("Please enter where you wanna' move the tank with its amount : ");
                    input = input.replace("  ", " ");
                    String[] inputs = input.split(" ");
                    if (isValidInput(2, inputs)) {
                        boolean[] array = null;
                        String firstAmount = inputs[0].substring(0, 1);
                        String firstMove = inputs[0].substring(1, inputs[0].length());
                        String secondMove = null;
                        if (inputs.length > 1) {
                            secondMove = inputs[1].substring(1, inputs[1].length());
                        }
                        array = switchCaseOfMoreMove(firstMove, firstAmount);
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
                return true;
            } else if (input.equalsIgnoreCase("3")) {
                while (flag) {
                    input = Reader.input("Please enter where you wanna' move the tank with its amount : ");
                    input = input.replace("  ", " ");
                    String[] inputs = input.split(" ");
                    boolean[] array = null;
                    if (isValidInput(3, inputs)) {
                        String firstAmount = inputs[0].substring(0, 1);
                        String firstMove = inputs[0].substring(1);
                        String secondAmount = null;
                        String secondMove = null;
                        if (inputs.length > 1) {
                            secondAmount = inputs[1].substring(0, 1);
                            secondMove = inputs[1].substring(1);
                        }
                        String thirdMove = null;
                        if (inputs.length > 2) {
                            thirdMove = inputs[2].substring(1);
                        }
                        array = switchCaseOfMoreMove(firstMove,firstAmount);
                        boolean continuing = array[1];
                        if (inputs.length > 1 && array[0] && continuing) {
                            array = switchCaseOfMoreMove(secondMove, secondAmount);
                            continuing = array[1];
                        }

                        if (inputs.length > 2 && array[0] && continuing) {
                            array = switchCaseOfOneMove(thirdMove);
                        }
                        if (array[0])
                            flag = false;

                        else
                            field = reset;

                    } else
                        System.out.println("Invalid input!");
                }
                return true;
            } else
                System.out.println("Invalid input!");
        }
        return false;
    }

    /**
     * This method we'll get called when a tank wants to attack
     */
    @Override
    public void attack() {
        boolean flag = true;
        int x = 0, y = 0;
        while (flag) {
            try {
                String input = Reader.input("Please enter the coordinates that you want to attack: ");
                input = input.replace(" ", "");
                String[] parameters = input.split(",");
                x = Integer.parseInt(parameters[0]);
                y = Integer.parseInt(parameters[1]);
                flag = false;
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
        try {
            if (!GameArena.isValidCoordinate(x, y))
                throw new Exception();
            int distance = GameArena.getDistance(field.getX(), field.getY(), x , y);
            Field field = GameArena.getField(x, y);
            if (distance == 0) {
                System.out.println("Invalid input!");
            } else if (distance <= 3) {
                List<Integer> dices = new ArrayList<>();
                int limitation = field.limitationOfTankOutside() + this.field.limitationOfTankInside();
                for (int i = 0; i < 3 - limitation; i++) {
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

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * setter method of field
     * @param field
     */
    @Override
    public void setField(Field field) {
        this.field = field;
    }

    /**
     * getter method of field
     * @return
     */
    public Field getField() {
        return field;
    }
}
