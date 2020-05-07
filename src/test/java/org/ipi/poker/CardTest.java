package org.ipi.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardTest {

    @Test
    void hasPip() {
        Card card = new Card(Pip.CLOVERS, 4);
        assertNotNull(card.getPip());
    }
}
