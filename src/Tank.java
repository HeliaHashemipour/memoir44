import javax.swing.plaf.IconUIResource;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tank implements Force {

    private Field field;
    private Field reset;

    @Override
    public boolean move() {
        reset = field;
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

            } else if (input.equalsIgnoreCase("3")) {

                return true;
            } else
                System.out.println("Invalid input!");
        }
        return false;
    }

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
    }

    @Override
    public void setField(Field field) {
        this.field = field;
    }


    public Field getField() {
        return field;
    }
}
