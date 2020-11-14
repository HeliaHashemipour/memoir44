import java.util.ArrayList;
import java.util.List;

public class Artillery implements Force {

    private Field field;

    @Override
    public boolean move() {
        System.out.println("0.Just attack");
        System.out.println("1.Move one step (but can't attack after)");
        boolean flag = true;
        while (flag) {
            String input = Reader.input("Please enter one of the options: ");
            if (input.equalsIgnoreCase("0"))
                return false;

            else if (input.equalsIgnoreCase("1")) {
                while (flag) {
                    input = Reader.input("Please enter where you wanna' move the artillery with its amount: ");
                    if (isValidInput(1, input)) {
                        String firstAmount = input.substring(0, 1);
                        String firstMove = input.substring(1, input.length());
                        boolean[] array = switchCaseOfOneMove(firstMove);
                        if(array[0])
                            flag = false;
                    } else
                        System.out.println("Invalid input!");
                }
                return true;
            } else {
                System.out.println("Invalid input!");
            }
        }
        return false;
    }

    @Override
    public void attack() {
        boolean flag = true;
        int x = 0, y = 0;
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
                    System.out.println("Invalid input!");
                }
            }
            flag = true;
            try {
                if (!GameArena.isValidCoordinate(x, y))
                    throw new Exception();
                int distance = GameArena.getDistance(field.getX(), field.getY(), x, y);
                Field field = GameArena.getField(x, y);
                if (distance == 0)
                    System.out.println("Invalid input!");
                else if (distance == 1 || distance == 2){
                    List<Integer> dices = new ArrayList<>();
                    for (int i = 0; i < 3 ; i++) {
                        int dice = (int) (Math.random() * 6 + 1);
                        dices.add(dice);
                        System.out.println("dice" + i + 1 + dice);
                    }
                    if (field.getUnit().canGotAttacked(dices))
                        field.gotAttacked();
                }
                else if (distance == 3 || distance == 4){
                    List<Integer> dices = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        int dice = (int) (Math.random() * 6 + 1);
                        dices.add(dice);
                        System.out.println("dice" + i + 1 + dice);
                    }
                    if (field.getUnit().canGotAttacked(dices))
                        field.gotAttacked();
                }
                else if (distance == 5 || distance == 6){
                    List<Integer> dices = new ArrayList<>();
                    for (int i = 0; i < 1; i++) {
                        int dice = (int) (Math.random() * 6 + 1);
                        dices.add(dice);
                        System.out.printf("%s. dice: %s", i + 1, dice);
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
                e.printStackTrace();
            }
        }
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public void setField(Field field) {

    }

    public static void main(String[] args) {
        Force tank = new Tank();
        tank.move();

    }
}
