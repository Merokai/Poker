package org.ipi.poker;

public class Card {
    private Pip pip;
    private int value; // 1 to 14

    public Card(Pip pip, int value) {
        if (value < 1 || value > 13 || pip == null) {
            throw new IllegalArgumentException();
        }
        this.pip = pip;
        this.value = value;
    }

    public Pip getPip() {
        return pip;
    }

    public int getValue() {
        return value;
    }

    public int getScore() {
        return value == 1 ? 14 : value;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash *= 13 + pip.hashCode();
        hash *= 17 + Integer.hashCode(value);
        return Integer.hashCode(hash);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == Card.class && obj.hashCode() == hashCode();
    }

    @Override
    public String toString() {
        return String.format("Card: {%s %s}", pip, value);
    }
}
