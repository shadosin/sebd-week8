package com.kenzie.oregontrail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.lang.reflect.*;
import java.util.Random;


// TODO - Do not change code in this file. These helper methods CALL the class code you write
@SuppressWarnings("unchecked")
public class OregonTrailUtil {

    // ----------------- One Day Methods -----------------
    @SuppressWarnings("unchecked")
    public static void feedWagon(Traveler[] passengerArray) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method hunterEat = Ranger.class.getMethod("eat");
        Method carpenterEat = Carpenter.class.getMethod("eat");
        Method travelerEat = Traveler.class.getMethod("eat");

        // Call specific class versions of eat to ensure inheritance relationship
        for (Traveler person : passengerArray) {
            if (person != null) {
                if (person instanceof Ranger) {
                    hunterEat.invoke(person);
                } else if (person instanceof Carpenter) {
                    carpenterEat.invoke(person);
                } else {
                    travelerEat.invoke(person);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void fixWagon(Traveler[] travelers, Wagon wagon) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method wagonGetIsBrokenMethod = Wagon.class.getMethod("isBroken");
        Method carpenterFixMethod = Carpenter.class.getMethod("tryToFixWagon", Wagon.class, Traveler.class);

        boolean isWagonBroken = (boolean) wagonGetIsBrokenMethod.invoke(wagon);

        // Wagon will randomly break >:)
        // The carpenter needs help from one other traveler to fix it
        // If the traveler is sick, they can't help, the wagon stays broken

        Carpenter carpenter = null;

        for(Traveler t : travelers) {
            if(t instanceof Carpenter) {
                carpenter = (Carpenter) t;
                break;
            }
        }

        if(carpenter == null) {
            System.out.println("You don't have a carpenter! Wagon broken forever");
            return;
        }

        if(isWagonBroken) {
            System.out.println("Oh no! The wagon is broken!");

            // Get random traveler and name
            int randomTravelerIndex = new Random().nextInt(100) % travelers.length;
            Traveler randomTraveler = travelers[randomTravelerIndex];
            Method travelerGetNameMethod = Traveler.class.getMethod("getName");
            String travelerName = (String) travelerGetNameMethod.invoke(randomTraveler);

            // Try to fix the wagon
            carpenterFixMethod.invoke(carpenter, wagon, randomTraveler);

            // Success?
            if (!(boolean) wagonGetIsBrokenMethod.invoke(wagon)) {
                System.out.println("The wagon is fixed with the help of " + travelerName);
            } else {
                System.out.println("Oh no! The carpenter and " + travelerName + " didn't fix the wagon");
            }
        }

    }

    @SuppressWarnings("unchecked")
    public static void goTrapping(Traveler[] passengerArray) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method rangerTrapMethod = Ranger.class.getMethod("trap");
        Method travelerTrapMethod = Traveler.class.getMethod("trap");

        for (Traveler person : passengerArray) {
                if (person instanceof Ranger) {
                    rangerTrapMethod.invoke(person);
                } else {
                    travelerTrapMethod.invoke(person);
                }
        }
    }

    @SuppressWarnings("unchecked")
    public static void printWagonTravelerStatus(Traveler[] travelers) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Object traveler : travelers) {

            String travelerName = (String) Traveler.class.getMethod("getName").invoke(traveler);
            System.out.println("Traveler: " + travelerName);
            System.out.println("\thas " + Traveler.class.getMethod("getFood").invoke(traveler) + " food");

            if ((boolean) Traveler.class.getMethod("isHealthy").invoke(traveler)) {
                System.out.println("\tis very healthy");
            } else {
                System.out.println("\tis not healthy");
            }
        }
    }

    // ----------------- Play Game Methods -----------------

    @SuppressWarnings("unchecked")
    public static void loadWagon(Wagon wagon, int num_travelers, int num_doctors, int num_hunters) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method loadWagon = Wagon.class.getMethod("allAboard", int.class, int.class, int.class);

        if (num_travelers + num_doctors + num_hunters > 0) {
            loadWagon.invoke(wagon,num_travelers, num_doctors, num_hunters);
        } else {
            System.out.println("Number of travelers = 0. No one to load in wagon.");
            System.out.println("The journey of a thousand miles starts with a single step. " +
                    "Better luck next time.");
            System.exit(0);
        }

    }

    public static void quarantineCare(Traveler[] passengerArray, int request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getIsHealthy = Traveler.class.getMethod("isHealthy");

        for (Traveler person : passengerArray) {

            // Rangers will try to forage medicine and feed
            if (person instanceof Ranger) {
                boolean foraged = rangerTry(Ranger.class.getMethod("forageMedicine", Traveler.class, int.class), getIsHealthy, passengerArray, person, request);
                boolean healed = rangerTry(Ranger.class.getMethod("cook", Traveler.class, int.class), getIsHealthy, passengerArray, person, request);
                if(foraged || healed) {
                    break;
                }
            }
        }
    }

    public static boolean rangerTry(Method specialMethod, Method isHealthy, Traveler[] passengerArray, Traveler r, int amount) throws InvocationTargetException, IllegalAccessException {
        for (Traveler fellowTraveler : passengerArray) {
            if (!((boolean) isHealthy.invoke(fellowTraveler))) {
                specialMethod.invoke(r, fellowTraveler, amount);
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static void displayStatus (Wagon wagon, int daysTravelled, int milesTravelled) throws Exception {
        Method shouldQuarantine = Wagon.class.getMethod("shouldQuarantine");

        System.out.println("*************************************");
        System.out.println("Days travelled:"+ daysTravelled);
        System.out.println("Miles travelled:"+ milesTravelled);
        System.out.println("Quarantined?:" + ((boolean)shouldQuarantine.invoke(wagon)) );
        System.out.println("Remaining miles to Oregon:" + ((int) OregonTrailUtil.getStaticFieldValue("TOTAL_MILES") - milesTravelled));
    }

    @SuppressWarnings("unchecked")
    public static void endGame(int miles_travelled, int total_miles) {
        System.out.println("**********************OREGON TRAIL****************************");
        System.out.println("The wagon traveled: " + miles_travelled + " miles.");
        System.out.println("You needed to travel a total of " + total_miles + " to make it to Oregon");
        System.out.println("**************************************************************");

        if (miles_travelled >= total_miles) {
            System.out.println("Your wagon made it to Oregon. Congratulations!");
        } else {
            System.out.println("Time has run out! You've died a horrible death on the Oregon Trail.");
        }
    }

    // ----------------- Util -----------------

    static int getStaticFieldValue(String path) throws NoSuchElementException {
        try {
            Field f = Application.class.getDeclaredField(path);
            return f.getInt(null);
        }
        catch(Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }
}