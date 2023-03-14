package com.kenzie.oregontrail;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HunterTest {
    public static final int TEST_INITIAL_FOOD = 2;
    public static final int TEST_HUNT_FOOD = 7;
    public static final int TEST_NO_FOOD = 0;
    public static final int TEST_INITIAL_TRAVELER_FOOD = 1;
    public static final int TEST_INITIAL_HUNTER_FOOD = 2;
    public static final int TEST_GIVE_FOOD = 2;
    public static final int TEST_GIVE_FOOD_TOTAL = 3;
    public static final int TEST_REMAINING_FOOD_TOTAL = 0;
    public static final boolean TEST_ISHEALTHY = true;
    public static final boolean TEST_ISNOTHEALTHY = false;

    private String CLASSNAME = "Hunter";

    Object [] HUNTER_CLASS_CONSTRUCTOR_VALUES = {"Homer"};

    Class<?> [] HUNTER_CLASS_CONSTRUCTOR_TYPES = {String.class};

    Object [] EMPTY_HUNTER_CLASS_CONSTRUCTOR_VALUES = {};

    Class<?> [] EMPTY_HUNTER_CLASS_CONSTRUCTOR_TYPES = {};

    @Order(1)
    @Test
    void hunterClass_Extends_TravelerClass() throws NoSuchElementException{
        try {
            assertTrue(doesChildClassExtendParentClass(Hunter.class, Traveler.class), "The Hunter class should extend the Traveler class.");
        }
        catch(Exception e){
            assertTrue(false, "The Hunter class should extend the Traveler class.");
        }
    }

    @Order(2)
    @Test
    void hunterClass_hasZero_Parameter_Constructor() throws NoSuchElementException{
        try {
            getClassToConstruct(EMPTY_HUNTER_CLASS_CONSTRUCTOR_VALUES, EMPTY_HUNTER_CLASS_CONSTRUCTOR_TYPES, Hunter.class);
            String defaultName = getParentFieldValueInClassUsingReflection("name", EMPTY_HUNTER_CLASS_CONSTRUCTOR_VALUES, EMPTY_HUNTER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("Hunt", defaultName, "You did not set the default name to Hunt");

            String defaultFood = getParentFieldValueInClassUsingReflection("food", EMPTY_HUNTER_CLASS_CONSTRUCTOR_VALUES, EMPTY_HUNTER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("2", defaultFood, "You did not set the default food amount to 2");

            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false, "The Hunter class should have a Parameterless Constructor.");
        }
    }
    @Order(3)
    @Test
    void hunterClass_hasOne_StringParameter_Constructor() throws NoSuchElementException{
        try {
            getClassToConstruct(HUNTER_CLASS_CONSTRUCTOR_VALUES, HUNTER_CLASS_CONSTRUCTOR_TYPES, Hunter.class);
            String defaultName = getParentFieldValueInClassUsingReflection("name",HUNTER_CLASS_CONSTRUCTOR_VALUES, HUNTER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("Homer", defaultName, "You did not use super to set the name in your constructor");

            String defaultFood = getParentFieldValueInClassUsingReflection("food", HUNTER_CLASS_CONSTRUCTOR_VALUES, HUNTER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("2", defaultFood, "You did not set the default food amount to 2");

            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false, "The Hunter class should have a One Parameter Constructor that takes a single String parameter.");
        }
    }

    @Order(4)
    @Test
    void hunterClass_AllMethods_ExcludingGettersAndSetters_Exist() {
        try {
            Method giveFood = Hunter.class.getMethod("giveFood", Traveler.class, int.class);

            Method hunt =  Hunter.class.getMethod("hunt");

            Method eat =  Hunter.class.getMethod("eat");

            assertTrue(true);
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Hunter Class has the correct Constructors and that you have methods for hunt, eat, and giveFood in your Hunter Class. This excludes getter and setter methods for this test.");
        }
    }

    @DisplayName("Hunter class can be instantiated with no arguments")
    @Order(5)
    @Test
    void canCreateHunter() {
        try {
            Hunter hunter = getTestHunter();
            @SuppressWarnings("unchecked")
            Method getFood = Hunter.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method getIsHealthy = Hunter.class.getMethod("getIsHealthy");

            assertTrue(hunter instanceof Traveler, "Hunter is instanceof Traveler");
            assertEquals(TEST_INITIAL_FOOD, (int)getFood.invoke(hunter), "Hunter.food starts at 2" );
            assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(hunter), "Hunter.isHealthy starts at true");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Hunter class must be defined with getFood() and getIsHealthy() method");
        }
    }

    @DisplayName("Hunter class has implemented hunt")
    @Order(6)
    @Test
    void canHunt() {
        try {
            Hunter hunter = getTestHunter();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Hunter.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method hunt = Hunter.class.getMethod("hunt");

            hunt.invoke(hunter);
            assertEquals(TEST_HUNT_FOOD, (int)getFood.invoke(hunter), "Hunter.food after hunting is 7" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Hunter class must be defined with hunt() and getFood() methods");
        }
    }

    @DisplayName("Hunter class has implemented eat")
    @Order(7)
    @Test
    void canEat() {
        try {
            Hunter hunter = getTestHunter();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Hunter.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method setFood = Hunter.class.getMethod("setFood",int.class);
            @SuppressWarnings("unchecked")
            Method eat = Hunter.class.getMethod("eat");

            eat.invoke(hunter);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(hunter), "Hunter.food after eating is 0" );
            setFood.invoke(hunter,1);
            eat.invoke(hunter);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(hunter), "Hunter.food after overeating is still 0" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Hunter class must be defined with eat() and getFood() methods");
        }
    }

    @DisplayName("Hunter class has implemented giveFood")
    @Order(8)
    @Test
    void canGiveFood() {

        try {
            Hunter hunter = getTestHunter();
            Traveler traveler = getTestTraveler();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Hunter.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method giveFood = Hunter.class.getMethod("giveFood",Traveler.class,int.class);

            assertEquals(TEST_INITIAL_TRAVELER_FOOD, (int)getFood.invoke(traveler), "Traveler.food after creation is not 1" );
            assertEquals(TEST_INITIAL_HUNTER_FOOD, (int)getFood.invoke(hunter), "Hunter.food after creation is not 2" );
            giveFood.invoke(hunter,traveler,TEST_GIVE_FOOD);
            assertEquals(TEST_GIVE_FOOD_TOTAL, (int)getFood.invoke(traveler), "Traveler.food after giveFood is not 3" );
            assertEquals(TEST_REMAINING_FOOD_TOTAL, (int)getFood.invoke(hunter), "Hunter.food after giveFood is not 0" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Hunter class must be defined with giveFood() and getFood() methods");
        }
    }

    @DisplayName("Hunter class can update isGetHealthy")
    @Order(9)
    @Test
    void canUpdateHealth() {
        try {
            Hunter hunter = getTestHunter();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Hunter.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method eat = Hunter.class.getMethod("eat");
            @SuppressWarnings("unchecked")
            Method getIsHealthy = Hunter.class.getMethod("getIsHealthy");

            eat.invoke(hunter);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(hunter), "Hunter.food after eating is 0" );
            assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(hunter), "Hunter.isHealthy after eating once is true" );

            eat.invoke(hunter);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(hunter), "Hunter.food after overeating is still 0" );
            assertEquals(TEST_ISNOTHEALTHY, (boolean)getIsHealthy.invoke(hunter), "Hunter.isHealthy after eating once is false" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Hunter class must be defined with eat(), getFood(), and getIsHealthy() methods");
        }
    }

    private static Hunter getTestHunter() throws NoSuchElementException {
        try {
            @SuppressWarnings("unchecked")
            Constructor hunterConstructor = Hunter.class.getConstructor();
            @SuppressWarnings("unchecked")
            Hunter hunter = (Hunter) hunterConstructor.newInstance();
            return hunter;
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    private static Traveler getTestTraveler() throws NoSuchElementException {
        try {
            @SuppressWarnings("unchecked")
            Constructor travelerConstructor = Traveler.class.getConstructor();
            @SuppressWarnings("unchecked")
            Traveler person = (Traveler) travelerConstructor.newInstance();
            return person;
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    private boolean doesChildClassExtendParentClass(Class<?> childClass, Class<?> parentClassToCheck) throws NoSuchElementException{
        try {
            if(childClass == null || parentClassToCheck == null) {
                throw new Exception("You need to provide a parent and child class to check if a child class extends a given parent class.");
            }

            if(childClass.getSuperclass() == parentClassToCheck) {
                return true;
            }
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }

        return false;
    }

    private <T> T getClassToConstruct(Object[] params, Class<?>[] paramTypes, Class<?> classToConstruct) throws NoSuchElementException{
        try {
            if(params == null || paramTypes == null || classToConstruct == null) {
                throw new Exception("params, paramTypes, and classToConstruct parameters must be provided to use this method.");
            }
            else {
                Constructor constructor = classToConstruct.getConstructor(paramTypes);
                return (T) constructor.newInstance(params);
            }
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }
    }
    private String getParentFieldValueInClassUsingReflection(String variableName, Object[] params, Class<?> [] paramTypes) {
        try {
            Class<Hunter> myClass = Hunter.class;

            Constructor constructor = myClass.getConstructor(paramTypes);
            Hunter traveler = (Hunter) constructor.newInstance(params);

            Field field = myClass.getSuperclass().getDeclaredField(variableName);
            Object fieldType = field.getType();

            field.setAccessible(true);

            if (field.get(traveler) == null){
                return "";
            }

            return field.get(traveler).toString();

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}