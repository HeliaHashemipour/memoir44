import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class of this application
 * Everything starts from here :)
 *
 * @author Hashemipour
 * @since 2020
 */
public class Main {

    /**
     * exactly here :D
     *
     * @param args
     */
    public static void main(String[] args) {
        boolean flag = true;

        Scanner scanner = new Scanner(System.in);
        System.out.print(Printer.RED_BOLD);
        System.out.println("Welcome to the memoir44 game. In this game we'll simulate the WW2.");
        System.out.print(Printer.BLUE_BOLD);
        Allied.setPlayersName(Reader.input("Please enter the first players name: "));
        Axis.setPlayersName(Reader.input("Please enter the second players name: "));
        boolean check = true;
        while (check) {
            show();
            int option = scanner.nextInt();
            switch (option) {

                case 1:
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
                        System.out.printf("Score of Allied: %s%n", Allied.getScore());
                        System.out.printf("Score of Axis: %s%n", Axis.getScore());
                        flag = !flag;
                    }
                    if (Axis.getScore() < 6) {
                        System.out.printf(Printer.RED_BOLD + "%s win.%n", Allied.getPlayersName());
                    } else {
                        System.out.printf(Printer.RED_BOLD + "%s win.%n", Axis.getPlayersName());
                    }
                    break;
                case 3:
                    System.out.println(Printer.BLACK_BOLD);
                    System.out.println("Published in collaboration with the Mission for the 60th Anniversary of the D-Day Landings & Liberation of France, Memoir '44 is a uniquely fun, simple and engaging game.\n" +
                            "Designed to commemorate the efforts and sacrifices of the men & women of the Second World War, we trust it will provide all who play it with a sense of history and the desire\nto learn more about that greatest generation. Our fondest hope is that it will make you want to transmit this unique historical heritage onto younger generations.\n" +
                            "To that effect, we encourage you to visit the numerous web sites we will be progressively featuring on our Links page. They offer an endless treasure trove of information on\nthese events that shaped our history.\n" +
                            "We also invite you to visit the beaches, villages and museums of Normandy, where over 1,000 ceremonies are going to be held over the coming months.\n" +
                            "Memoir '44 is a unique historical game where players command a horde of little plastic Army men facing-off in dozens of WWII battles on an oversize hex game board.\n" +
                            "Each battle scenario mimics the historical terrain, troop placements and objectives of each army. Deploying forces through a variety of Command cards, the smart commander uses\nthe unique skills of his units - infantry, paratroopers, tanks, artillery, commandos and resistance fighters - to its greatest strength.\n" +
                            "Easy to learn and fast-paced, Memoir '44 requires strategic card play, timely dice rolling and an aggressive, yet flexible battle plan to achieve victory!");
                    break;
                case 2:
                    showEdit();
                    int nameNumber = scanner.nextInt();
                    if (nameNumber == 1) {
                        Allied.setPlayersName(Reader.input(Printer.BLACK_BOLD+"Change the name:\n"));
                        System.out.println(Printer.BLACK_BOLD+"The name has changed...\n");
                    } else if (nameNumber == 2) {
                        Axis.setPlayersName(Reader.input(Printer.BLACK_BOLD+"Change the name to :\n"));
                        System.out.println(Printer.BLACK_BOLD+"The name has changed...\n");
                    }
                    break;
                case 4:
                    check = false;
                    System.out.println(Printer.RED_BOLD + "Bye ):");
                    break;
                default:
                    System.out.println(Printer.RED_BOLD+"Incorrect input. PLZ pay attention..");
            }
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

    private static void show() {
        System.out.println();
        System.out.println(Printer.PURPLE_BOLD_BRIGHT);
        System.out.println("**Menu**");
        System.out.println("1)Start the Game\n2)Edit the players name\n3)About the Game\n4)Exit");
    }

    private static void showEdit() {
        System.out.println(Printer.BLUE_BOLD+"Which player do you want to edit?(Choose the Correct Number)\n" + "1)" + Allied.getPlayersName() + "\n2)" + Axis.getPlayersName() + "\n");

    }
}
