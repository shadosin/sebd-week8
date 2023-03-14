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
public class DoctorTest {
    public static final int TEST_INITIAL_FOOD = 1;
    public static final int TEST_HUNT_FOOD = 3;
    public static final int TEST_NO_FOOD = 0;
    public static final boolean TEST_ISHEALTHY = true;
    public static final boolean TEST_ISNOTHEALTHY = false;

    private String CLASSNAME = "Doctor";

    Object [] DOCTOR_CLASS_CONSTRUCTOR_VALUES = {"Homer"};

    Class<?> [] DOCTOR_CLASS_CONSTRUCTOR_TYPES = {String.class};

    Object [] EMPTY_DOCTOR_CLASS_CONSTRUCTOR_VALUES = {};

    Class<?> [] EMPTY_DOCTOR_CLASS_CONSTRUCTOR_TYPES = {};


    @Order(1)
    @Test
    void doctorClass_Extends_TravelerClass() throws NoSuchElementException{
        try {
            assertTrue(doesChildClassExtendParentClass(Doctor.class, Traveler.class), "The Doctor class should extend the Traveler class.");
        }
        catch(Exception e){
            assertTrue(false, "The Doctor class should extend the Traveler class.");
        }
    }

    @Order(2)
    @Test
    void doctorClass_hasZero_Parameter_Constructor() throws NoSuchElementException{
        try {
            getClassToConstruct(EMPTY_DOCTOR_CLASS_CONSTRUCTOR_VALUES, EMPTY_DOCTOR_CLASS_CONSTRUCTOR_TYPES, Doctor.class);
            String defaultName = getParentFieldValueInClassUsingReflection("name", EMPTY_DOCTOR_CLASS_CONSTRUCTOR_VALUES, EMPTY_DOCTOR_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("Doc", defaultName, "You did not set the default name to Doc");
            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false, "The Doctor class should have a Parameterless Constructor.");
        }
    }
    @Order(3)
    @Test
    void doctorClass_hasOne_StringParameter_Constructor() throws NoSuchElementException{
        try {
            getClassToConstruct(DOCTOR_CLASS_CONSTRUCTOR_VALUES, DOCTOR_CLASS_CONSTRUCTOR_TYPES, Doctor.class);
            String defaultName = getParentFieldValueInClassUsingReflection("name", DOCTOR_CLASS_CONSTRUCTOR_VALUES, DOCTOR_CLASS_CONSTRUCTOR_TYPES);
            assertEquals("Homer", defaultName, "You did not update the name property");
            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false, "The Doctor class should have a One Parameter Constructor that takes a single String parameter.");
        }
    }

    @Order(4)
    @Test
    void doctorClass_AllMethods_ExcludingGettersAndSetters_Exist() {
        try {
            Method heal = Doctor.class.getMethod("heal", Traveler.class);
        }
        catch(Exception e) {
            assertTrue(false, "Please double check your Doctor Class has the correct Constructors and that you have method for heal in your Doctor Class. This excludes getter and setter methods for this test.");
        }
    }


    @DisplayName("Doctor class can be instantiated with no arguments")
    @Order(5)
    @Test
    void canCreateDoctor() {
        try {
            Doctor doctor = getTestDoctor();
            @SuppressWarnings("unchecked")
            Method getFood = Doctor.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method getIsHealthy = Doctor.class.getMethod("getIsHealthy");

            assertTrue(doctor instanceof Traveler, "Doctor is instanceof Traveler");
            assertEquals(TEST_INITIAL_FOOD, (int)getFood.invoke(doctor), "Doctor.food starts at 1");
            assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(doctor), "Doctor.isHealthy starts at true");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Doctor class must be defined with getFood() and getIsHealthy() method");
        }
    }

    @DisplayName("Doctor class has implemented hunt")
    @Order(6)
    @Test
    void canHunt() {
        try {
            Doctor doctor = getTestDoctor();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Doctor.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method hunt = Doctor.class.getMethod("hunt");

            hunt.invoke(doctor);
            assertEquals(TEST_HUNT_FOOD,  (int)getFood.invoke(doctor), "Doctor.food after hunting is 3" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Doctor class must be defined with hunt() and getFood() methods");
        }

    }

    @DisplayName("Doctor class has implemented eat")
    @Order(7)
    @Test
    void canEat() {

        try {
            Doctor doctor = getTestDoctor();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Doctor.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method setFood = Doctor.class.getMethod("setFood",int.class);
            @SuppressWarnings("unchecked")
            Method eat = Doctor.class.getMethod("eat");

            eat.invoke(doctor);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(doctor), "Doctor.food after eating is 0" );
            setFood.invoke(doctor,1);
            eat.invoke(doctor);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(doctor), "Doctor.food after overeating is still 0" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Doctor class must be defined with eat(),setFood(), and getFood() methods");
        }
    }

    @DisplayName("Doctor class can update isGetHealthy")
    @Order(8)
    @Test
    void canUpdateHealth() {
        try {
            Doctor doctor = getTestDoctor();

            //define methods for invocation
            @SuppressWarnings("unchecked")
            Method getFood = Doctor.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method eat = Doctor.class.getMethod("eat");
            @SuppressWarnings("unchecked")
            Method getIsHealthy = Doctor.class.getMethod("getIsHealthy");

            eat.invoke(doctor);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(doctor), "Doctor.food after eating is 0" );
            assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(doctor), "Doctor.isHealthy after eating once is true" );

            eat.invoke(doctor);
            assertEquals(TEST_NO_FOOD, (int)getFood.invoke(doctor), "Doctor.food after overeating is still 0" );
            assertEquals(TEST_ISNOTHEALTHY,  (boolean)getIsHealthy.invoke(doctor), "Doctor.isHealthy after eating once is false" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Doctor class must be defined with eat(), getFood(), and getIsHealthy() methods");
        }
    }

    @DisplayName("Doctor class has implemented heal")
    @Order(9)
    @Test
    void canHeal() {
        try {
            Doctor doctor = getTestDoctor();
            Traveler traveler = getTestTraveler();

            //define doctor methods for invocation
            @SuppressWarnings("unchecked")
            Method heal = Doctor.class.getMethod("heal", Traveler.class);

            //define traveler methods for invocation
            @SuppressWarnings("unchecked")
            Method eat = Traveler.class.getMethod("eat");
            @SuppressWarnings("unchecked")
            Method getFood = Traveler.class.getMethod("getFood");
            @SuppressWarnings("unchecked")
            Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

            eat.invoke(traveler);
            assertEquals(TEST_NO_FOOD, (int) getFood.invoke(traveler), "Traveler.food after eating is 0" );
            assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(traveler), "Traveler.isHealthy after eating once is true" );

            eat.invoke(traveler);
            assertEquals(TEST_ISNOTHEALTHY, (boolean)getIsHealthy.invoke(traveler), "Traveler.isHealthy after eating once is false" );

            heal.invoke(doctor,traveler);
            assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(traveler), "Traveler.isHealthy after being healed" );
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail("Doctor class must be defined with heal() method. Traveler must be defined with getIsHealthy and eat methods");
        }

    }

    private static Doctor getTestDoctor() throws NoSuchElementException {
        try {
            @SuppressWarnings("unchecked")
            Constructor doctorConstructor = Doctor.class.getConstructor();
            @SuppressWarnings("unchecked")
            Doctor doctor = (Doctor) doctorConstructor.newInstance();
            return doctor;
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
            Class<Doctor> myClass = Doctor.class;

            Constructor constructor = myClass.getConstructor(paramTypes);
            Doctor traveler = (Doctor) constructor.newInstance(params);

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