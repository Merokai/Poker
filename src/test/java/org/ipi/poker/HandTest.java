package org.ipi.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
