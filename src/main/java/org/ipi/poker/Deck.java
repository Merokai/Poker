package org.ipi.poker;

public class Deck {
    public static final int SIZE = 52;

    private int count = SIZE;

    public int count() {
        return count;
    }

    public Card drawOne() {
        if (count < 1) {
            throw new IllegalStateException();
        }
        count--;
        return null;
    }
}
