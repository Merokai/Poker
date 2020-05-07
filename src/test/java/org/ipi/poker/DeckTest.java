package org.ipi.poker;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test void drawingCards(){
        Deck deck = new Deck();
        Card card = deck.drawOne();

        assertNotNull(card);
    }

}
