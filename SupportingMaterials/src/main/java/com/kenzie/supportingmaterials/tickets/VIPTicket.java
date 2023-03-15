package com.kenzie.supportingmaterials.tickets;

public class VIPTicket extends Ticket {
    private String name;
    private String giveaway;

    public VIPTicket() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public String getGiveaway() {
        return this.giveaway;
    }

    public void createVIPTicket(String name, String event) {
        // TODO fill me out
        this.name = name;
        this. event = event;
        this.price = 0.0;
        this.activateTicket();
        this.giveaway = "white rabbit";
    }

}
