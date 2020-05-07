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
            score += 100000 * uniqueCardScores.stream().max(Integer::compare).orElse(0);
        }

        // Straight
        if (uniqueCardScores.size() == 5 && uniqueCardScores.stream().max(Integer::compare).orElse(0) == uniqueCardScores.stream().min(Integer::compare).orElse(0) + 4) {
            if (score > 160000) { // Straight flush
                return 100000000 * uniqueCardScores.stream().max(Integer::compare).orElse(0);
            }
            score += 10000 * uniqueCardScores.stream().max(Integer::compare).orElse(0);
        }

        if (uniqueCardScores.containsAll(Set.of(14, 2, 3, 4, 5))) {
            if (score > 160000) { // Straight flush
                return 100000000 * 5;
            }
            score += 10000 * 5;
        }
        int pairScore = 0;
        for (int cardScore : uniqueCardScores) {
            final int cardsForThisCardScore = (int) mutableCards.stream().filter(c -> c.getScore() == cardScore).count();
            if (cardsForThisCardScore == 4) {
                return 10000000 * cardScore;
                // Pair
            } else if (cardsForThisCardScore == 3) { // ToaK
                if (score > 160) { // Full house
                    return 1000000 * cardScore;
                }
                score += 1000 * cardScore;
                mutableCards = mutableCards.stream().filter(c -> c.getScore() != cardScore).collect(Collectors.toSet());
                // Pair
            } else if (cardsForThisCardScore == 2) {
                if (pairScore > 0) {
                    // Second Pair
                    pairScore = 10 * Math.max(pairScore, cardScore) + Math.min(pairScore, cardScore);
                } else if (score > 1600) { // Full house
                    return 1000000 * Math.max(score / 1000, cardScore);
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
