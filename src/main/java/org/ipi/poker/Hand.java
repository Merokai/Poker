package org.ipi.poker;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Hand {

    public static final int SIZE = 5;

    private final Set<Card> cards;

    private long score;

    public Hand() {
        this.cards = new HashSet<>();
        this.score = 0;
    }

    public void addCard(Card card) {
        if (cards.size() >= SIZE) {
            throw new IllegalStateException();
        }
        cards.add(card);
        this.score = computeScore();
    }

    public boolean isBetterThan(Hand hand2) {
        return score > hand2.score;
    }

    private long computeScore() {
        final long FOUR_OF_A_KIND = (long) Math.pow(15, 7);
        final long FULL_HOUSE = (long) Math.pow(15, 6);
        final long FLUSH = (long) Math.pow(15, 5);
        final long STRAIGHT = (long) Math.pow(15, 4);
        final long THREE_OF_A_KIND = (long) Math.pow(15, 3);
        final long PAIR = 15;

        long score = 0;


        Set<Integer> uniqueCardScores = cards.stream().map(Card::getScore).collect(Collectors.toSet());

        final int uniquePipsCount = (int) cards.stream().map(Card::getPip).distinct().count();

        // Five cards of the same pip: Flush
        if (uniquePipsCount == 1 && cards.size() == 5) {
            score = FLUSH * uniqueCardScores.stream().max(Integer::compare).orElse(0);
        }

        // Five differents values with lowest and highest closing a 5 values range: Straight
        if (uniqueCardScores.size() == 5 && uniqueCardScores.stream().max(Integer::compare).orElse(0) == uniqueCardScores.stream().min(Integer::compare).orElse(0) + 4) {
            return (score > FLUSH ? FLUSH : 1) * STRAIGHT * uniqueCardScores.stream().max(Integer::compare).orElse(0);
        }

        // Straight starting with an ace
        if (uniqueCardScores.containsAll(Set.of(14, 2, 3, 4, 5))) {
            return (score > FLUSH ? FLUSH : 1) * STRAIGHT * 5;
        }

        // Three/Four of a kind, full house and pairs
        for (int cardScore : uniqueCardScores) {

            final int numberOfCardsWithSameValue = (int) cards.stream().filter(c -> c.getScore() == cardScore).count();

            // Full house
            if (score > PAIR && numberOfCardsWithSameValue == 3) {
                return FULL_HOUSE * cardScore + score;
            }
            if (score > THREE_OF_A_KIND && numberOfCardsWithSameValue == 2) {
                return FULL_HOUSE * score / THREE_OF_A_KIND + cardScore;
            }

            switch (numberOfCardsWithSameValue) {
                case 4:
                    return FOUR_OF_A_KIND * cardScore;
                case 3:
                    score += THREE_OF_A_KIND * cardScore;
                    break;
                case 2:
                    score = PAIR * Math.max(score, cardScore) + Math.min(score, cardScore);
                    break;
            }
        }

        // High card
        return score + cards.stream().map(Card::getScore).max(Integer::compare).orElse(0);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
