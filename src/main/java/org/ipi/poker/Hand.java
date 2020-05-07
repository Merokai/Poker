package org.ipi.poker;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Hand {

    public static final int SIZE = 5;

    private final Set<Card> cards;

    public Hand() {
        this.cards = new HashSet<>();
    }

    public void addCard(Card card) {
        if (cards.size() >= SIZE) {
            throw new IllegalStateException();
        }
        cards.add(card);
    }

    public boolean isBetterThan(Hand hand2) {
        Optional<Integer> hand1Max = cards.stream().map(Card::getValue).max(Integer::compare);
        Optional<Integer> hand2Max = hand2.cards.stream().map(Card::getValue).max(Integer::compare);

        if (hand1Max.isEmpty() || hand2Max.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return hand1Max.get() > hand2Max.get();
    }
}
