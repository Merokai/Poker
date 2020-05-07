package org.ipi.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    void hasPip() {
        Card card = new Card(Pip.CLOVERS, 4);
        assertNotNull(card.getPip());

        assertEquals(Pip.CLOVERS, new Card(Pip.CLOVERS, 4).getPip());
        assertEquals(Pip.PIKES, new Card(Pip.PIKES, 4).getPip());
        assertEquals(Pip.HEARTS, new Card(Pip.HEARTS, 4).getPip());
        assertEquals(Pip.TILES, new Card(Pip.TILES, 4).getPip());
        assertThrows(IllegalArgumentException.class, () -> new Card(null, 1));
    }

    @Test
    void hasValue() {
        for (int i = 0; i < 20; i++) {
            int value = i;
            if (value < 1 || value > 13) {
                assertThrows(IllegalArgumentException.class, () -> new Card(Pip.TILES, value));
            } else {
                assertEquals(i, new Card(Pip.TILES, value).getValue());
            }
        }
    }

    @Test void areEquals(){
        Card card = new Card(Pip.TILES, 2);
        Card sameCard = new Card(Pip.TILES, 2);
        Card differentPip = new Card(Pip.HEARTS, 2);
        Card differentValue = new Card(Pip.TILES, 3);

        assertEquals(card, sameCard);
    }
}
