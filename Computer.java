package ntu.r09922114.gambling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import ntu.r09922114.util.*;

public class Computer {
    static String[] rankList = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    static char[] suitList = { 'C', 'D', 'H', 'S' };
    static HashMap<String, Integer> rankOrder = new HashMap<>();
    static HashMap<Character, Integer> suitOrder = new HashMap<>();
    static HashMap<String, int []> payoffTable = new HashMap<>();
    static {
        for (int i = 0; i < rankList.length; i++)
            rankOrder.put(rankList[i], i);
        for (int i = 0; i < suitList.length; i++)
            suitOrder.put(suitList[i], i);
        payoffTable.put("royal flush", new int[] {250, 500, 750, 1000, 4000});
        payoffTable.put("straight flush", new int[] {50, 100, 150, 200, 250});
        payoffTable.put("four of a kind", new int[] {25, 50, 75, 100, 125});
        payoffTable.put("full house", new int[] {9, 18, 27, 36, 45});
        payoffTable.put("flush", new int[] {6, 12, 18, 24, 30});
        payoffTable.put("strait", new int[] {4, 8, 12, 16, 20});
        payoffTable.put("three of a kind", new int[] {3, 6, 9, 12, 15});
        payoffTable.put("two pair", new int[] {2, 4, 6, 8, 10});
        payoffTable.put("Jacks or better", new int[] {1, 2, 3, 4, 5});
        payoffTable.put("others", new int[] {0, 0, 0, 0, 0});
    }

    private Card [] dack;
    private int cardIndex;

    public Computer() {
        cardIndex = 0;
        dack = new Card [52];
        int i = 0;
        for (String rank: rankList)
            for (char suit: suitList)
                dack[i++] = new Card(suit, rank);
    }

    public void startNewRound() {
        Shuffler.shuffleArray(dack);
        cardIndex = 0;
    }

    public Card dealOneCard() {
        return dack[cardIndex++];
    }


    public String classify(Card [] cards) {
        Sorter.bubbleSort(cards);
        if (isRoyalFlush(cards))
            return "royal flush";
        if (isStraitFlush(cards))
            return "straight flush";
        if (isFourOfOneKind(cards))
            return "four of a kind";
        if (isFullHouse(cards))
            return "full house";
        if (isFlush(cards))
            return "flush";
        if (isStrait(cards))
            return "strait";
        if (isThreeOfOneKind(cards))
            return "three of a kind";
        if (isTwoPair(cards))
            return "two pair";
        if (isJacksOrBetter(cards))
            return "Jacks or better";
        return "others";
    }

    public int pays(String hand, int bet) {
        return payoffTable.get(hand)[bet - 1];
    }

    private boolean sameSuit(Card... cards) {
        for (int i = 1; i < cards.length; i++)
            if (cards[i].getSuit() != cards[i - 1].getSuit())
                return false;
        return true;
    }

    private boolean sameRank(Card... cards) {
        for (int i = 1; i < cards.length; i++)
            if (rankOrder.get(cards[i].getRank()) != rankOrder.get(cards[i - 1].getRank()))
                return false;
        return true;
    }

    private boolean sequentialRank(Card... cards) {
        for (int i = 1; i < cards.length; i++)
            if (rankOrder.get(cards[i].getRank()) != rankOrder.get(cards[i - 1].getRank()) + 1)
                return false;
        return true;
    }

    private boolean isRoyalFlush(Card... cards) {
        if (cards[0].getRank().equals("10") &&
            cards[1].getRank().equals("J") &&
            cards[2].getRank().equals("Q") &&
            cards[3].getRank().equals("K") &&
            cards[4].getRank().equals("A") &&
            sameSuit(cards))
            return true;
        return false;
    }

    private boolean isStraitFlush(Card... cards) {
        return sequentialRank(cards) && sameSuit(cards);
    }

    private boolean isFourOfOneKind(Card... cards) {
        return sameRank(Arrays.copyOfRange(cards, 0, 4)) ||
            sameRank(Arrays.copyOfRange(cards, 1, 5));
    }

    private boolean isFullHouse(Card... cards) {
        return (sameRank(Arrays.copyOfRange(cards, 0, 3)) && sameRank(Arrays.copyOfRange(cards, 3, 5))) ||
            (sameRank(Arrays.copyOfRange(cards, 0, 2)) && sameRank(Arrays.copyOfRange(cards, 2, 5)));
    }

    private boolean isFlush(Card... cards) {
        return sameSuit(cards);
    }

    private boolean isStrait(Card... cards) {
        return sequentialRank(cards);
    }

    private boolean isThreeOfOneKind(Card... cards) {
        if (sameRank(Arrays.copyOfRange(cards, 0, 3)) &&
            !cards[2].getRank().equals(cards[3].getRank()) &&
            !cards[3].getRank().equals(cards[4].getRank()))
            return true;
        if (sameRank(Arrays.copyOfRange(cards, 1, 4)))
            return true;
        if (sameRank(Arrays.copyOfRange(cards, 2, 5)) &&
            !cards[0].getRank().equals(cards[1].getRank()) &&
            !cards[1].getRank().equals(cards[2].getRank()))
            return true;
        return false;
    }

    private boolean isTwoPair(Card... cards) {
        if (sameRank(Arrays.copyOfRange(cards, 0, 2)) &&
            sameRank(Arrays.copyOfRange(cards, 2, 4)) &&
            !cards[1].getRank().equals(cards[2].getRank()) &&
            !cards[3].getRank().equals(cards[4].getRank()))
            return true;
        if (sameRank(Arrays.copyOfRange(cards, 0, 2)) &&
            sameRank(Arrays.copyOfRange(cards, 3, 5)) &&
            !cards[1].getRank().equals(cards[2].getRank()) &&
            !cards[2].getRank().equals(cards[3].getRank()))
            return true;
        if (sameRank(Arrays.copyOfRange(cards, 1, 3)) &&
            sameRank(Arrays.copyOfRange(cards, 3, 5)) &&
            !cards[0].getRank().equals(cards[1].getRank()) &&
            !cards[2].getRank().equals(cards[3].getRank()))
            return true;
        return false;
    }

    private boolean isJacksOrBetter(Card... cards) {
        for (int i = 0; i < 4; i++) {
            if (sameRank(cards[i], cards[i + 1]) &&
                rankOrder.get(cards[i].getRank()) >= 8 &&
                rankOrder.get(cards[i].getRank()) < 13) {
                    Card prevCard = null;
                    for (int j = 0; j < 5; j++) {
                        if (j != i && prevCard != null && sameRank(cards[j], prevCard))
                            return false;
                    }
                    return true;
                }
        }
        return false;
    }
}
