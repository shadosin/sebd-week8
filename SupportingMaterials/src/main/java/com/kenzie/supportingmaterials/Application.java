package com.kenzie.supportingmaterials;

import com.kenzie.supportingmaterials.animals.Alligator;
import com.kenzie.supportingmaterials.calendar.Calendar;
import com.kenzie.supportingmaterials.calendar.DayOfWeek;
import com.kenzie.supportingmaterials.tickets.VIPTicket;


public class Application {
    public static void overridingString() {
        Dice dice = new Dice(1,5);
        System.out.println(dice);
    }

    public static void ticketPractice() {
        VIPTicket vipTicket = new VIPTicket();

        // TODO print each property
        System.out.println(vipTicket.event);
        System.out.println(vipTicket.getPrice());
        System.out.println(vipTicket.getIsValid());
        System.out.println(vipTicket.getName());
        System.out.println(vipTicket.getGiveaway());
    }

    public static void appointmentPractice() {
        Appointment myAppt = new Appointment();

        myAppt.setAppointmentStatus("active");
        System.out.println("Current status: " + myAppt.getAppointmentStatus());

    }

    public static void alligatorPractice() {
        Alligator alligator = new Alligator();
        alligator.makeSound();


    }

    public static void constantPractice() {
        /*
        Write each of these values as a constant variable declaration:
        * dozen = 12
        * destination = "Oregon"
        * maximum days = 100
        * difficulty mode = "Advanced"
        */
        final int dozen = 12;
        final String destination = "Oregon";
        final int maxDays = 100;
        final String difficultyMode = "Advanced";
        System.out.println(dozen);
        System.out.println(destination);
        System.out.println(maxDays);
        System.out.println(difficultyMode);
    }

    public static void calendarPractice() {
        Calendar currentDay = new Calendar(Calendar.Month.March, DayOfWeek.Wednesday);

        System.out.println(currentDay);
    }

    public static void main(String[] args) {
        // TODO run your methods here
    }
}