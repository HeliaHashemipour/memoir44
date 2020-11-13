public class Soldier implements Force {

    private Field field;

    @Override
    public boolean move() {
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
            } else if (input.equalsIgnoreCase("2")) {
                while (flag) {
                    input = Reader.input("Please enter where you wanna' move the soldier with its amount : ");
                    input = input.replace("  ", " ");
                    String[] inputs = input.split(" ");
                    if (isValidInput(2, inputs)) {
                        String firstAmount = inputs[0].substring(0, 1);
                        String firstMove = inputs[0].substring(1, inputs[0].length());
                        String secondAmount = null;
                        String secondMove = null;
                        if (inputs.length > 1) {
                            secondAmount = inputs[1].substring(0, 1);
                            secondMove = inputs[1].substring(1, inputs[1].length());
                        }
                        switch (firstMove) {
                            case "U":
                                for (int i = 0; i < Integer.parseInt(firstAmount); i++)
                                    moveUp();
                                flag = false;
                                break;
                            case "D":
                                for (int i = 0; i < Integer.parseInt(firstAmount); i++)
                                    moveDown();
                                flag = false;
                                break;
                            case "UR":
                                for (int i = 0; i < Integer.parseInt(firstAmount); i++)
                                    moveUpRight();
                                flag = false;
                                break;
                            case "UL":
                                for (int i = 0; i < Integer.parseInt(firstAmount); i++)
                                    moveUpLeft();
                                flag = false;
                                break;
                            case "DR":
                                for (int i = 0; i < Integer.parseInt(firstAmount); i++)
                                    moveDownRight();
                                flag = false;
                                break;
                            case "DL":
                                for (int i = 0; i < Integer.parseInt(firstAmount); i++)
                                    moveDownLeft();
                                flag = false;
                                break;
                            default:
                                System.out.println("Invalid input!");
                        }
                        if (inputs.length >1) {
                            switch (secondMove) {
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
                        }
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
            if (distance == 0) {
                System.out.println("Invalid input!");
            } else if (distance == 1) {
                int dice1 = (int) (Math.random() * 6 + 1);
                int dice2 = (int) (Math.random() * 6 + 1);
                int dice3 = (int) (Math.random() * 6 + 1);
                System.out.println("dice1 = " + dice1);
                System.out.println("dice2 = " + dice2);
                System.out.println("dice3 = " + dice3);
                if (field.getUnit().canGotAttacked(dice1, dice2, dice3))
                    field.gotAttacked();
            } else if (distance == 2) {
                int dice1 = (int) (Math.random() * 6 + 1);
                int dice2 = (int) (Math.random() * 6 + 1);
                System.out.println("dice1 = " + dice1);
                System.out.println("dice2 = " + dice2);
                if (field.getUnit().canGotAttacked(dice1, dice2))
                    field.gotAttacked();
            } else if (distance == 3) {
                int dice1 = (int) (Math.random() * 6 + 1);
                System.out.println("dice1 = " + dice1);
                if (field.getUnit().canGotAttacked(dice1))
                    field.gotAttacked();
            }
        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }
}
