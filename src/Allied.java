public class Allied {

    private static Card[] cards = new Card[4];
    private static int score;
    private static String playersName;

    static {
        for (int i = 0; i < 4; i++) {
            cards[i] = Card.getCard();
        }
    }

    public static void setPlayersName(String playersName) {
        Allied.playersName = playersName;
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


}
