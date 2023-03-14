package com.kenzie.supportingmaterials.tickets;

public class Ticket {
    public String event;
    protected double price;
    private boolean isValid;

    public Ticket(){

    }

    public Ticket(String event, double price){
        this.event = event;
        this.price = price;
        this.isValid = false;
    }

    public void activateTicket(){
        this.isValid = true;
    }

    public boolean getIsValid(){
        return this.isValid;
    }

    public double getPrice(){
        return this.price;
    }
}
