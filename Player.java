package ntu.r09922114.gambling;

import java.io.*;
import java.util.*;

public class Player {
    private String name;
    private int asset;
    private Card [] cards;

    public Player(String name, int asset) {
        this.name = name;
        this.asset = asset;
        this.cards = new Card [5];
    }

    public Player(String name) {
        this(name, 1000);
    }

    public String getName() {
        return name;
    }

    public int getAsset() {
        return asset;
    }

    public Card[] getCards() {
        return cards;
    }

    public void placeAbet(int bet) {
        asset -= bet;
    }

    public void drawCards(Computer c) {
        for (int i = 0; i < cards.length; i++)
            this.cards[i] = c.dealOneCard();
    }

    public void showCards() {
        String[] indices = {"(a)", "(b)", "(c)", "(d)", "(e)"};
        for (int i = 0; i < 5; i++)
            System.out.print(" " + indices[i] + " " + cards[i].toString());
        System.out.println(".");
    }

    public void replaceCards(String keptIndicesToken, Computer c) {
        char[] indices = { 'a', 'b', 'c', 'd', 'e' };
        HashMap<Character, Integer> indexMapper = new HashMap<>();
        for (int i = 0; i < 5; i++)
            indexMapper.put(indices[i], i);

        Set<Character> keptIndicesSet = new HashSet<>();
        for (char ch: keptIndicesToken.toCharArray())
            keptIndicesSet.add(ch);
        for (char ch: indices) {
            if (!keptIndicesSet.contains(ch)) {
                System.out.println("Okay. I will discard " + "(" + ch + ") " + cards[indexMapper.get(ch)].toString() + ".");
                cards[indexMapper.get(ch)] = c.dealOneCard();
            }
        }
    }

    public void getPayoff(int payoff) {
        asset += payoff;
    }
}
