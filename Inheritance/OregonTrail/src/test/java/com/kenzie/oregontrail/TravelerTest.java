package com.kenzie.oregontrail;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TravelerTest {
    private static final int TEST_INITIAL_FOOD = 1;
    private static final int TEST_HUNT_FOOD = 3;
    private static final String DEFAULT_NAME = "Jane";

    private String CLASS_NAME = "Traveler";
    private Class CLASS = Traveler.class;
    private Traveler travelerInstance;

    @BeforeEach
    public void beforeEach() {
        try {
            this.travelerInstance = (Traveler) CLASS.getConstructor().newInstance();
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Order(1)
    @Test
    void travelerClass_HasField_name_Test() {
        String fieldNameToFind = "name";
        TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, this.travelerInstance, CLASS);
    }

    @Order(2)
    @Test
    void travelerClass_nameField_usesPrivateModifier_Test() {
        String EXPECTED_MODIFIER = "PROTECTED";
        String fieldNameToFind = "name";

        TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, this.travelerInstance, CLASS);
        assertTrue(TestUtil.doesClassFieldUseCorrectModifier(fieldNameToFind, EXPECTED_MODIFIER, CLASS),
                "Please Double check that your field " + fieldNameToFind + " uses the " + EXPECTED_MODIFIER + " modifier.");

    }

    @Order(3)
    @Test
    void travelerClass_HasField_food_Test() {
        String fieldNameToFind = "food";
        TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, this.travelerInstance, CLASS);
    }

    @Order(4)
    @Test
    void travelerClass_foodField_usesPrivateModifier_Test() {
        String EXPECTED_MODIFIER = "PROTECTED";
        String fieldNameToFind = "food";

        TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, this.travelerInstance, CLASS);
        assertTrue(TestUtil.doesClassFieldUseCorrectModifier(fieldNameToFind, EXPECTED_MODIFIER, CLASS), "Please Double check that your field " + fieldNameToFind + " uses the " + EXPECTED_MODIFIER + "modifier.");
    }

    @Order(5)
    @Test
    void travelerClass_HasField_isHealthy_Test() {
        String fieldNameToFind = "isHealthy";
        TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, this.travelerInstance, CLASS);
    }

    @Order(6)
    @Test
    void travelerClass_isHealthyField_usesPrivateModifier_Test() {
        String EXPECTED_MODIFIER = "PROTECTED";
        String fieldNameToFind = "isHealthy";

        TestUtil.getFieldValueInClassUsingReflection(fieldNameToFind, this.travelerInstance, CLASS);
        assertTrue(TestUtil.doesClassFieldUseCorrectModifier(fieldNameToFind, EXPECTED_MODIFIER, CLASS), "Please Double check that your field " + fieldNameToFind + " uses the " + EXPECTED_MODIFIER + "modifier.");
    }

    @Order(7)
    @Test
    void travelerClass_hasZero_Parameter_Constructor() throws NoSuchElementException {
        String field = "name";
        String result = TestUtil.getFieldValueInClassUsingReflection(field, this.travelerInstance, CLASS);
        assertEquals(DEFAULT_NAME, result, "Please verify that you initialize name to " + DEFAULT_NAME + " in the default constructor");

        field = "food";
        result = TestUtil.getFieldValueInClassUsingReflection(field, this.travelerInstance, CLASS);
        assertEquals("1", result, "Please verify that you initialize food to 1 in the default constructor");

        field = "isHealthy";
        result = TestUtil.getFieldValueInClassUsingReflection(field, this.travelerInstance, CLASS);
        assertEquals("true", result, "Please verify that you initialize isHealthy to true in the default constructor");
    }

    @Order(8)
    @Test
    void travelerClass_hasOne_StringParameter_Constructor() throws NoSuchElementException {
        Traveler t = TestUtil.getDefaultTestTraveler("Hanna Montana");

        String field = "name";
        String result = TestUtil.getFieldValueInClassUsingReflection(field, t, CLASS);
        assertEquals("Hanna Montana", result, "Please verify that you initialize name in the parameterized constructor");

        field = "food";
        int resultFood = Integer.parseInt(TestUtil.getFieldValueInClassUsingReflection(field, t, CLASS));
        assertEquals(TEST_INITIAL_FOOD, resultFood, "Please verify that you initialize food in the parameterized constructor");

        field = "isHealthy";
        result = TestUtil.getFieldValueInClassUsingReflection(field, t, CLASS);
        assertEquals("true", result, "Please verify that you initialize isHealthy in the parameterized constructor");
    }

    @Order(9)
    @Test
    void travelerClass_Name_GettersAndSetters_Exist() throws NoSuchMethodException {
        Method getName = Traveler.class.getMethod("getName");
        Method setName = Traveler.class.getMethod("setName", String.class);
    }

    @Order(9)
    @Test
    void travelerClass_Food_GettersAndSetters_Exist() throws NoSuchMethodException {
        Method getFood = Traveler.class.getMethod("getFood");
        Method setFood = Traveler.class.getMethod("setFood", int.class);
    }

    @Order(9)
    @Test
    void travelerClass_IsHealthy_GettersandSetters_Exist() throws NoSuchMethodException {
        Method getIsHealthy = Traveler.class.getMethod("isHealthy");
        Method setIsHealthy = Traveler.class.getMethod("setHealthy", boolean.class);
    }

    @Order(10)
    @Test
    void travelerClass_AllMethods_ExcludingGettersAndSetters_Exist() throws NoSuchMethodException {
        Method trap = Traveler.class.getMethod("trap");
        Method eat = Traveler.class.getMethod("eat");
    }


    @DisplayName("Traveler class can be instantiated with no arguments")
    @Order(11)
    @Test
    void canCreateTraveler() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Traveler person = TestUtil.getDefaultTestTraveler("Katherine");

        Method getFood = Traveler.class.getMethod("getFood");
        Method isHealthy = Traveler.class.getMethod("isHealthy");

        assertEquals(TEST_INITIAL_FOOD, (int) getFood.invoke(person), "Traveler.food starts at 1");
        assertTrue((boolean) isHealthy.invoke(person), "Traveler.isHealthy starts at true");
    }

    @DisplayName("Traveler class can trap")
    @Order(12)
    @Test
    void canHunt() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Traveler person = TestUtil.getDefaultTestTraveler("Water bottle");

        Method getFood = Traveler.class.getMethod("getFood");
        Method trap = Traveler.class.getMethod("trap");

        trap.invoke(person);
        assertEquals(TEST_HUNT_FOOD + TEST_INITIAL_FOOD, (int) getFood.invoke(person), "Traveler.food after trapping");
    }

    @DisplayName("Traveler class can eat")
    @Order(13)
    @Test
    void canEat() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Traveler person = TestUtil.getDefaultTestTraveler("Spork");

        Method getFood = Traveler.class.getMethod("getFood");
        Method eat = Traveler.class.getMethod("eat");

        eat.invoke(person);
        assertEquals(0, (int) getFood.invoke(person), "Traveler.food after eating is 0");
    }

    @DisplayName("Traveler class health is updated when no food")
    @Order(15)
    @Test
    void canUpdateHealth() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Traveler person = TestUtil.getDefaultTestTraveler("Pear");

        Method getFood = Traveler.class.getMethod("getFood");
        Method eat = Traveler.class.getMethod("eat");
        Method isHealthy = Traveler.class.getMethod("isHealthy");

        eat.invoke(person);
        assertEquals(0, (int) getFood.invoke(person), "Traveler.food after eating is 0");
        assertTrue((boolean) isHealthy.invoke(person), "Traveler.isHealthy after eating once is true");

        eat.invoke(person);
        assertFalse((boolean) isHealthy.invoke(person), "Traveler.isHealthy after eating once is false");
    }
}