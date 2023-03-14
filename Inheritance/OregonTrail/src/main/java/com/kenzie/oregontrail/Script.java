package com.kenzie.oregontrail;

/*
    Add and use as many script items as you'd like
*/

public enum Script {
    YEEHAW("Yeehaw! Love being on the open range!"),
    MONOLOG("Wow, traveling by wagon is boring."),
    NO_FOOD_FOR_YOU("Sorry pal, no food for you!"),
    SHOOT("Shoot! We can't fix the wagon"),
    DYING_BREATH("Before I leave this world, I want you to know my deepest secret....");

    String message;

    Script(String message) {
        this.message = message;
    }
}
