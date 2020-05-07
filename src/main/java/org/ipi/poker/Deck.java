package org.ipi.poker;

public class Deck {
    public static final int SIZE = 52;

    private final int offset;

    private int count = SIZE;

    public Deck() {
        this.offset = generateOffset();
    }

    public int count() {
        return count;
    }

    public Card drawOne() {
        if (count < 1) {
            throw new IllegalStateException();
        }
        count--;
        return new Card(Pip.values()[(offset + count) % 4], 1 + (offset + count) % 13);
    }

    private int generateOffset() {
        return (int) (Math.random() * SIZE);
    }
}
