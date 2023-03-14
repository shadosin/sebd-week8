package com.kenzie.oregontrail;

import org.junit.jupiter.api.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubclassATest {
    private static final int TEST_INITIAL_FOOD = 1;
    private static final int TRAP_FOOD_INCREASE = 3;

    private static final Class<?>[] CLASS_CONSTRUCTOR_TYPES = {String.class};
    private static final Object[] CLASS_CONSTRUCTOR_VALUES = {"Homer"};

    private static final String CLASS_DEFAULT_NAME = "Carpenter";
    private static final Class CLASS = Carpenter.class;
    private Carpenter subclassInstance;

    private static final String SPECIALIZED_METHOD = "tryToFixWagon";

    @BeforeEach
    public void beforeEach() {
        try {
            this.subclassInstance = (Carpenter) CLASS.getConstructor().newInstance();
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Order(1)
    @Test
    void class_Extends_TravelerClass() throws NoSuchElementException {
        try {
            assertTrue(TestUtil.doesChildClassExtendParentClass(CLASS, Traveler.class), "The " + CLASS_DEFAULT_NAME + " class should extend the Traveler class.");
        } catch (Exception e) {
            assertTrue(false, "The " + CLASS_DEFAULT_NAME + " class should extend the Traveler class.");
        }
    }

    @Order(2)
    @Test
    void class_hasZero_Parameter_Constructor() throws NoSuchElementException {
        try {

            Object[] emptyValues = {};
            Class<?>[] emptyTypes = {};

            Carpenter c = TestUtil.getClassToConstruct(emptyValues, emptyTypes, CLASS);
            String defaultName = TestUtil.getParentFieldValueInClassUsingReflection("name", CLASS, c);

            assertEquals(CLASS_DEFAULT_NAME, defaultName, "You did not set the default name to " + CLASS_DEFAULT_NAME);
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false, "The " + CLASS_DEFAULT_NAME + " class should have a Parameterless Constructor.");
        }
    }

    @Order(3)
    @Test
    void class_hasOne_StringParameter_Constructor() throws NoSuchElementException {
        try {
            Carpenter c = TestUtil.getClassToConstruct(CLASS_CONSTRUCTOR_VALUES, CLASS_CONSTRUCTOR_TYPES, CLASS);
            String defaultName = TestUtil.getParentFieldValueInClassUsingReflection("name", CLASS, c);
            assertEquals(CLASS_CONSTRUCTOR_VALUES[0], defaultName, "You did not update the name property");
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false, "The " + CLASS_DEFAULT_NAME + " class should have a One Parameter Constructor that takes a single String parameter.");
        }
    }

    @Order(4)
    @Test
    void class_AllMethods_ExcludingGettersAndSetters_Exist() {
        try {
            Method specialMethod = CLASS.getMethod(SPECIALIZED_METHOD, Wagon.class, Traveler.class);
        } catch (Exception e) {
            assertTrue(false, "Please double check your " + CLASS_DEFAULT_NAME + " Class has the correct Constructors and that you have method for " + SPECIALIZED_METHOD + ". This excludes getter and setter methods for this test.");
        }
    }


    @DisplayName("Class can be instantiated with no arguments")
    @Order(5)
    @Test
    void canCreateClass() {
        try {
            Method getFood = CLASS.getMethod("getFood");
            Method isHealthy = CLASS.getMethod("isHealthy");

            assertTrue(subclassInstance instanceof Traveler, CLASS_DEFAULT_NAME + " is instanceof Traveler");
            assertEquals(TEST_INITIAL_FOOD, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food starts at 1");
            assertEquals(true, (boolean) isHealthy.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".isHealthy starts at true");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail(CLASS_DEFAULT_NAME + " class must be defined with getFood() and isHealthy() method");
        }
    }

    @DisplayName("Class has implemented specialized method")
    @Order(6)
    @Test
    void canExecuteSpecializedMethod() {
        try {
            Constructor wagonConstructor = Wagon.class.getConstructor();
            Wagon wagon = (Wagon) wagonConstructor.newInstance();

            Method specializedMethod = CLASS.getMethod(SPECIALIZED_METHOD, Wagon.class, Traveler.class);
            specializedMethod.invoke(subclassInstance, wagon, TestUtil.getDefaultTestTraveler("Moby"));

            assertFalse(wagon.isBroken(), "Wagon is fixed after executing " + CLASS_DEFAULT_NAME + "." + SPECIALIZED_METHOD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Class must be defined with " + SPECIALIZED_METHOD + " methods");
        }
    }

    @DisplayName("Class has implemented eat")
    @Order(7)
    @Test
    void canEat() {
        try {
            Method getFood = CLASS.getMethod("getFood");
            Method setFood = CLASS.getMethod("setFood", int.class);
            Method eat = CLASS.getMethod("eat");

            eat.invoke(subclassInstance);
            assertEquals(0, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food after eating is 0");
            setFood.invoke(subclassInstance, 1);
            eat.invoke(subclassInstance);
            assertEquals(0, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food after overeating is still 0");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Class must be defined with eat(), setFood() and getFood() methods");
        }
    }

    @DisplayName("Class can update isGetHealthy")
    @Order(8)
    @Test
    void canUpdateHealth() {
        try {

            Method getFood = CLASS.getMethod("getFood");
            Method eat = CLASS.getMethod("eat");
            Method isHealthy = CLASS.getMethod("isHealthy");

            eat.invoke(subclassInstance);
            assertEquals(0, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food after eating is 0");
            assertEquals(true, (boolean) isHealthy.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".isHealthy after eating once is true");

            eat.invoke(subclassInstance);
            assertEquals(0, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food after overeating is still 0");
            assertEquals(false, (boolean) isHealthy.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".isHealthy after eating once is false");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail(CLASS_DEFAULT_NAME + " class must be defined with eat(), getFood(), and isHealthy() methods");
        }
    }
}