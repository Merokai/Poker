package org.ipi.poker;

import org.junit.jupiter.api.Test;

import java.util.Collection;
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

    @Test
    void drawingCards() {
        Deck deck = new Deck();
        Card card = deck.drawOne();

        assertNotNull(card);

        Card card2 = deck.drawOne();
        assertNotEquals(card, card2);

        Set<Card> eachCard = new HashSet<>();
        eachCard.add(card);
        eachCard.add(card2);
        while (deck.count() > 0) {
            eachCard.add(deck.drawOne());
        }
        assertEquals(Deck.SIZE, eachCard.size());
    }

    @Test void firstCardDrawnChanges(){
        final int A_HUNDRED_THOUSAND = 100000;

        Set<Card> firstPick = new HashSet<>();

        for(int i=0;i<A_HUNDRED_THOUSAND;i++){
            firstPick.add(new Deck().drawOne());
        }

        assertEquals(Deck.SIZE, firstPick.size());
    }

    @Test void cardPickOrderChanges(){
        final int A_HUNDRED_THOUSAND = 100000;
        Set<Integer> valueShifts = new HashSet<>();
        Set<Integer> pipShifts = new HashSet<>();

        for(int i=0;i<A_HUNDRED_THOUSAND;i++){
            Deck deck = new Deck();
            Card card1 = deck.drawOne();
            Card card2 = deck.drawOne();
            valueShifts.add(card1.getValue() - card2.getValue());
            pipShifts.add(card1.getPip().ordinal() - card2.getPip().ordinal());
        }

        assertNotEquals(2, valueShifts.size());
        assertNotEquals(2, pipShifts.size());
    }

    @Test void drawAHand(){
        Deck deck = new Deck();
        Collection<Card> hand = deck.drawFive();

        assertEquals(5, hand.size());
    }

}
