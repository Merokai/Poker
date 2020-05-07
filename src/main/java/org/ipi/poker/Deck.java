package org.ipi.poker;

import java.util.*;

public class Deck {
    public static final int SIZE = 52;


    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            cards.add(new Card(Pip.values()[cards.size() % 4], 1 + cards.size() % 13));
        }
        Collections.shuffle(cards);
    }

    public int count() {
        return cards.size();
    }

    public Card drawOne() {
        if (cards.size() < 1) {
            throw new IllegalStateException();
        }
        return cards.remove(0);
    }

    public Set<Card> drawFive() {
        Set<Card> cards = new HashSet<>();
        while (cards.size() < 5) {
            cards.add(drawOne());
        }
        return cards;
    }
}
