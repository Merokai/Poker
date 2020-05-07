package org.ipi.poker;

public class Card {
    private Pip pip;
    private int value; // 1 to 14

    public Card(Pip pip, int value) {
        this.pip = pip;
        this.value = value;
    }

    public Pip getPip() {
        return pip;
    }

    public int getValue() {
        return value;
    }
}
