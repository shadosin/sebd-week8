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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Wagon {
    private int capacity;
    private Traveler[] passengers;
    private boolean isBroken;

    public  Wagon(){
        this.capacity = 10;
        this.isBroken = false;
        this.passengers = new Traveler[capacity];
    }
    public Wagon(int capacity){
        this.capacity = capacity;
        this.isBroken = false;
        this.passengers = new Traveler[capacity];
    }


    public int getCapacity() {
        return capacity;
    }

    public void setPassengers(Traveler[] passengers) {
        this.passengers = passengers;
    }

    public void setCapacity(int updatedCapacity) {
        if(this.capacity == updatedCapacity){
            return;
        }
        //worked with author: Leanne Hayes
        if(this.capacity < updatedCapacity){
            this.capacity = updatedCapacity;
            Traveler[] largerCap = new Traveler[updatedCapacity];
            System.arraycopy(passengers, 0, largerCap, 0, passengers.length);
            this.passengers = largerCap;
        }else{
            Traveler[] setCap = new Traveler[updatedCapacity];
            System.arraycopy(passengers, 0, setCap, 0, updatedCapacity);
            this.passengers = setCap;
        }

    }




    public void addTraveler(Traveler traveler){
        if(traveler.getName().equals("Jane")){
            if(passengers[0]== null){
                passengers[0] = traveler;
            }
        }else{
            for(int i = 1; i < passengers.length; i++){
                if(passengers[i]==null){
                    passengers[i] = traveler;
                    break;
                }
            }
        }
    }
    public boolean shouldQuarantine(){
        for(Traveler traveler: passengers){
            if(traveler != null && !traveler.isHealthy()){
                return true;
            }
        }
        return false;
    }
    public int totalFood(){
        int total = 0;
        for(Traveler traveler: passengers){
            if(traveler != null){
                total += traveler.getFood();
            }
        }

        return total;
    }
    public boolean isThereRoom(){
        for(Traveler traveler:passengers){
            if(traveler == null){
                return true;
            }
        }
        return false;
    }
    public void allAboard(int numTravelers, int numCarpenters, int numRangers){
        int i = 0;
        while (i < numRangers && isThereRoom()){
            addTraveler(new Ranger());
            i++;
        }
        int j = 0;
        while(j < numCarpenters && isThereRoom()){
            addTraveler(new Carpenter());
            j++;
        }
        int k = 0;
        while(k < numTravelers && isThereRoom()){
            addTraveler(new Traveler());
            k++;
        }

    }


    public Traveler[] getPassengers() {
        return passengers;
    }



    public void setBroken(boolean broken) {
        isBroken = broken;
    }

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
