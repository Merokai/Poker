package org.ipi.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {

    @Test
    void newHand() {
        Deck deck = new Deck();
        Hand hand = new Hand();
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                hand.addCard(deck.drawOne());
            } else {
                assertThrows(IllegalStateException.class, () -> hand.addCard(deck.drawOne()));
            }
        }
    }

    @Test void compareHands(){
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();

        hand1.addCard(new Card(Pip.HEARTS, 5));
        hand2.addCard(new Card(Pip.HEARTS, 4));

        assertTrue(hand1.isBetterThan(hand2));
    }
}
