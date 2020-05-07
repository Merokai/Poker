package org.ipi.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        // Hand 1 wins with a High Card of 8
        Hand hand1 = new Hand();
        hand1.addCard(new Card(Pip.HEARTS, 8));
        Hand hand2 = new Hand();
        hand2.addCard(new Card(Pip.HEARTS, 7));

        assertTrue(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));

        // Hand 1 wins with a High Card of 1
        hand1 = new Hand();
        hand1.addCard(new Card(Pip.HEARTS, 1));
        hand2 = new Hand();
        hand2.addCard(new Card(Pip.HEARTS, 13));

        assertTrue(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));

        // Hand 1 wins with a pair of 2 against an High Card
        hand1 = new Hand();
        hand1.addCard(new Card(Pip.HEARTS, 2));
        hand1.addCard(new Card(Pip.CLOVERS, 2));
        hand2 = new Hand();
        hand2.addCard(new Card(Pip.HEARTS, 1));

        assertTrue(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));


        // Hand 1 wins with a pair of 3 against a pair of 2
        hand1 = new Hand();
        hand1.addCard(new Card(Pip.HEARTS, 3));
        hand1.addCard(new Card(Pip.CLOVERS, 3));
        hand2 = new Hand();
        hand2.addCard(new Card(Pip.HEARTS, 2));
        hand2.addCard(new Card(Pip.PIKES, 2));

        assertTrue(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));


        // Hand 1 wins with a pair thanks to his high card
        hand1 = new Hand();
        hand1.addCard(new Card(Pip.HEARTS, 3));
        hand1.addCard(new Card(Pip.CLOVERS, 3));
        hand1.addCard(new Card(Pip.CLOVERS, 6));
        hand2 = new Hand();
        hand2.addCard(new Card(Pip.TILES, 3));
        hand2.addCard(new Card(Pip.PIKES, 3));
        hand2.addCard(new Card(Pip.PIKES, 5));

        assertTrue(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));


        // Hand 1 wins with two pairs
        hand1 = new Hand();
        hand1.addCard(new Card(Pip.HEARTS, 3));
        hand1.addCard(new Card(Pip.CLOVERS, 3));
        hand1.addCard(new Card(Pip.CLOVERS, 2));
        hand1.addCard(new Card(Pip.TILES, 2));
        hand2 = new Hand();
        hand2.addCard(new Card(Pip.TILES, 10));
        hand2.addCard(new Card(Pip.PIKES, 10));
        hand2.addCard(new Card(Pip.PIKES, 5));
        hand2.addCard(new Card(Pip.PIKES, 4));

        assertTrue(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));


        // nobody wins with two pairs
        hand1 = new Hand();
        hand1.addCard(new Card(Pip.HEARTS, 4));
        hand1.addCard(new Card(Pip.CLOVERS, 4));
        hand1.addCard(new Card(Pip.HEARTS, 2));
        hand1.addCard(new Card(Pip.CLOVERS, 2));
        hand2 = new Hand();
        hand2.addCard(new Card(Pip.TILES, 4));
        hand2.addCard(new Card(Pip.PIKES, 4));
        hand2.addCard(new Card(Pip.TILES, 2));
        hand2.addCard(new Card(Pip.PIKES, 2));

        assertFalse(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));


        // hand 1 wins with three of a kind
        hand1 = new Hand();
        hand1.addCard(new Card(Pip.HEARTS, 3));
        hand1.addCard(new Card(Pip.CLOVERS, 3));
        hand1.addCard(new Card(Pip.TILES, 3));
        hand1.addCard(new Card(Pip.HEARTS, 2));
        hand2 = new Hand();
        hand2.addCard(new Card(Pip.TILES, 4));
        hand2.addCard(new Card(Pip.PIKES, 4));
        hand2.addCard(new Card(Pip.CLOVERS, 2));
        hand2.addCard(new Card(Pip.PIKES, 2));

        assertTrue(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));


        // hand 1 wins with a straight
        hand1 = new Hand();
        hand1.addCard(new Card(Pip.HEARTS, 2));
        hand1.addCard(new Card(Pip.CLOVERS, 3));
        hand1.addCard(new Card(Pip.HEARTS, 4));
        hand1.addCard(new Card(Pip.HEARTS, 5));
        hand1.addCard(new Card(Pip.HEARTS, 6));
        hand2 = new Hand();
        hand2.addCard(new Card(Pip.TILES, 4));
        hand2.addCard(new Card(Pip.PIKES, 4));
        hand2.addCard(new Card(Pip.CLOVERS, 4));
        hand2.addCard(new Card(Pip.PIKES, 2));

        assertTrue(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));


        // hand 1 wins with a flush
        hand1 = new Hand();
        hand1.addCard(new Card(Pip.PIKES, 2));
        hand1.addCard(new Card(Pip.PIKES, 3));
        hand1.addCard(new Card(Pip.PIKES, 5));
        hand1.addCard(new Card(Pip.PIKES, 6));
        hand1.addCard(new Card(Pip.PIKES, 8));
        hand2 = new Hand();
        hand2.addCard(new Card(Pip.HEARTS, 2));
        hand2.addCard(new Card(Pip.CLOVERS, 3));
        hand2.addCard(new Card(Pip.HEARTS, 4));
        hand2.addCard(new Card(Pip.HEARTS, 5));
        hand2.addCard(new Card(Pip.HEARTS, 6));

        assertTrue(hand1.isBetterThan(hand2));
        assertFalse(hand2.isBetterThan(hand1));



    }


}
