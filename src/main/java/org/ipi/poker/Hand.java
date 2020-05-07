package org.ipi.poker;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
        return computeScore() > hand2.computeScore();
    }

    private int computeScore() {
        Set<Card> mutableCards = new HashSet<>(cards);

        int score = 0;

        // Computing Pairs
        // 20 to 140 for One Pair + High Card
        // 200 to 1400 for Two Pair
        Set<Integer> uniqueScores = mutableCards.stream().map(Card::getScore).collect(Collectors.toSet());
        int pairScore = 0;
        for (int cardScore : uniqueScores) {
            if (mutableCards.stream().filter(c -> c.getScore() == cardScore).count() == 2) {
                // This is a second Pair
                if (pairScore > 0) {
                    return Math.max(pairScore, cardScore) * 100;
                }
                pairScore = cardScore;
                mutableCards = mutableCards.stream().filter(c -> c.getScore() != cardScore).collect(Collectors.toSet());
            }
        }
        score += 10 * pairScore;

        // Computing High Card value
        // 2 to 14 score
        score += mutableCards.stream().map(Card::getScore).max(Integer::compare).orElse(0);

        return score;
    }
}
