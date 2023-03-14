package com.kenzie.oregontrail;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.*;
import java.util.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WagonTest {

    private static final String CLASS_NAME = "Wagon";
    private static final Class CLASS = Wagon.class;
    private Wagon wagonInstance;

    private static final int TEST_CAPACITY = 4;

    @BeforeEach
    void createTestWagon() {
        try {
            Constructor wagonConstructor = CLASS.getConstructor(int.class);
            this.wagonInstance = (Wagon) wagonConstructor.newInstance(TEST_CAPACITY);
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Order(1)
    @Test
    void wagonClass_HasField_capacity_Test() {
        String fieldNameToFind = "capacity";
        String fieldType = "int";

        try {
            TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, this.wagonInstance, CLASS);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false, "Double check and make sure you have a field of type " + fieldType + " variable with the name " + fieldNameToFind + " in the " + CLASS_NAME + " class. You must also have a constructor.");
        }
    }

    @Order(2)
    @Test
    void wagonClass_nameField_usesPrivateModifier_Test() {
        String expectedAccess = "PRIVATE";
        String fieldNameToFind = "capacity";
        String fieldType = "String";

        try {
            TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, this.wagonInstance, CLASS);
            assertTrue(TestUtil.doesClassFieldUseCorrectModifier(fieldNameToFind, expectedAccess, CLASS),
                    "Please double check that your field " + fieldNameToFind + " uses the " + expectedAccess + "modifier.  You must also have a constructor.");
        }
        catch (Exception e) {
            assertTrue(false,
                    "Double check that you have a field called: "+ fieldNameToFind + "of type " + fieldType + " in the " + CLASS_NAME + " class that has the " + expectedAccess + " Modifier.");
        }
    }

    @Order(3)
    @Test
    void wagonClass_HasField_passengers_Test() {
        String fieldNameToFind = "passengers";
        String fieldType = "Traveler Array";

        try {
            TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, wagonInstance, CLASS);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false,
                    "Double check and make sure you have a field of type " + fieldType + " variable with the name " + fieldNameToFind + " in the " + CLASS_NAME + " class.  You must also have a constructor.");
        }
    }

    @Order(4)
    @Test
    void wagonClass_passengersField_usesPrivateModifier_Test() {
        String expectedAccess = "PRIVATE";
        String fieldNameToFind = "capacity";
        String fieldType = "String";

        try {
            TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, wagonInstance, CLASS);
            assertTrue(TestUtil.doesClassFieldUseCorrectModifier(fieldNameToFind, expectedAccess, CLASS),
                    "Please Double check that your field " + fieldNameToFind + " uses the " + expectedAccess + "modifier.");
        }
        catch (Exception e) {
            assertTrue(false,
                    "Double check that you have a field called: "+ fieldNameToFind + "of type " + fieldType + " in the " + CLASS_NAME + " class that has the " + expectedAccess + " Modifier.  You must also have a constructor.");
        }
    }

    @Order(5)
    @Test
    void wagonClass_hasOne_intParameter_Constructor() throws NoSuchElementException {
        try {
            Constructor wagonConstructor = CLASS.getConstructor(int.class);
            Wagon wagon = (Wagon) wagonConstructor.newInstance(TEST_CAPACITY);

            int result = Integer.parseInt(TestUtil.getFieldValueInClassUsingReflection("capacity", wagon, CLASS));

            assertEquals(TEST_CAPACITY, result, "Be sure to set capacity in your constructor");

            Object passengerArray = TestUtil.getFieldValueInClassUsingReflection("passengers", wagon, CLASS);
            assertNotNull(passengerArray, "Be sure to set passengers in your constructor");
        } catch(Exception e) {
            assertTrue(false, "Please double check your Wagon Class has one Constructor that takes one String parameter");
        }
    }

    @Order(6)
    @Test
    void wagon_GettersSetters_Exist() {
        try {
            Method getCapacity = CLASS.getMethod("getCapacity");
            Method setCapacity = CLASS.getMethod("setCapacity", int.class);
            Method getPassengers = CLASS.getMethod("getPassengers");

            try {
                Method setPassengersArray = CLASS.getMethod("setPassengers", ArrayList.class);
            } catch (Exception e) {
                Method setPassengersPrimitive = CLASS.getMethod("setPassengers", Traveler[].class);
            }

            assertTrue(true);

        } catch(Exception e) {
            assertTrue(false, "Please double check your Wagon Class has one Constructor that takes one int parameter and should also have getters and setters for getCapacity, setCapacity, and getPassengers.");
        }
    }

    @Order(7)
    @Test
    void wagon_AllMethods_ExcludingGettersAndSetters_Exist() {
        try {
            Method addTraveler =  CLASS.getMethod("addTraveler", Traveler.class);
            Method shouldQuarantine =  CLASS.getMethod("shouldQuarantine");
            Method totalFood =  CLASS.getMethod("totalFood");
            Method isThereRoom =  CLASS.getMethod("isThereRoom");
            Method allAboard =  CLASS.getMethod("allAboard", int.class, int.class, int.class);

            assertTrue(true);
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Wagon Class has one Constructor that takes one int parameter and should also have methods for getAvailableSeatCount, addTraveler, shouldQuarantine, totalFood, and loadWagon.");
        }
    }

    @Order(8)
    @Test
    void wagon_Capacity() {
        try {
            Method isThereRoom = this.wagonInstance.getClass().getMethod("isThereRoom");
            Method setCapacity = this.wagonInstance.getClass().getMethod("setCapacity", int.class);

            boolean isRoom = (boolean) isThereRoom.invoke(this.wagonInstance);
            assertTrue(isRoom, "isThereRoom() returns true with no passengers");

            setCapacity.invoke(this.wagonInstance, 0);

            boolean noRoom = (boolean) isThereRoom.invoke(this.wagonInstance);
            assertFalse(noRoom, "isThereRoom() return false with no capacity");

        } catch(Exception e) {
            assertTrue(false, "Please double check your Wagon Class has the method isThereRoom()");
        }
    }

    @DisplayName("Wagon class can use addTraveler to add travelers")
    @Order(9)
    @Test
    void canTravelerJoin() {
        try {
            Method addTraveler = CLASS.getMethod("addTraveler", Traveler.class);
            Method setCapacity = this.wagonInstance.getClass().getMethod("setCapacity", int.class);
            Method getPassengers = this.wagonInstance.getClass().getMethod("getPassengers");

            int capacity = 2;
            setCapacity.invoke(this.wagonInstance, capacity);

            Traveler kevin = TestUtil.getDefaultTestTraveler("Kevin");
            Traveler jane = TestUtil.getDefaultTestTraveler("Jane");
            Traveler snooze = TestUtil.getDefaultTestTraveler("Snooze");

            addTraveler.invoke(this.wagonInstance, kevin);
            addTraveler.invoke(this.wagonInstance, jane);
            addTraveler.invoke(this.wagonInstance, snooze);

            Object passengers = getPassengers.invoke(this.wagonInstance);
            List<Object> t = TestUtil.convertPassengerList(passengers);

            Method getName =  Traveler.class.getMethod("getName");

            String t0Name = (String) getName.invoke(t.get(0));
            String t1Name = (String) getName.invoke(t.get(1));

            assertEquals(t0Name, "Jane", "Jane should be first in the wagon");
            assertEquals(t1Name, "Kevin", "Kevin should be second on the wagon");
            assertEquals(t.size(), 2, "there should only be " + capacity + " people in the wagon");

        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("Wagon class must be defined with addTraveler(), setCapacity(), and getPassengers() method");
        }
    }

    @DisplayName("checkTravelerFood: Wagon class can check total food in wagon")
    @Order(10)
    @Test
    void checkTravelerFood() {
        try {
            Method totalFood = CLASS.getMethod("totalFood");
            int t = 0;
            assertEquals(t++, (int) totalFood.invoke(this.wagonInstance), "totalFood() on empty wagon should return 0");

            Traveler traveler = TestUtil.getDefaultTestTraveler("Fin");
            Method addTraveler = CLASS.getMethod("addTraveler",Traveler.class);

            addTraveler.invoke(this.wagonInstance, traveler);
            assertEquals(t++,(int) totalFood.invoke(this.wagonInstance), "totalFood() after 1 addTraveler should return 1");

            addTraveler.invoke(this.wagonInstance, traveler);
            assertEquals(t++,(int) totalFood.invoke(this.wagonInstance), "totalFood() after 2 addTravelers should return 2");

            addTraveler.invoke(this.wagonInstance, traveler);
            assertEquals(t,(int) totalFood.invoke(this.wagonInstance), "totalFood() after 3 addTravelers should return 3");

        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("Be sure to check for null! Additionally, Wagon class must be defined with totalFood() method");
        }
    }

    @DisplayName("checkShouldQuarantine: Wagon class can check health of all passengers to set quarantine")
    @Order(11)
    @Test
    void checkShouldQuarantine() {
        try {
            Method addTraveler = CLASS.getMethod("addTraveler", Traveler.class);
            Method shouldQuarantine = CLASS.getMethod("shouldQuarantine");
            Method getIsHealthy = Traveler.class.getMethod("isHealthy");
            Method eat = Traveler.class.getMethod("eat");

            assertEquals(false, shouldQuarantine.invoke(this.wagonInstance),
                    "shouldQuarantine() on empty wagon should return false");

            Traveler traveler = TestUtil.getDefaultTestTraveler("Fernando");
            addTraveler.invoke(this.wagonInstance, traveler);
            assertEquals(false, shouldQuarantine.invoke(this.wagonInstance),
                    "shouldQuarantine() on one healthy traveler should return false");

            Traveler sickTraveler = TestUtil.getDefaultTestTraveler("Bob");
            eat.invoke(sickTraveler);
            eat.invoke(sickTraveler);
            assertEquals(false, getIsHealthy.invoke(sickTraveler),
                    "sickTraveler is unhealthy after eating with no food");

            addTraveler.invoke(this.wagonInstance,sickTraveler);
            assertEquals(true, shouldQuarantine.invoke(this.wagonInstance),
                    "shouldQuarantine() with one unhealthy travelers should return true");

        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("Be sure to check for null! Additionally, Wagon class must be defined with shouldQuarantine and addTraveler methods.");
        }
    }

    @DisplayName("canLoadWagon: Wagon class can load travelers into wagon")
    @Order(12)
    @Test
    void canLoadWagon() {
        try {
            Method allAboard = CLASS.getMethod("allAboard", int.class, int.class, int.class);
            Method setCapacity = CLASS.getMethod("setCapacity", int.class);
            Method getPassengers = CLASS.getMethod("getPassengers");

            int capacity = 9;

            setCapacity.invoke(this.wagonInstance, capacity);
            allAboard.invoke(this.wagonInstance, 3,3,3);

            Object passengers = getPassengers.invoke(this.wagonInstance);
            List<Object> t = TestUtil.convertPassengerList(passengers);

            assertEquals(3, t.stream().map(p -> p instanceof Ranger).filter(p -> p).count(), "Incorrect Number of Rangers");
            assertEquals(3, t.stream().map(p -> p instanceof Carpenter).filter(p -> p).count(), "Incorrect Number of Carpenter");
            assertEquals(capacity, t.size(), "Incorrect Number of total Travelers");

        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("Wagon class must be defined with allAboard(), setCapacity() and getPassengers() method");
        }
    }

    @DisplayName("overload Wagon: correctly load special travelers first")
    @Order(13)
    @Test
    void overLoadWagon() {
        try {
            Method allAboard = CLASS.getMethod("allAboard", int.class, int.class, int.class);
            Method setCapacity = CLASS.getMethod("setCapacity", int.class);
            Method getPassengers = CLASS.getMethod("getPassengers");

            int capacity = 7;

            setCapacity.invoke(this.wagonInstance, capacity);
            allAboard.invoke(this.wagonInstance, 3,3,3);

            Object passengers = getPassengers.invoke(this.wagonInstance);
            List<Object> t = TestUtil.convertPassengerList(passengers);

            assertEquals(3, t.stream().map(p -> p instanceof Ranger).filter(p -> p).count(), "Incorrect Number of Rangers");
            assertEquals(3, t.stream().map(p -> p instanceof Carpenter).filter(p -> p).count(), "Incorrect Number of Carpenter");
            assertEquals(capacity, t.size(), "Incorrect Number of total Travelers");

        } catch(Exception e){
            System.out.println(e.getMessage());
            fail("Wagon class must be defined with allAboard(), setCapacity() and getPassengers() method");
        }
    }
}