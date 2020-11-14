public class Axis {

    private static Card[] cards = new Card[2];
    private static int score;
    private static String playersName;

    static {
        for (int i = 0; i < 2; i++) {
            cards[i] = Card.getCard();
        }
    }

    public static void setPlayersName(String playersName) {
        Axis.playersName = playersName;
    }

    public static Card[] getCards() {
        return cards;
    }

    public static int getScore() {
        return score;
    }

    public static String getPlayersName() {
        return playersName;
    }

    public static void scored() {
        score++;
    }

    public static int useCard(int index) {
        Card card = cards[index];
        cards[index] = Card.getCard();
        Card.returningCard(card);
        return card.getCommandLimitation();
    }
}
