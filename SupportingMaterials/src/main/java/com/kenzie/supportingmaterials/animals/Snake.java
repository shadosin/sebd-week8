package com.kenzie.supportingmaterials.animals;

class Snake extends Reptile{
    Snake(){
        super(0);
    }

    @Override
    public void makeSound() {
        super.makeSound();
        System.out.println("Hiss.");
    }
}
