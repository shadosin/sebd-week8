package com.kenzie.oregontrail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.internal.matchers.Null;

import java.lang.reflect.*;
import java.util.NoSuchElementException;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WagonTest {
    public static final int TEST_CAPACITY = 4;

    public static final int TEST_CAPACITY_AFTER_JOIN = 3;
    public static final int TEST_NO_CAPACITY = 0;
    public static final int TEST_INITIAL_FOOD = 1;
    public static final int TEST_ADD_FOOD_1 = 2;
    public static final int TEST_ADD_FOOD_2 = 3;
    public static final int TEST_ADD_FOOD_3 = 4;
    public static final int TEST_NO_FOOD = 0;
    public static final boolean TEST_ISHEALTHY = true;
    public static final boolean TEST_ISNOTHEALTHY = false;
    public static final boolean TEST_SHOULD_QUARANTINE = true;
    public static final boolean TEST_SHOULDNOT_QUARANTINE = false;
    public static final int TEST_CAPACITY_LOAD = 8;
    public static final int TEST_CAPACITY_LOW = 2;
    public static final int TEST_NUM_TRAVELERS = 2;
    public static final int TEST_NUM_HUNTERS = 3;
    public static final int TEST_NUM_DOCTORS = 3;
    public static final int TEST_NUM_TRAVELERS_OVERFLOW = 4;

    String CLASSNAME = "Wagon";

    public static final int WAGON_10_SEATS = 10;

    Object [] WAGON_CLASS_CONSTRUCTOR_VALUES = {WAGON_10_SEATS};

    Class<?> [] WAGON_CLASS_CONSTRUCTOR_TYPES = {int.class};

    @Order(1)
    @Test
    void wagonClass_HasField_capacity_Test() {
        String fieldNameToFind = "capacity";
        String fieldType = "int";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, WAGON_CLASS_CONSTRUCTOR_VALUES, WAGON_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false, "Double check and make sure you have a field of type " + fieldType + " variable with the name " + fieldNameToFind + " in the " + CLASSNAME + " class. You must also have a constructor.");
        }
    }

    @Order(2)
    @Test
    void wagonClass_nameField_usesPrivateModifier_Test() {
        String EXPECTED_MODIFIER = "PRIVATE";
        String fieldNameToFind = "capacity";
        String fieldType = "String";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, WAGON_CLASS_CONSTRUCTOR_VALUES, WAGON_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(doesClassFieldUseCorrectModifier(fieldNameToFind, WAGON_CLASS_CONSTRUCTOR_VALUES, WAGON_CLASS_CONSTRUCTOR_TYPES, EXPECTED_MODIFIER), "Please Double check that your field " + fieldNameToFind + " uses the " + EXPECTED_MODIFIER + "modifier.  You must also have a constructor.");
        }
        catch (Exception e) {
            assertTrue(false, "Double check that you have a field called: "+ fieldNameToFind + "of type " + fieldType + " in the " + CLASSNAME + " class that has the " + EXPECTED_MODIFIER + " Modifier.");
        }
    }

    @Order(3)
    @Test
    void wagonClass_HasField_passengers_Test() {
        String fieldNameToFind = "passengers";
        String fieldType = "Traveler Array";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, WAGON_CLASS_CONSTRUCTOR_VALUES, WAGON_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false, "Double check and make sure you have a field of type " + fieldType + " variable with the name " + fieldNameToFind + " in the " + CLASSNAME + " class.  You must also have a constructor.");
        }
    }

    @Order(4)
    @Test
    void wagonClass_passengersField_usesPrivateModifier_Test() {
        String EXPECTED_MODIFIER = "PRIVATE";
        String fieldNameToFind = "capacity";
        String fieldType = "String";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, WAGON_CLASS_CONSTRUCTOR_VALUES, WAGON_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(doesClassFieldUseCorrectModifier(fieldNameToFind, WAGON_CLASS_CONSTRUCTOR_VALUES, WAGON_CLASS_CONSTRUCTOR_TYPES, EXPECTED_MODIFIER), "Please Double check that your field " + fieldNameToFind + " uses the " + EXPECTED_MODIFIER + "modifier.");
        }
        catch (Exception e) {
            assertTrue(false, "Double check that you have a field called: "+ fieldNameToFind + "of type " + fieldType + " in the " + CLASSNAME + " class that has the " + EXPECTED_MODIFIER + " Modifier.  You must also have a constructor.");
        }
    }

    @Order(5)
    @Test
    void wagonClass_hasOne_intParameter_Constructor() throws NoSuchElementException {
        try {
            @SuppressWarnings("unchecked")
            Constructor wagonConstructor = Wagon.class.getConstructor(int.class);
            @SuppressWarnings("unchecked")
            Wagon wagon = (Wagon) wagonConstructor.newInstance(TEST_CAPACITY);
            String result = getFieldValueInClassUsingReflection("capacity", WAGON_CLASS_CONSTRUCTOR_VALUES, WAGON_CLASS_CONSTRUCTOR_TYPES);
            String expected = WAGON_10_SEATS + "";
            assertEquals(expected, result, "Be sure to set capacity in your constructor");
            result = getFieldValueInClassUsingReflection("passengers", WAGON_CLASS_CONSTRUCTOR_VALUES, WAGON_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(result.length() > 0, "Be sure to set passengers in your constructor");

        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Wagon Class has one Constructor that takes one String parameter");
        }
    }



    @Order(6)
    @Test
    void wagon_GettersandSetters_Exist() {
        try {
            Method getCapacity = Wagon.class.getMethod("getCapacity");

            Method setCapacity = Wagon.class.getMethod("setCapacity", int.class);

            Method getPassengers = Wagon.class.getMethod("getPassengers");

            Method setPassengers = Wagon.class.getMethod("setPassengers", Traveler[].class);

            assertTrue(true);
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Wagon Class has one Constructor that takes one int parameter and should also have getters and setters for getCapacity, setCapacity, and getPassengers.");
        }
    }

    @Order(7)
    @Test
    void wagon_AllMethods_ExcludingGettersAndSetters_Exist() {
        try {
            Method getAvailableSeatCount =  Wagon.class.getMethod("getAvailableSeatCount");

            Method join =  Wagon.class.getMethod("join", Traveler.class);

            Method shouldQuarantine =  Wagon.class.getMethod("shouldQuarantine");

            Method totalFood =  Wagon.class.getMethod("totalFood");

            Method loadWagon =  Wagon.class.getMethod("loadWagon", int.class, int.class, int.class);

            assertTrue(true);
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Wagon Class has one Constructor that takes one int parameter and should also have methods for getAvailableSeatCount, join, shouldQuarantine, totalFood, and loadWagon.");
        }
    }

    @Order(8)
    @Test
    void wagon_getAvailableSeatCountMethod_Returns10AvailableSeats() {
        Method getAvailableSeatCount;
        try {
            Wagon wagon = (Wagon) getClassToConstruct(WAGON_CLASS_CONSTRUCTOR_VALUES, WAGON_CLASS_CONSTRUCTOR_TYPES, Wagon.class);

            getAvailableSeatCount = wagon.getClass().getMethod("getAvailableSeatCount");

            if(getAvailableSeatCount == null) {
                fail("Could not find method getAvailableSeatCount");
            }

            int seatValue = (int) getAvailableSeatCount.invoke(wagon);

            assertEquals(WAGON_10_SEATS, seatValue, "getAvailableSeatCount() returns the available seat count");
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Wagon Class has the method getAvailableSeatCount()");
        }
    }

    @DisplayName("Wagon class can use join to add travelers")
    @Order(9)
    @Test
    void canTravelerJoin() {
        try {
            Wagon wagon = getDefaultTestWagon(4);
            Traveler traveler = getDefaultTestTraveler();

            //test Wagon join method
            @SuppressWarnings("unchecked")
            Method join = Wagon.class.getMethod("join", Traveler.class);
            join.invoke(wagon,traveler);

            @SuppressWarnings("unchecked")
            Method getAvailableSeatCount = Wagon.class.getMethod("getAvailableSeatCount");
            assertEquals(3, (int) getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() after join should return 1 less");

            //add three travelers to test a full wagon
            join.invoke(wagon,traveler);
            join.invoke(wagon,traveler);
            join.invoke(wagon,traveler);
            assertEquals(0, (int) getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() after 4 joins is 0");

            //add one more after no capacity
            join.invoke(wagon,traveler);
            assertEquals(0, (int) getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() after 5 joins should still be 0");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Wagon class must be defined with getAvailableSeatCount() and join() method");
        }
    }

    @DisplayName("checkTravelerFood: Wagon class can check total food in wagon")
    @Order(10)
    @Test
    void checkTravelerFood() {
        try {
            Wagon wagon = getDefaultTestWagon();
            @SuppressWarnings("unchecked")
            Method totalFood = Wagon.class.getMethod("totalFood");
            assertEquals(TEST_NO_FOOD,(int) totalFood.invoke(wagon), "totalFood() on empty wagon should return 0");

            Traveler traveler = getDefaultTestTraveler();
            @SuppressWarnings("unchecked")
            Method join = Wagon.class.getMethod("join",Traveler.class);

            //add travelers and test totalFood
            join.invoke(wagon,traveler);
            assertEquals(TEST_INITIAL_FOOD,(int) totalFood.invoke(wagon), "totalFood() after 1 join should return 1");
            join.invoke(wagon,traveler);
            assertEquals(TEST_ADD_FOOD_1,(int) totalFood.invoke(wagon), "totalFood() after 2 joins should return 2");
            join.invoke(wagon,traveler);
            assertEquals(TEST_ADD_FOOD_2,(int) totalFood.invoke(wagon), "totalFood() after 3 joins should return 3");
            join.invoke(wagon,traveler);
            assertEquals(TEST_ADD_FOOD_3,(int) totalFood.invoke(wagon), "totalFood() after 4 join should return 4");

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Be sure to check for null! Additionally, Wagon class must be defined with totalFood() method");

        }
    }

    @DisplayName("checkShouldQuarantine: Wagon class can check health of all passengers to set quarantine")
    @Order(11)
    @Test
    void checkShouldQuarantine() {
        try {
            Wagon wagon = getDefaultTestWagon();
            @SuppressWarnings("unchecked")
            Method shouldQuarantine = Wagon.class.getMethod("shouldQuarantine");

            assertEquals(TEST_SHOULDNOT_QUARANTINE,(boolean)shouldQuarantine.invoke(wagon), "shouldQuarantine() on empty wagon should return false");

            Traveler traveler = getDefaultTestTraveler();
            @SuppressWarnings("unchecked")
            Method join = Wagon.class.getMethod("join", Traveler.class);
            @SuppressWarnings("unchecked")
            Method eat = Traveler.class.getMethod("eat");
            @SuppressWarnings("unchecked")
            Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

            join.invoke(wagon,traveler);
            assertEquals(TEST_SHOULDNOT_QUARANTINE,(boolean) shouldQuarantine.invoke(wagon), "shouldQuarantine() on one healthy traveler should return false");

            Traveler sickTraveler = getDefaultTestTraveler();
            eat.invoke(sickTraveler);
            eat.invoke(sickTraveler);
            assertEquals(TEST_ISNOTHEALTHY,(boolean)getIsHealthy.invoke(sickTraveler),"sickTraveler is unhealthy after eating with no food");

            join.invoke(wagon,sickTraveler);
            assertEquals(TEST_SHOULD_QUARANTINE,shouldQuarantine.invoke(wagon), "shouldQuarantine() with one unhealthy travelers should return true");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Be sure to check for null! Additionally, Wagon class must be defined with shouldQuarantine and join methods.");
        }
    }

    @DisplayName("canLoadWagon: Wagon class can load travelers into wagon")
    @Order(12)
    @Test
    void canLoadWagon() {
        try {
            Wagon wagon = getDefaultTestWagon();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getAvailableSeatCount = Wagon.class.getMethod("getAvailableSeatCount");
            @SuppressWarnings("unchecked")
            Method loadWagon = Wagon.class.getMethod("loadWagon", int.class, int.class, int.class);

            assertEquals(TEST_CAPACITY_LOAD, (int)getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() should be 8");
            loadWagon.invoke(wagon,TEST_NUM_TRAVELERS,TEST_NUM_DOCTORS,TEST_NUM_HUNTERS);
            assertEquals(TEST_NO_CAPACITY, (int)getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() should be 0 after load");

            //test small wagon
            Wagon wagonLowCapacity = getDefaultTestWagon();
            assertEquals(TEST_CAPACITY_LOAD, (int)getAvailableSeatCount.invoke(wagonLowCapacity), "getAvailableSeatCount() should initially be 8");
            loadWagon.invoke(wagonLowCapacity,TEST_NUM_TRAVELERS,TEST_NUM_TRAVELERS,TEST_NUM_TRAVELERS);
            assertEquals(TEST_CAPACITY_LOW, (int)getAvailableSeatCount.invoke(wagonLowCapacity), "getAvailableSeatCount() should be 2");

            //test loading over capacity in wagon
            Wagon wagonOverflowCapacity = getDefaultTestWagon();
            assertEquals(TEST_CAPACITY_LOAD, (int)getAvailableSeatCount.invoke(wagonOverflowCapacity), "getAvailableSeatCount() should initially be 8");
            loadWagon.invoke(wagonOverflowCapacity,TEST_NUM_TRAVELERS_OVERFLOW,TEST_NUM_TRAVELERS,TEST_NUM_TRAVELERS);
            assertEquals(TEST_NO_CAPACITY, (int)getAvailableSeatCount.invoke(wagonOverflowCapacity), "getAvailableSeatCount() should be 0");
            loadWagon.invoke(wagonOverflowCapacity,TEST_NUM_TRAVELERS_OVERFLOW,TEST_NUM_TRAVELERS,TEST_NUM_TRAVELERS);
            assertEquals(TEST_NO_CAPACITY, (int)getAvailableSeatCount.invoke(wagonOverflowCapacity), "getAvailableSeatCount() should still be 0 after overflow load");

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Wagon class must be defined with loadWagon() and getAvailableSeatCount method");
        }
    }

    @DisplayName("checkPassengers: Check that passengers are unique")
    @Order(13)
    @Test
    void checkPassengers() {
        try {
            Wagon wagon = getDefaultTestWagon();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getAvailableSeatCount = Wagon.class.getMethod("getAvailableSeatCount");
            @SuppressWarnings("unchecked")
            Method loadWagon = Wagon.class.getMethod("loadWagon", int.class, int.class, int.class);
            @SuppressWarnings("unchecked")
            Method getPassengers = Wagon.class.getMethod("getPassengers");

            assertEquals(TEST_CAPACITY_LOAD, (int)getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() should be 8");
            loadWagon.invoke(wagon,TEST_NUM_TRAVELERS,TEST_NUM_DOCTORS,TEST_NUM_HUNTERS);
            assertEquals(TEST_NO_CAPACITY, (int)getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() should be 0 after load");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Wagon class must be defined with getPassengers(), loadWagon(), and getAvailableSeatCount method");
        }
    }


    private static Wagon getDefaultTestWagon() throws NoSuchElementException {
        try {
            @SuppressWarnings("unchecked")
            Constructor wagonConstructor = Wagon.class.getConstructor(int.class);

            Wagon wagon = (Wagon) wagonConstructor.newInstance(TEST_CAPACITY_LOAD);

            return wagon;
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }
    }
    private static Wagon getDefaultTestWagon(Integer capacity) throws NoSuchElementException {
        try {
            @SuppressWarnings("unchecked")
            Constructor wagonConstructor = Wagon.class.getConstructor(int.class);

            if(capacity != null) {
                Wagon wagon = (Wagon) wagonConstructor.newInstance(capacity);
                return wagon;
            }
            else {
                Wagon wagon = (Wagon) wagonConstructor.newInstance(TEST_CAPACITY_LOAD);
                return wagon;
            }
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    private static Traveler getDefaultTestTraveler() throws NoSuchElementException{
        try {
            Constructor constructor = Traveler.class.getConstructor();
            Traveler traveler = (Traveler) constructor.newInstance();
            return traveler;
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }
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

    private String getFieldValueInClassUsingReflection(String variableName, Object[] params, Class<?> [] paramTypes) {
        try {
            Class<Wagon> myClass = Wagon.class;

            Constructor constructor = myClass.getConstructor(paramTypes);
            Wagon wagon = (Wagon) constructor.newInstance(params);

            Field field = myClass.getDeclaredField(variableName);
            Object fieldType = field.getType();

            field.setAccessible(true);
            if (field.get(wagon) == null){
                return "";
            }
            return field.get(wagon).toString();

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

    private boolean doesClassFieldUseCorrectModifier(String variableName, Object[] params, Class<?> [] paramTypes, String expectedModifier) {
        try {
            if(paramTypes == null || expectedModifier == null || expectedModifier.isEmpty() || expectedModifier.isBlank() ) {
                throw new Exception("paramTypes and expectedModifier parameters must be provided and must be valid.");
            }

            Class<Wagon> myClass = Wagon.class;

            Constructor constructor = myClass.getConstructor(paramTypes);
            Wagon wagon = (Wagon) constructor.newInstance(params);

            Field field = myClass.getDeclaredField(variableName);
            Object fieldType = field.getType();

            int modifiers = field.getModifiers();

            String expectedModifierToLower = expectedModifier.toLowerCase();

            if(Modifier.isProtected(modifiers)) {
                if(expectedModifier.toLowerCase().equalsIgnoreCase("protected")) {
                    return true;
                }
            }
            else if(Modifier.isPrivate(modifiers)) {
                if(expectedModifier.toLowerCase().equalsIgnoreCase("private")) {
                    return true;
                }
            }
            else if(Modifier.isPublic(modifiers)) {
                if(expectedModifier.toLowerCase().equalsIgnoreCase("public")) {
                    return true;
                }
            }
            else if(Modifier.isAbstract(modifiers)) {
                if(expectedModifier.toLowerCase().equalsIgnoreCase("abstract")) {
                    return true;
                }
            }
            else if(Modifier.isFinal(modifiers)) {
                if(expectedModifier.toLowerCase().equalsIgnoreCase("final")) {
                    return true;
                }
            }
            else if(Modifier.isInterface(modifiers)) {
                if(expectedModifier.toLowerCase().equalsIgnoreCase("interface")) {
                    return true;
                }
            }
            else if(Modifier.isStatic(modifiers)) {
                if(expectedModifier.toLowerCase().equalsIgnoreCase("static")) {
                    return true;
                }
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}