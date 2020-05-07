package org.ipi.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckTest {

    @Test
    void newDeckHasCards() {
        Deck deck = new Deck();

        assertEquals(52, Deck.SIZE);
        assertEquals(Deck.SIZE, deck.count());

        int size = Deck.SIZE;
        for (int i = 0; i < 60; i++) {
            assertEquals(size, deck.count());

            if (size < 1) {
                assertThrows(IllegalStateException.class, deck::drawOne);
            } else {
                deck.drawOne();
                size--;
            }
        }

    }

}
