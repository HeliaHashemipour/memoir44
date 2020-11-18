/**
 * This Class contains the information of the Allied team
 * @author Hashemipour
 * @since 2020
 */
public class Allied {

    // This field contains the cards of Allied team
    private static Card[] cards = new Card[4];

    // This field contains the score of Allied team
    private static int score;

    // This field contains the players name which plays with allied team
    private static String playersName;

    // An static block to get Cards from card repository.
    static {
        for (int i = 0; i < 4; i++) {
            cards[i] = Card.getCard();
        }
    }

    /**
     * This method sets the name of player which plays with allied team.
     * @param playersName
     */
    public static void setPlayersName(String playersName) {
        Allied.playersName = playersName;
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

    /**
     * This method invokes every time that allies player scores
     */
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
