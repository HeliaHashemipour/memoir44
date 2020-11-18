import java.util.ArrayList;
import java.util.List;

/**
 * Every instance of this class is a game card that contains the command limitation
 * player should command as many forces as wrote on the card
 * @author Hashemipour
 * @since 2020
 */
public class Card {

    // This field is a card repository it and it contains the cards of this game.
    private final static List<Card> cardRepository;

    // This field contains the command limitation of this card
    private final int commandLimitation;

    /**
     * I've made Constructor of this class private so it can't get initialized from outside of this class
     * @param commandLimitation
     */
    private Card(int commandLimitation) {
        this.commandLimitation = commandLimitation;
    }

    // The static block that adds cards of game to the card repository.
    static {
        cardRepository = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            cardRepository.add(new Card(1));
        }
        for (int i = 0; i < 13; i++) {
            cardRepository.add(new Card(2));
        }
        for (int i = 0; i < 10; i++) {
            cardRepository.add(new Card(3));
        }
        for (int i = 0; i < 6; i++) {
            cardRepository.add(new Card(4));
        }
    }

    /**
     * The getter method of command limitation
     * @return
     */
    public int getCommandLimitation() {
        return commandLimitation;
    }

    /**
     * This method gets a random card from the card repository
     * @return
     */
    public static Card getCard() {
        int index = (int) (Math.random() * cardRepository.size());
        return cardRepository.remove(index);
    }

    /**
     * this method returns a card to the repository
     * @param card
     */
    public static void returningCard(Card card) {
        cardRepository.add(card);
    }
}
