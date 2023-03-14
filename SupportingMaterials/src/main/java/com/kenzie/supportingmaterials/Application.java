package com.kenzie.supportingmaterials;

import com.kenzie.supportingmaterials.calendar.Calendar;
import com.kenzie.supportingmaterials.tickets.VIPTicket;


public class Application {
    public static void overridingString() {
        Dice dice = new Dice(1,5);
        System.out.println(dice);
    }

    public static void ticketPractice() {
        VIPTicket vipTicket = new VIPTicket();

        // TODO print each property

    }

    public static void appointmentPractice() {
        Appointment myAppt = new Appointment();

        myAppt.setAppointmentStatus("active");
        System.out.println("Current status: " + myAppt.getAppointmentStatus());

    }

    public static void alligatorPractice() {


    }

    public static void constantPractice() {
        /*
        Write each of these values as a constant variable declaration:
        * dozen = 12
        * destination = "Oregon"
        * maximum days = 100
        * difficulty mode = "Advanced"
        */
    }

    public static void calendarPractice() {
        Calendar currentDay = new Calendar();

        System.out.println(currentDay);
    }

    public static void main(String[] args) {
        // TODO run your methods here
    }
}