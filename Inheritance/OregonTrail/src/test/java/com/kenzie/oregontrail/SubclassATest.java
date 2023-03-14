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
        Object[] emptyValues = {};
        Class<?>[] emptyTypes = {};

        Carpenter c = TestUtil.getClassToConstruct(emptyValues, emptyTypes, CLASS);
        String defaultName = TestUtil.getParentFieldValueInClassUsingReflection("name", CLASS, c);

        assertEquals(CLASS_DEFAULT_NAME, defaultName, "You did not set the default name to " + CLASS_DEFAULT_NAME);
        assertTrue(true);
    }

    @Order(3)
    @Test
    void class_hasOne_StringParameter_Constructor() throws NoSuchElementException {
        Carpenter c = TestUtil.getClassToConstruct(CLASS_CONSTRUCTOR_VALUES, CLASS_CONSTRUCTOR_TYPES, CLASS);
        String defaultName = TestUtil.getParentFieldValueInClassUsingReflection("name", CLASS, c);
        assertEquals(CLASS_CONSTRUCTOR_VALUES[0], defaultName, "You did not update the name property");
        assertTrue(true);
    }

    @Order(4)
    @Test
    void class_AllMethods_ExcludingGettersAndSetters_Exist() throws NoSuchMethodException {
        Method specialMethod = CLASS.getMethod(SPECIALIZED_METHOD, Wagon.class, Traveler.class);
    }


    @DisplayName("Class can be instantiated with no arguments")
    @Order(5)
    @Test
    void canCreateClass() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getFood = CLASS.getMethod("getFood");
        Method isHealthy = CLASS.getMethod("isHealthy");

        assertTrue(subclassInstance instanceof Traveler, CLASS_DEFAULT_NAME + " is instanceof Traveler");
        assertEquals(TEST_INITIAL_FOOD, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food starts at 1");
        assertEquals(true, (boolean) isHealthy.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".isHealthy starts at true");
    }

    @DisplayName("Specialized method fails on bad condition ")
    @Order(6)
    @Test
    void canExecuteSpecializedMethodFail() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor wagonConstructor = Wagon.class.getConstructor();
        Wagon wagon = (Wagon) wagonConstructor.newInstance();
        Method setIsBrokenTrue = Wagon.class.getMethod("breakWagon");

        setIsBrokenTrue.invoke(wagon);

        // Carpenter Starts With 1 Food, so they can't fix the wagon
        Method specializedMethod = CLASS.getMethod(SPECIALIZED_METHOD, Wagon.class, Traveler.class);

        // Make traveler sick
        Traveler t = TestUtil.getDefaultTestTraveler("Moby");
        Method setHealthy = Traveler.class.getMethod("setHealthy", boolean.class);
        setHealthy.invoke(t, false);

        specializedMethod.invoke(subclassInstance, wagon, t);

        assertTrue(wagon.isBroken(),
                "Wagon is not fixed. Class doesn't start with enough food and other traveler is sick");

    }

    @DisplayName("Specialized method passes on correct condition")

    @Order(7)
    @Test
    void canExecuteSpecializedMethodPass() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Constructor wagonConstructor = Wagon.class.getConstructor();
        Wagon wagon = (Wagon) wagonConstructor.newInstance();
        Method setIsBrokenTrue = Wagon.class.getMethod("breakWagon");
        setIsBrokenTrue.invoke(wagon);

        // Carpenter now has enough food to fix the wagon
        Method setFood = CLASS.getMethod("setFood", int.class);
        setFood.invoke(subclassInstance, 2);

        Method specializedMethod = CLASS.getMethod(SPECIALIZED_METHOD, Wagon.class, Traveler.class);
        specializedMethod.invoke(subclassInstance, wagon, TestUtil.getDefaultTestTraveler("Moby"));

        Method getHealthy = CLASS.getMethod("isHealthy");
        boolean isHealthy = (boolean) getHealthy.invoke(subclassInstance);

        assertFalse(wagon.isBroken(), "Wagon should be fixed with food and healthy traveler");
        assertFalse(isHealthy, "Fixing the wagon should eat all of the " + CLASS_DEFAULT_NAME + "'s food");
    }

    @DisplayName("Class has implemented eat")
    @Order(8)
    @Test
    void canEat() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getFood = CLASS.getMethod("getFood");
        Method setFood = CLASS.getMethod("setFood", int.class);
        Method eat = CLASS.getMethod("eat");

        eat.invoke(subclassInstance);
        assertEquals(0, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food after eating is 0");
        setFood.invoke(subclassInstance, 1);
        eat.invoke(subclassInstance);
        assertEquals(0, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food after overeating is still 0");
    }

    @DisplayName("Class can update isGetHealthy")
    @Order(9)
    @Test
    void canUpdateHealth() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getFood = CLASS.getMethod("getFood");
        Method eat = CLASS.getMethod("eat");
        Method isHealthy = CLASS.getMethod("isHealthy");

        eat.invoke(subclassInstance);
        assertEquals(0, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food after eating is 0");
        assertEquals(true, (boolean) isHealthy.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".isHealthy after eating once is true");

        eat.invoke(subclassInstance);
        assertEquals(0, (int) getFood.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".food after overeating is still 0");
        assertEquals(false, (boolean) isHealthy.invoke(subclassInstance), CLASS_DEFAULT_NAME + ".isHealthy after eating once is false");
    }
}