import java.util.List;

/**
 * The ThirteensBoard class represents the board in a game of Thirteens.
 */
public class ThirteensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 10;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0};

    /**
     * Creates a new <code>ThirteensBoard</code> instance.
     */
    public ThirteensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Thirteens, the legal groups are (1) a pair of non-face cards
     * whose values add to 13, and (2) a king.
     *
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     * false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        if (selectedCards.size() == 1)
            return containsKing(selectedCards);
        else if (selectedCards.size() == 2)
            return containsPairSum13(selectedCards);
        else
            return false;
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Thirteens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 13, or (2) a king.
     *
     * @return true if there is a legal play left on the board;
     * false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        List<Integer> cIndexes = cardIndexes();
        return containsPairSum13(cIndexes) || containsKing(cIndexes);
    }

    /**
     * Look for an 13-pair in the selected cards.
     *
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 13-pair.
     * @return a list of the indexes of an 13-pair, if an 13-pair was found;
     * an empty list, if an 13-pair was not found.
     */
    private boolean containsPairSum13(List<Integer> selectedCards) {
        for (int cIndex1: selectedCards)
            for (int cIndex2: selectedCards)
                if (cIndex1 != cIndex2 && cardAt(cIndex1).pointValue() + cardAt(cIndex2).pointValue() == 13)
                    return true;
        return false;
    }

    /**
     * Look for a king in the selected cards.
     *
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a king.
     * @return a list of the index of a king, if a king was found;
     * an empty list, if a king was not found.
     */
    private boolean containsKing(List<Integer> selectedCards) {
        for (int k : selectedCards)
            if (cardAt(k).rank().equals("king"))
                return true;
        return false;
    }
}
