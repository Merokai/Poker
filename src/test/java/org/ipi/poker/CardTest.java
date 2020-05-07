package org.ipi.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardTest {

    @Test
    void hasPip() {
        Card card = new Card(Pip.CLOVERS, 4);
        assertNotNull(card.getPip());

        assertEquals(Pip.CLOVERS, new Card(Pip.CLOVERS, 4).getPip());
        assertEquals(Pip.PIKES, new Card(Pip.PIKES, 4).getPip());
        assertEquals(Pip.HEARTS, new Card(Pip.HEARTS, 4).getPip());
        assertEquals(Pip.TILES, new Card(Pip.TILES, 4).getPip());
    }
}
