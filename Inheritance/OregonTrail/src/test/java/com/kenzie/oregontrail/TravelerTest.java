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
    public static final int TEST_INITIAL_FOOD = 1;
    public static final int TEST_HUNT_FOOD = 3;
    public static final int TEST_NO_FOOD = 0;
    public static final boolean TEST_ISHEALTHY = true;
    public static final boolean TEST_ISNOTHEALTHY = false;

    public static final String TRAVELER_NAME = "Kenzie Student";

    Object [] TRAVELER_CLASS_CONSTRUCTOR_VALUES = {"Kenzie Student"};

    Class<?> [] TRAVELER_CLASS_CONSTRUCTOR_TYPES = {String.class};


    Object [] EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES = {};
    Class<?> [] EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES = {};

    private String CLASSNAME = "Traveler";

    @Order(1)
    @Test
    void travelerClass_HasField_name_Test() {
        String fieldNameToFind = "name";
        String fieldType = "String";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false, "Double check and make sure you have a field of type " + fieldType + " variable with the name " + fieldNameToFind + " in the " + CLASSNAME + " class.");
        }
    }
    @Order(2)
    @Test
    void travelerClass_nameField_usesPrivateModifier_Test() {
        String EXPECTED_MODIFIER = "PRIVATE";
        String fieldNameToFind = "name";
        String fieldType = "String";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(doesClassFieldUseCorrectModifier(fieldNameToFind, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES, EXPECTED_MODIFIER), "Please Double check that your field " + fieldNameToFind + " uses the " + EXPECTED_MODIFIER + "modifier.");
        }
        catch (Exception e) {
            assertTrue(false, "Double check that you have a field called: "+ fieldNameToFind + "of type " + fieldType + " in the " + CLASSNAME + " class that has the " + EXPECTED_MODIFIER + " Modifier.");
        }
    }

    @Order(3)
    @Test
    void travelerClass_HasField_food_Test() {
        String fieldNameToFind = "food";
        String fieldType = "int";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false, "Double check and make sure you have a field of type " + fieldType + " variable with the name " + fieldNameToFind + " in the " + CLASSNAME + " class.");
        }
    }

    @Order(4)
    @Test
    void travelerClass_foodField_usesPrivateModifier_Test() {
        String EXPECTED_MODIFIER = "PRIVATE";
        String fieldNameToFind = "food";
        String fieldType = "int";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(doesClassFieldUseCorrectModifier(fieldNameToFind, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES, EXPECTED_MODIFIER), "Please Double check that your field " + fieldNameToFind + " uses the " + EXPECTED_MODIFIER + "modifier.");
        }
        catch (Exception e) {
            assertTrue(false, "Double check that you have a field called: "+ fieldNameToFind + "of type " + fieldType + " in the " + CLASSNAME + " class that has the " + EXPECTED_MODIFIER + " Modifier.");
        }
    }

    @Order(5)
    @Test
    void travelerClass_HasField_isHealthy_Test() {
        String fieldNameToFind = "isHealthy";
        String fieldType = "boolean";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false, "Double check and make sure you have a field of type " + fieldType + " variable with the name " + fieldNameToFind + " in the " + CLASSNAME + " class.");
        }
    }
    @Order(6)
    @Test
    void travelerClass_isHealthyField_usesPrivateModifier_Test() {
        String EXPECTED_MODIFIER = "PRIVATE";
        String fieldNameToFind = "isHealthy";
        String fieldType = "String";

        try {
            getFieldValueInClassUsingReflection(fieldNameToFind, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertTrue(doesClassFieldUseCorrectModifier(fieldNameToFind, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES, EXPECTED_MODIFIER), "Please Double check that your field " + fieldNameToFind + " uses the " + EXPECTED_MODIFIER + "modifier.");
        }
        catch (Exception e) {
            assertTrue(false, "Double check that you have a field called: "+ fieldNameToFind + "of type " + fieldType + " in the " + CLASSNAME + " class that has the " + EXPECTED_MODIFIER + " Modifier.");
        }
    }
    @Order(7)
    @Test
    void travelerClass_hasZero_Parameter_Constructor() throws NoSuchElementException{
        try {
            @SuppressWarnings("unchecked")
            Constructor travelerConstructor = Traveler.class.getConstructor();
            @SuppressWarnings("unchecked")
            Traveler person = (Traveler) travelerConstructor.newInstance();
            String field = "name";
            String result = getFieldValueInClassUsingReflection(field, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("John Doe", result, "Please verify that you initialize name to John Doe in the default constructor");

            field = "food";
            result = getFieldValueInClassUsingReflection(field, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("1", result, "Please verify that you initialize food to 1 in the default constructor");

            field = "isHealthy";
            result = getFieldValueInClassUsingReflection(field, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_VALUES, EMPTY_TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("true", result, "Please verify that you initialize isHealthy to true in the default constructor");

            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false, "The Traveler class should have a Parameterless Constructor");
        }
    }

    @Order(8)
    @Test
    void travelerClass_hasOne_StringParameter_Constructor() throws NoSuchElementException{
        try {
            @SuppressWarnings("unchecked")
            Constructor travelerConstructor = Traveler.class.getConstructor(String.class);
            @SuppressWarnings("unchecked")
            Traveler person = (Traveler) travelerConstructor.newInstance(TRAVELER_NAME);
            String field = "name";
            String result = getFieldValueInClassUsingReflection(field, TRAVELER_CLASS_CONSTRUCTOR_VALUES, TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("Kenzie Student", result, "Please verify that you initialize name in the parameterized constructor");

            field = "food";
            result = getFieldValueInClassUsingReflection(field, TRAVELER_CLASS_CONSTRUCTOR_VALUES, TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("1", result, "Please verify that you initialize food in the parameterized constructor");

            field = "isHealthy";
            result = getFieldValueInClassUsingReflection(field, TRAVELER_CLASS_CONSTRUCTOR_VALUES, TRAVELER_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("true", result, "Please verify that you initialize isHealthy in the parameterized constructor");


            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false, "The Traveler class should have a One Parameter Constructor that takes a single String parameter.");
        }
    }

    @Order(9)
    @Test
    void travelerClass_Name_GettersandSetters_Exist() {
        try {
            Method getName =  Traveler.class.getMethod("getName");

            Method setName =  Traveler.class.getMethod("setName", String.class);


            assertTrue(true);
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Traveler Class has the correct Constructors and that your class also has getters and setters - getName, setName.");
        }
    }

    @Order(9)
    @Test
    void travelerClass_Food_GettersandSetters_Exist() {
        try {

            Method getFood =  Traveler.class.getMethod("getFood");

            Method setFood =  Traveler.class.getMethod("setFood", int.class);

            assertTrue(true);
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Traveler Class has the correct Constructors and that your class also has getters and setters - getFood, setFood.");
        }
    }

    @Order(9)
    @Test
    void travelerClass_IsHealthy_GettersandSetters_Exist() {
        try {

            Method getIsHealthy =  Traveler.class.getMethod("getIsHealthy");

            Method setIsHealthy = Traveler.class.getMethod("setIsHealthy", boolean.class);

            assertTrue(true);
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Traveler Class has the correct Constructors and that your class also has getters and setters for getIsHealthy and setIsHealthy. (NOTE: isHealthy() and setHealthy() are not valid)");
        }
    }

    @Order(10)
    @Test
    void travelerClass_AllMethods_ExcludingGettersAndSetters_Exist() {
        try {
            Method hunt =  Traveler.class.getMethod("hunt");

            Method eat =  Traveler.class.getMethod("eat");

            assertTrue(true);
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Traveler Class has the correct Constructors and that you have methods for hunt and eat in your Traveler Class. This excludes getter and setter methods for this test.");
        }
    }


    @DisplayName("Traveler class can be instantiated with no arguments")
    @Order(11)
    @Test
    void canCreateTraveler() {
        try {
            Traveler person = getTestTraveler();

            @SuppressWarnings("unchecked")
            Method getFood = Traveler.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

            assertEquals(TEST_INITIAL_FOOD, (int)getFood.invoke(person), "Traveler.food starts at 1" );
            assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(person), "Traveler.isHealthy starts at true");
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            System.out.println(e.getMessage());
            fail("Traveler class must be defined with getFood() and getIsHealthy() method");
        }
    }

    @DisplayName("Traveler class can hunt")
    @Order(12)
    @Test
    void canHunt() {
        try {
            Traveler person = getTestTraveler();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Traveler.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method hunt = Traveler.class.getMethod("hunt");

            hunt.invoke(person);
            assertEquals(TEST_HUNT_FOOD, (int)getFood.invoke(person), "Traveler.food after hunting is 3" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Traveler class must be defined with hunt() and getFood() methods");
        }
    }

    @DisplayName("Traveler class can eat")
    @Order(13)
    @Test
    void canEat() {
        try {
            Traveler person = getTestTraveler();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Traveler.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method eat = Traveler.class.getMethod("eat");

            eat.invoke(person);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(person), "Traveler.food after eating is 0" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Traveler class must be defined with eat() and getFood() methods");
        }
    }

    @DisplayName("Traveler class health is updated when no food")
    @Order(15)
    @Test
    void canUpdateHealth() {
        try {
            Traveler person = getTestTraveler();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Traveler.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method eat = Traveler.class.getMethod("eat");
            @SuppressWarnings("unchecked")
            Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

            eat.invoke(person);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(person), "Traveler.food after eating is 0" );
            assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(person), "Traveler.isHealthy after eating once is true" );
            eat.invoke(person);
            assertEquals(TEST_ISNOTHEALTHY, (boolean)getIsHealthy.invoke(person), "Traveler.isHealthy after eating once is false" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Traveler class must be defined with eat(), getFood(), and getIsHealthy() methods");
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

    private String getFieldValueInClassUsingReflection(String variableName, Object[] params, Class<?> [] paramTypes) {
        try {
            Class<Traveler> myClass = Traveler.class;

            Constructor constructor = myClass.getConstructor(paramTypes);
            Traveler traveler = (Traveler) constructor.newInstance(params);

            Field field = myClass.getDeclaredField(variableName);
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

    private boolean doesClassFieldUseCorrectModifier(String variableName, Object[] params, Class<?> [] paramTypes, String expectedModifier) {
        try {
            if(paramTypes == null || expectedModifier == null || expectedModifier.isEmpty() || expectedModifier.isBlank() ) {
                throw new Exception("paramTypes and expectedModifier parameters must be provided and must be valid.");
            }

            Class<Traveler> myClass = Traveler.class;

            Constructor constructor = myClass.getConstructor(paramTypes);
            Traveler traveler = (Traveler) constructor.newInstance(params);

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