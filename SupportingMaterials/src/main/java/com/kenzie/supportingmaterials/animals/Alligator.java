package com.kenzie.supportingmaterials.animals;

public class Alligator extends Reptile {
    public Alligator() {
        super(4);
    }

    public void makeSound() {
        // call parent method
        super.makeSound();
        System.out.println("Chomp.");
        super.makeSound();
    }
}
