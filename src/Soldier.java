import java.util.ArrayList;
import java.util.List;

public class Soldier implements Force {

    private Field field;

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

                return false;
            } else {
                System.out.println("Invalid input!");
            }
        }
        return false;
    }

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

        }
    }

    @Override
    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public static void main(String[] args) {
        Soldier soldier = new Soldier();
        System.out.println(soldier.move());
    }
}
