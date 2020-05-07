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


        Set<Integer> uniqueCardScores = mutableCards.stream().map(Card::getScore).collect(Collectors.toSet());

        // Flush
        if (mutableCards.stream().map(Card::getPip).distinct().count() == 1 && mutableCards.size() == 5) {
            return 100000 * uniqueCardScores.stream().max(Integer::compare).orElse(0);
        }

        // Straight
        if (uniqueCardScores.size() == 5 && uniqueCardScores.stream().max(Integer::compare).orElse(0) == uniqueCardScores.stream().min(Integer::compare).orElse(0) + 4) {
            return 10000 * uniqueCardScores.stream().max(Integer::compare).orElse(0);
        }

        int pairScore = 0;
        for (int cardScore : uniqueCardScores) {
            final int cardsForThisCardScore = (int) mutableCards.stream().filter(c -> c.getScore() == cardScore).count();
            // ToaK
            if (cardsForThisCardScore == 3) {
                score += 1000 * cardScore;
                mutableCards = mutableCards.stream().filter(c -> c.getScore() != cardScore).collect(Collectors.toSet());
                // Pair
            } else if (cardsForThisCardScore == 2) {
                if (pairScore > 0) {
                    // Second Pair
                    pairScore = 10 * Math.max(pairScore, cardScore) + Math.min(pairScore, cardScore);
                } else {
                    pairScore = cardScore;
                }
                mutableCards = mutableCards.stream().filter(c -> c.getScore() != cardScore).collect(Collectors.toSet());
            }
        }
        score += 10 * pairScore;

        // High card
        score += mutableCards.stream().map(Card::getScore).max(Integer::compare).orElse(0);

        return score;
    }
}
