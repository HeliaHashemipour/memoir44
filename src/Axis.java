/**
 * This Class contains the information of the Axis team
 * @author Hashemipour
 * @since 2020
 */
public class Axis {

    // This field contains the cards of Axis team
    private static Card[] cards = new Card[4];

    // This field contains the score of Axis team
    private static int score;

    // This field contains the players name which plays with axis team
    private static String playersName;

    // An static block to get Cards from card repository.
    static {
        for (int i = 0; i < 2; i++) {
            cards[i] = Card.getCard();
        }
    }

    /**
     * This method sets the name of player which plays with axis team.
     * @param playersName
     */
    public static void setPlayersName(String playersName) {
        Axis.playersName = playersName;
    }

    /**
     * Getter method of Cards
     * @return
     */
    public static Card[] getCards() {
        return cards;
    }

    /**
     * The getter method of Score
     * @return
     */
    public static int getScore() {
        return score;
    }

    /**
     * The getter method of players name
     * @return
     */
    public static String getPlayersName() {
        return playersName;
    }

    public static void scored() {
        score++;
    }

    /**
     * It uses a card and replace it with another one from repository
     * @param index
     * @return
     */
    public static int useCard(int index) {
        Card card = cards[index];
        cards[index] = Card.getCard();
        Card.returningCard(card);
        return card.getCommandLimitation();
    }
}
