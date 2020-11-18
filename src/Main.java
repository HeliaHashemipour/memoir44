import java.util.ArrayList;
import java.util.List;

/**
 * The main class of this application
 * Everything starts from here :)
 */
public class Main {

    /**
     * exactly here :D
     * @param args
     */
    public static void main(String[] args) {
        boolean flag = true;
        System.out.println("Welcome to the memoir44 game. In this game we'll simulate the WW2.");
        Allied.setPlayersName(Reader.input("Please enter the first players name: "));
        Axis.setPlayersName(Reader.input("Please enter the second players name: "));
        while (Axis.getScore() < 6 && Allied.getScore() < 6) {
            GameArena.printArena();
            Card[] cards;
            if (flag) {
                System.out.printf("It's %ss turn.%n", Allied.getPlayersName());
                cards = Allied.getCards();
            } else {
                System.out.printf("It's %ss turn.%n", Axis.getPlayersName());
                cards = Axis.getCards();
            }
            printCards(cards);
            int commandLimitation = selectCard(cards, flag ? Team.ALLIED : Team.AXIS);
            for (int i = 0; i < commandLimitation; i++) {
                Unit unit = selectUnit(flag ? Team.ALLIED : Team.AXIS);
                if (unit.getForces().get(0).move()) {
                    String input = Reader.input("Do you want to attack? (Y/n): ");
                    if (input.equalsIgnoreCase("y") || input.length() == 0)
                        unit.getForces().get(0).attack();
                } else {
                    System.out.println("You can't attack at this situation.");
                }
            }
            System.out.printf("Score of Allied: %s", Allied.getScore());
            System.out.printf("Score of Axis: %s", Axis.getScore());
            flag = !flag;
        }
        if (Axis.getScore() < 6) {
            System.out.printf("%s win.%n", Allied.getPlayersName());
        } else {
            System.out.printf("%s win.%n", Axis.getPlayersName());
        }
    }

    private static void printCards(Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
            System.out.printf("%s. Command %s units.%n", i + 1, cards[i].getCommandLimitation());
        }
    }

    private static int selectCard(Card[] cards, Team team) {
        boolean flag = true;
        int commandLimitation = 0;
        while (flag) {
            int index = Reader.integerInput("Please select one of the cards above: ");
            if (0 < index && index <= cards.length) {
                if (team == Team.ALLIED) {
                    commandLimitation = Allied.useCard(index - 1);
                } else {
                    commandLimitation = Axis.useCard(index - 1);
                }
                flag = false;
            } else {
                System.out.println("Invalid input!!");
            }
        }
        return commandLimitation;
    }

    private static Unit selectUnit(Team team) {
        boolean flag = true;
        Unit unit = null;
        while (flag) {
            String input = Reader.input("Please enter the coordinates of your unit: ");
            input = input.replace(" ", "");
            if (input.matches("[0-9]+,[0-9]+")) {
                String[] parameters = input.split(",");
                try {
                    int[] coordinates = {Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1])};
                    if (GameArena.isValidCoordinate(coordinates[0], coordinates[1])) {
                        unit = GameArena.getField(coordinates[0], coordinates[1]).getUnit();
                        if (unit == null) {
                            System.out.println("Entered coordinate is empty.");
                        } else if (unit.getTeam() != team) {
                            System.out.println("The unit in that Coordinate is not yours.");
                        } else {
                            System.out.printf("%s on (%s, %s) is selected.%n", unit,
                                    unit.getForces().get(0).getField().getX(), unit.getForces().get(0).getField().getY());
                            flag = false;
                        }
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }
        return unit;
    }
}
