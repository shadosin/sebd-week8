package com.kenzie.oregontrail;

/*
Class Variables
    - isBroken, if the wagon is broken.
    - capacity, the number of seats on the wagon
    - passengers, a list of all passengers. can only hold up to <capacity> number of passengers

Constructors
    - empty constructor,
        - sets the capacity to 10
        - isBroken to false
        - empty passenger list of size 10
    - constructor that takes one int
        - set the int to the wagon capacity
        - create an empty passenger list of size capacity

Methods
    - standard getters and setters
    - setCapacity() should remove passengers if the capacity becomes smaller. Remove passengers from the back.
    - addTraveler(), adds a traveler to an empty seat.
            - If no seats are empty, do not add the traveler
            - Jane always sits in front. If the passenger's name is "Jane", add to the front of the passenger array
    - shouldQuarantine(), returns true if any passenger is sick
    - totalFood(), sum of the total food of all passengers
    - isThereRoom(), returns true if there are empty seats on the wagon
    - allAboard(), initialize the wagon with passengers. This will take 3 parameters -
        - The number of Travelers
        - The number of Carpenters
        - The number of Rangers
        - Add until the Wagon is full. Do not add more travelers than the wagon capacity
        - Load rangers first, then carpenters, then travelers

Pre-Made Methods
    - fixWagon(), sets isBroken to false
*/

public class Wagon {
    private boolean isBroken;

    public boolean isBroken() {
        return this.isBroken;
    }

    public void breakWagon() {
        this.isBroken = true;
    }

    public void fixWagon() {
        this.isBroken = false;
    }
}
