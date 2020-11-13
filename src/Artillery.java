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
                        switch (firstMove) {
                            case "U":
                                moveUp();
                                flag = false;
                                break;
                            case "D":
                                moveDown();
                                flag = false;
                                break;
                            case "UR":
                                moveUpRight();
                                flag = false;
                                break;
                            case "UL":
                                moveUpLeft();
                                flag = false;
                                break;
                            case "DR":
                                moveDownRight();
                                flag = false;
                                break;
                            case "DL":
                                moveDownLeft();
                                flag = false;
                                break;
                            default:
                                System.out.println("Invalid input!");
                        }
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
        String input = Reader.input("Please enter the coordinates that you want to attack: ");
        input = input.replace(" ", "");
        String[] parameters = input.split(",");
        int x = Integer.parseInt(parameters[0]);
        int y = Integer.parseInt(parameters[1]);
        try {
            if (!GameArena.isAvailable(x, y))
                throw new Exception();
            int distance = GameArena.getDistance(field.getX(), field.getY(), x, y);
            Field field = GameArena.getField(x, y);
            if (distance == 0)
                System.out.println("Invalid input!");
            if (distance == 1 || distance == 2){
                int dice1 = (int) (Math.random() * 6 + 1);
                int dice2 = (int) (Math.random() * 6 + 1);
                int dice3 = (int) (Math.random() * 6 + 1);
                System.out.println("dice1 = " + dice1);
                System.out.println("dice2 = " + dice2);
                System.out.println("dice3 = " + dice3);
                if (field.getUnit().canGotAttacked(dice1, dice2, dice3))
                    field.gotAttacked();
            }
            if (distance == 3 || distance == 4){
                int dice1 = (int) (Math.random() * 6 + 1);
                int dice2 = (int) (Math.random() * 6 + 1);
                int dice3 = (int) (Math.random() * 6 + 1);
                int dice4 = (int) (Math.random() * 6 + 1);
                System.out.println("dice1 = " + dice1);
                System.out.println("dice2 = " + dice2);
                System.out.println("dice3 = " + dice3);
                System.out.println("dice4 = " + dice4);
                if (field.getUnit().canGotAttacked(dice1, dice2, dice3, dice4))
                    field.gotAttacked();
            }
            if (distance == 5 || distance == 6){
                int dice1 = (int) (Math.random() * 6 + 1);
                System.out.println("dice1 = " + dice1);
                if (field.getUnit().canGotAttacked(dice1))
                    field.gotAttacked();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Force solder = new Soldier();
        solder.move();

    }
}
