package com.kenzie.oregontrail;

import java.util.Random;

/*
Ranger is a child class of Traveler with two additional methods.

Rules
    - The default name should be "Ranger"
    - Starts with 4 food

Constructors (calls super)
    - empty constructor
    - constructor that takes a string name

Methods
    - trap(), increases food supply by 4
    - forageMedicine()
            - takes another traveler as a parameter
            - takes an amount of medicine that the traveler requests
            - foraging is hard! heal the other traveler ONLY if a random number generator matches the amount of medicine requested
            - if (medicine requested equals a random number generated) then the traveler is healthy
    - cook()
            - take a traveler
            - takes an integer amount of food that the traveler requests
            - if hunter has less food than requested, no food should be transferred
            - if the hunter has enough food, the food is transferred
 */

public class Ranger extends Traveler {

}
