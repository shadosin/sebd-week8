package com.kenzie.oregontrail;

import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

import java.lang.reflect.Method;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unchecked")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubclassBTest {
    private static final int TEST_INITIAL_FOOD = 4;
    private static final int FOOD_INCREASE = 4;

    private static final Class<?>[] CLASS_CONSTRUCTOR_TYPES = {String.class};
    private static final Object[] CLASS_CONSTRUCTOR_VALUES = {"Lisa"};

    private static final String CLASS_DEFAULT_NAME = "Ranger";
    private static final Class<?> CLASS = Ranger.class;
    private Ranger subclassInstance;

    private static final String SPECIALIZED_METHOD_MEDICINE = "forageMedicine";
    private static final String SPECIALIZED_METHOD_COOK = "cook";
    private static final String SPECIALIZED_METHOD_TRAP = "trap";

    @BeforeEach
    public void beforeEach() {
        try {
            this.subclassInstance = (Ranger) CLASS.getConstructor().newInstance();
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Order(1)
    @Test
    void class_Extends_TravelerClass() throws NoSuchElementException {
        assertTrue(TestUtil.doesChildClassExtendParentClass(CLASS, Traveler.class), "The " + CLASS_DEFAULT_NAME + " class should extend the Traveler class.");
    }

    @Order(2)
    @Test
    void class_hasZero_Parameter_Constructor() throws NoSuchElementException {
        Object[] emptyValues = {};
        Class<?>[] emptyTypes = {};

        Ranger c = TestUtil.getClassToConstruct(emptyValues, emptyTypes, CLASS);
        String defaultName = TestUtil.getParentFieldValueInClassUsingReflection("name", CLASS, c);

        assertEquals(CLASS_DEFAULT_NAME, defaultName, "You did not set the default name to " + CLASS_DEFAULT_NAME);
        assertTrue(true);
    }

    @Order(3)
    @Test
    void class_hasOne_StringParameter_Constructor() throws NoSuchElementException {
        Ranger c = TestUtil.getClassToConstruct(CLASS_CONSTRUCTOR_VALUES, CLASS_CONSTRUCTOR_TYPES, CLASS);
        String defaultName = TestUtil.getParentFieldValueInClassUsingReflection("name", CLASS, c);
        assertEquals(CLASS_CONSTRUCTOR_VALUES[0], defaultName, "You did not update the name property");
        assertTrue(true);
    }

    @Order(4)
    @Test
    void class_AllMethods_ExcludingGettersAndSetters_Exist() throws NoSuchMethodException {
        Method specialMethod = CLASS.getMethod(SPECIALIZED_METHOD_COOK, Traveler.class, int.class);
        Method specialMethodMedicine = CLASS.getMethod(SPECIALIZED_METHOD_MEDICINE, Traveler.class, int.class);
    }


    @DisplayName("Class can be instantiated with no arguments")
    @Order(5)
    @Test
    void canCreateClass() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getFood = CLASS.getMethod("getFood");
        Method isHealthy = CLASS.getMethod("isHealthy");

        assertTrue(this.subclassInstance instanceof Traveler, CLASS_DEFAULT_NAME + " is instanceof Traveler");
        assertEquals(TEST_INITIAL_FOOD, (int) getFood.invoke(this.subclassInstance), CLASS_DEFAULT_NAME + ".food starts at 1");
        assertEquals(true, (boolean) isHealthy.invoke(this.subclassInstance), CLASS_DEFAULT_NAME + ".isHealthy starts at true");

    }

    @DisplayName("Class has implemented specialized method one")
    @Order(6)
    @Test
    void canExecuteSpecializedMethodOne() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method cook = CLASS.getMethod(SPECIALIZED_METHOD_COOK, Traveler.class, int.class);
        Method travelerGetFood = CLASS.getMethod("getFood");

        int amount = 1;

        Traveler t = TestUtil.getDefaultTestTraveler("Billy");
        int travelerBeforeFood = (int) travelerGetFood.invoke(t);
        int rangerBeforeFood = (int) travelerGetFood.invoke(this.subclassInstance);

        cook.invoke(this.subclassInstance, t, amount);

        int travelerAfterFood = (int) travelerGetFood.invoke(t);
        int rangerAfterFood = (int) travelerGetFood.invoke(this.subclassInstance);

        assertEquals(amount, travelerAfterFood - travelerBeforeFood, amount + " food should have been transferred to Traveler");
        assertEquals(amount, rangerBeforeFood - rangerAfterFood, amount + " food should have been removed from Ranger");


    }

    @DisplayName("Class has implemented specialized method one")
    @Order(7)
    @Test
    void canExecuteSpecializedMethodOne_MaxFood() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method cook = CLASS.getMethod(SPECIALIZED_METHOD_COOK, Traveler.class, int.class);
        Method getFood = CLASS.getMethod("getFood");

        int amount = 100;

        Traveler t = TestUtil.getDefaultTestTraveler("Billy");
        int tBeforeFood = (int) getFood.invoke(t);
        int rBeforeFood = (int) getFood.invoke(this.subclassInstance);

        cook.invoke(this.subclassInstance, t, amount);
        int tAfterFood = (int) getFood.invoke(t);
        int rAfterFood = (int) getFood.invoke(this.subclassInstance);

        assertEquals(tAfterFood - tBeforeFood, 0, "No food should have been added in Traveler, requested too much");
        assertEquals(rBeforeFood - rAfterFood, 0, "No food should have been exchanged in Ranger, requested too much");


    }

    @DisplayName("Class has implemented specialized method 2")
    @Order(8)
    @Test
    void canExecuteSpecializedMethodTwo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method forageMedicine = CLASS.getMethod(SPECIALIZED_METHOD_MEDICINE, Traveler.class, int.class);
        forageMedicine.invoke(this.subclassInstance, TestUtil.getDefaultTestTraveler("Billy"), 1);
    }


    @DisplayName("Class has implemented specialized method 3")
    @Order(9)
    @Test
    void canExecuteSpecializedMethodThree() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method trap = CLASS.getMethod(SPECIALIZED_METHOD_TRAP);
        Method getFood = CLASS.getMethod("getFood");

        int beforeFood = (int) getFood.invoke(this.subclassInstance);
        trap.invoke(this.subclassInstance);
        int afterFood = (int) getFood.invoke(this.subclassInstance);

        assertEquals(afterFood - beforeFood, FOOD_INCREASE, "trapping should increase food by " + FOOD_INCREASE);


    }

    @DisplayName("Class has implemented eat")
    @Order(10)
    @Test
    void canEat() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getFood = CLASS.getMethod("getFood");
        Method setFood = CLASS.getMethod("setFood", int.class);
        Method eat = CLASS.getMethod("eat");

        assertEquals(TEST_INITIAL_FOOD, (int) getFood.invoke(this.subclassInstance), CLASS_DEFAULT_NAME + " starts with 4 food.");

        eat.invoke(this.subclassInstance);
        assertEquals(3, (int) getFood.invoke(this.subclassInstance), CLASS_DEFAULT_NAME + " eating removes 1 food");

        setFood.invoke(this.subclassInstance, 0);
        eat.invoke(this.subclassInstance);
        assertEquals(0, (int) getFood.invoke(this.subclassInstance), CLASS_DEFAULT_NAME + ".food after overeating is 0");

    }

    @DisplayName("Class can update isGetHealthy")
    @Order(11)
    @Test
    void canUpdateHealth() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getFood = CLASS.getMethod("getFood");
        Method eat = CLASS.getMethod("eat");
        Method isHealthy = CLASS.getMethod("isHealthy");

        eat.invoke(this.subclassInstance);
        assertEquals(TEST_INITIAL_FOOD - 1, (int) getFood.invoke(this.subclassInstance), CLASS_DEFAULT_NAME + ".food after eating is " + (TEST_INITIAL_FOOD - 1));
        assertEquals(true, (boolean) isHealthy.invoke(this.subclassInstance), CLASS_DEFAULT_NAME + ".isHealthy after eating once is true");

        eat.invoke(this.subclassInstance);
        eat.invoke(this.subclassInstance);
        eat.invoke(this.subclassInstance);
        eat.invoke(this.subclassInstance);

        assertEquals(0, (int) getFood.invoke(this.subclassInstance), CLASS_DEFAULT_NAME + ".food after overeating is still 0");
        assertEquals(false, isHealthy.invoke(this.subclassInstance), CLASS_DEFAULT_NAME + ".isHealthy after overeating is false");

    }
}