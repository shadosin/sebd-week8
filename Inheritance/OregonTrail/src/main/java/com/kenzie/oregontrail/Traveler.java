package com.kenzie.oregontrail;

/*
Child classes - Carpenter and Ranger
*/


/*
Variables (set to not public) -
    - name, a string set to the default of "Jane"
    - food, a number with a default value of 1
    - isHealthy, a boolean that shows if the traveler is sick or not

Constructors
    - empty constructor
    - constructor that takes a string name

Methods
    - getters and setters for the class variables
        (pay attention to boolean getter and setter names)
    - trap(), increases the food by 3
    - eat(),
        - decreases the food variable by 1
        - If the traveler tries to eat and food is at 0, they are no longer healthy
    - speak, the traveler says any phrase from the script enum
        (find the script enum in the file named Script.java)
 */

import java.util.Random;

public class Traveler {
    protected String name = "Jane";
    protected int food = 1;
    protected boolean isHealthy;
    public Traveler() {
        this.name = name;
        this.food = 1;
        this.isHealthy = true;
    }

    public Traveler(String name) {
        this.name = name;
        this.food = 1;
        this.isHealthy = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }
    public void trap(){
        food += 3;
    }
    public void eat(){

        if (this.food == 0) {
            this.isHealthy = false;
        } else {
            this.food--;
            isHealthy=true;
        }
    }
    public String speak(){
        Random random = new Random();
        Script[] scripts = Script.values();
        Script randomScript = scripts[random.nextInt(scripts.length)];
        return randomScript.message;
    }
}