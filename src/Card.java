import java.util.ArrayList;
import java.util.List;

public class Card {

    private final static List<Card> cardRepository;
    private final int commandLimitation;

    private Card(int commandLimitation) {
        this.commandLimitation = commandLimitation;
    }

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

    public int getCommandLimitation() {
        return commandLimitation;
    }

    public static Card getCard() {
        int index = (int) (Math.random() * cardRepository.size());
        return cardRepository.remove(index);
    }
}
