package com.kenzie.supportingmaterials.calendar;

public class Calendar {
    private Month month;
    private DayOfWeek day;

    // TODO Define enum Month for all twelve months here
    public enum Month {
        January,
        February,
        March,
        April,
        May,
        June,
        July,
        August,
        September,
        October,
        November,
        December

    }

    public Calendar() {

    }

    public Calendar(Month month, DayOfWeek day){
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "month=" + month +
                ", day=" + day +
                '}';
    }
}
