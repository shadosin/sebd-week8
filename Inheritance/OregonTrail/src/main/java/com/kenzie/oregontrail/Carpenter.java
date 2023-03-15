package com.kenzie.oregontrail;

/*
Carpenter is a child class of Traveler with one additional method.

Rules
    - The default name should be "Carpenter"
    - Eats 2 food when eat() is called
        - if the Traveler tries to eat when food is 0, isHealthy is false
        - if the Traveler tries to eat when food is 1, food goes to 0, they stay healthy

Constructors (calls super)
    - empty constructor
    - constructor that takes a string name

Methods
    - tryToFixWagon, takes a wagon and another traveler as parameters. The two try to fix the wagon.

        The wagon is fixed if -
            - The carpenter can eat 2 food
            - The other traveler is healthy
            - Use Wagon.fixWagon() (this should already be written for you)

        The wagon can't be fixed if -
            - The carpenter has less than 2 food
            - The other traveler isn't healthy
            - If the wagon isn't fixed, print the SHOOT value of the Script enum

        If the carpenter is left with 0 food, they become unhealthy
 */
public class Carpenter extends Traveler {
    public Carpenter(){
        super("Carpenter");
    }
    public Carpenter(String name){
        super(name);

    }
    @Override
    public void eat(){

        if(food >= 2){
            food -= 2;
            isHealthy=true;
        }else{
            super.eat();
        }

    }
    public void tryToFixWagon(Wagon wagon, Traveler other){
        if (food >= 2 && other.isHealthy()){
            wagon.fixWagon();
            eat();
        }else{
            System.out.println(Script.SHOOT.message);
        }
    }
    }

