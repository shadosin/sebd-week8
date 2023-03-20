package com.kenzie.oregontrail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/*
This program should have the following statics and constants

 * TOTAL_MILES, the total miles required to reach Oregon
 * MILES_PER_DAY, total miles travelled per day
 * FOOD_EXCHANGE, how much food the travelers give each other each time
 * MAX_DAYS, the maximum number of days to reach Oregon or bust
 * WAGON_SIZE, the total capacity -- choose either 4, 8 or 12

 The number of each type of Traveler is up to you to set.
 All three numbers together should total up to the value of `WAGON_SIZE`

 * NUM_TRAVELERS, how many regular travelers
 * NUM_CARPENTER, how many Carpenters
 * NUM_RANGER, how many Rangers
 */


@SuppressWarnings("unchecked")
public class Application {
    /*** Declare Statics and Constants Here ***/
    static final int TOTAL_MILES = 250;
    static final int MILES_PER_DAY = 20;
    static final int FOOD_EXCHANGE = 2;
    static final int  MAX_DAYS = 23;
    static final int WAGON_SIZE = 8;
    static final int NUM_TRAVELERS = 3;
    static final int NUM_RANGERS = 3;
    static final int NUM_CARPENTERS = 2;


    // You go hunting every X days ( you can change this as you play )
    public static final int TRAPPING_FREQUENCY = 3;


    @SuppressWarnings("unchecked")
    public static void main (String[] args) throws Exception {

        // ---------------- Oregon Trail Part One ----------------
        // This represents one day on the oregon trail
        try {

            Constructor<Traveler> travelerConstructor = Traveler.class.getConstructor(String.class);
            Traveler billyBob = travelerConstructor.newInstance("Billy Bob");
            Traveler ezra = travelerConstructor.newInstance("Ezra");
            Traveler horace = travelerConstructor.newInstance("Horace");
            Traveler sylvia = travelerConstructor.newInstance("Sylvia");

            Constructor<Carpenter> subclassAConstructor = Carpenter.class.getConstructor(String.class);
            Carpenter bobVila = subclassAConstructor.newInstance("Bob Vila");

            Constructor<Ranger> rangerConstructor = Ranger.class.getConstructor(String.class);
            Ranger steveRinella = rangerConstructor.newInstance("Steve Rinella");

            Constructor<Wagon> wagonConstructor = Wagon.class.getConstructor();

            // Break the wagon so the carpenter has something to do
            Wagon wagon = wagonConstructor.newInstance();
            Method breakWagon = Wagon.class.getMethod("breakWagon");
            breakWagon.invoke(wagon);

            Traveler[] travelers = new Traveler[]{ billyBob, ezra, horace, sylvia, steveRinella, bobVila };

            oregonTrailOneDay(travelers, wagon, true);

        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            System.out.println("One-Day Error: missing class or constructor");
            throw e;
        }

        // ---------------- Oregon Trail Part Two ----------------
        // This uses the one day method above, to play the entire game!
        // TODO uncomment this when you're ready
         oregonTrailPlayGame();
    }

    /*** DO NOT CHANGE THE CODE BELOW THIS LINE ***/

    @SuppressWarnings("unchecked")
    public static void oregonTrailOneDay(Traveler[] travelers, Wagon wagon, boolean trapDay) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        if(trapDay) {
            OregonTrailUtil.goTrapping(travelers);
        }

        OregonTrailUtil.feedWagon(travelers);
        OregonTrailUtil.fixWagon(travelers, wagon);
        OregonTrailUtil.printWagonTravelerStatus(travelers);
    }

    @SuppressWarnings("unchecked")
    public static void oregonTrailPlayGame() throws Exception {
        try {

            // ---------------- Constants and Class Variables ----------------
            int wagon_size = (int) OregonTrailUtil.getStaticFieldValue("WAGON_SIZE");
            int max_days = (int) OregonTrailUtil.getStaticFieldValue("MAX_DAYS");
            int food_exchange = (int) OregonTrailUtil.getStaticFieldValue("FOOD_EXCHANGE");
            int miles_per_day = (int) OregonTrailUtil.getStaticFieldValue("MILES_PER_DAY");
            int total_miles = (int) OregonTrailUtil.getStaticFieldValue("TOTAL_MILES");

            int numTravelers = (int) OregonTrailUtil.getStaticFieldValue("NUM_TRAVELERS");
            int numCarpenters = (int) OregonTrailUtil.getStaticFieldValue("NUM_CARPENTERS");
            int numRangers = (int) OregonTrailUtil.getStaticFieldValue("NUM_RANGERS");

            int days_travelled = 0;
            int miles_travelled = 0;

            // ---------------- PLAY Load Wagon, Get Travelers ----------------

            Constructor<Wagon> wagonConstructor = Wagon.class.getConstructor(int.class);
            Wagon wagon = wagonConstructor.newInstance(wagon_size);

            OregonTrailUtil.loadWagon(wagon, numTravelers, numCarpenters, numRangers);
            OregonTrailUtil.displayStatus(wagon, days_travelled, miles_travelled);

            Method getPassengers = Wagon.class.getMethod("getPassengers");
            Method breakWagon = Wagon.class.getMethod("breakWagon");
            Method isWagonBroken = Wagon.class.getMethod("isBroken");

            Traveler[] passengers;

            try {
                passengers = (Traveler[]) getPassengers.invoke(wagon);
            } catch (Exception e) {
                ArrayList<Traveler> arrayList = (ArrayList<Traveler>) getPassengers.invoke(wagon);
                passengers = (arrayList).toArray(new Traveler[arrayList.size()]);
            }

            // ---------------- PLAY Day by Day ----------------

            for (int i = 0; (i < max_days) && (miles_travelled < total_miles); i++) {

                days_travelled = days_travelled + 1;
                boolean trapDay = i % TRAPPING_FREQUENCY == 0;

                try {

                    Application.oregonTrailOneDay(passengers, wagon, trapDay);

                    // Quarantine
                    Method shouldQuarantineMethod = Wagon.class.getMethod("shouldQuarantine");
                    boolean shouldQuarantine = (boolean) shouldQuarantineMethod.invoke(wagon);

                    if (shouldQuarantine) {
                        OregonTrailUtil.quarantineCare(passengers, food_exchange);
                    }

                    boolean wagonIsBroken = (boolean) isWagonBroken.invoke(wagon);

                    // Count miles travelled
                    if (!shouldQuarantine && !trapDay && !wagonIsBroken) {
                        miles_travelled = miles_travelled + miles_per_day;
                    }

                    // Break the wagon randomly
                    boolean wagonBreakDay = new Random().nextInt(100) % 5 == 3;

                    if(wagonBreakDay) {
                        System.out.println(Script.FIX_IT_TOMORROW);
                        breakWagon.invoke(wagon);
                    }

                } catch (Exception e) {
                    System.out.println("One-Day Error: missing class, method, or constructor");
                }

                OregonTrailUtil.displayStatus(wagon, days_travelled, miles_travelled);
            }


            // ---------------- PLAY Game End ----------------

            OregonTrailUtil.endGame(miles_travelled, total_miles);

        } catch (NoSuchElementException | NoSuchMethodException | InstantiationException e) {
            System.out.println("Play-Game Error: There are required elements that are missing. Finish coding all required elements before playing the entire game");
            throw e;
        }
    }
}
