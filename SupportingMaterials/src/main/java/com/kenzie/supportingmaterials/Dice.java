package com.kenzie.supportingmaterials;

import java.util.Random;

public class Dice {
    private int die1;
    private int die2;

    public Dice(int x, int y) {
        die1 = x;
        die2 = y;
    }

    public int rollDice() {
        Random rand = new Random();

        this.die1 = rand.nextInt(6) + 1;
        this.die2 = rand.nextInt(6) + 1;

        return this.die1 + this.die2;
    }

    public int rollDice(int x) {
        // TODO
        Random rand = new Random();
        this.die1 = rand.nextInt(x) +1;
        this.die2 = rand.nextInt(x) +1;
        return -1;
    }
@Override
    public String toString(){
        return "(" + die1 + "," + die2 + ")";
}
    // TODO override toString
}