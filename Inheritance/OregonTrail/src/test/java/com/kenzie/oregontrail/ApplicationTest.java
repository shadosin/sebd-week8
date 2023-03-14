package com.kenzie.oregontrail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ApplicationTest {

    @DisplayName("addedConstantVariables: Required Constants added")
    @Test
    void addedConstantVariables() {
        try {
            int wagon_size = (int) OregonTrailUtil.getStaticFieldValue("WAGON_SIZE");
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("WAGON_SIZE static final variable needs to be defined");
        }

        try {
            int max_days = (int) OregonTrailUtil.getStaticFieldValue("MAX_DAYS");
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("MAX_DAYS static final variable needs to be defined");
        }

        try {
            int food_exchange = (int) OregonTrailUtil.getStaticFieldValue("FOOD_EXCHANGE");
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("FOOD_EXCHANGE static final variable needs to be defined");
        }

        try {
            int miles_per_day = (int) OregonTrailUtil.getStaticFieldValue("MILES_PER_DAY");
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("MILES_PER_DAY static final variable needs to be defined");
        }

        try {
            int total_miles = (int) OregonTrailUtil.getStaticFieldValue("TOTAL_MILES");
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("TOTAL_MILES static final variable needs to be defined");
        }

        try {
            int num_travelers = (int) OregonTrailUtil.getStaticFieldValue("NUM_TRAVELERS");
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("NUM_TRAVELERS static final variable needs to be defined");
        }

        try {
            int num_doctors = (int) OregonTrailUtil.getStaticFieldValue("NUM_RANGERS");
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("NUM_RANGERS static final variable needs to be defined");
        }

        try {
            int num_hunters = (int) OregonTrailUtil.getStaticFieldValue("NUM_CARPENTERS");
        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("NUM_CARPENTERS static final variable needs to be defined");
        }
    }
}