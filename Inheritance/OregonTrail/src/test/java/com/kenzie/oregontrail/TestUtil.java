package com.kenzie.oregontrail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class TestUtil {
    public static boolean doesChildClassExtendParentClass(Class<?> childClass, Class<?> parentClassToCheck) throws NoSuchElementException {
        try {
            if (childClass == null || parentClassToCheck == null) {
                throw new Exception("You need to provide a parent and child class to check if a child class extends a given parent class.");
            }

            if (childClass.getSuperclass() == parentClassToCheck) {
                return true;
            }
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }

        return false;
    }

    public static <T> T getClassToConstruct(Object[] params, Class<?>[] paramTypes, Class<?> classToConstruct) throws NoSuchElementException {
        try {
            if (params == null || paramTypes == null || classToConstruct == null) {
                throw new Exception("params, paramTypes, and classToConstruct parameters must be provided to use this method.");
            } else {
                Constructor constructor = classToConstruct.getConstructor(paramTypes);
                return (T) constructor.newInstance(params);
            }
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public static String getParentFieldValueInClassUsingReflection(String variableName, Class clazz, Traveler c) {
        try {
            Field field = clazz.getSuperclass().getDeclaredField(variableName);

            field.setAccessible(true);

            if (field.get(c) == null) {
                return "";
            }

            return field.get(c).toString();

        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Traveler getDefaultTestTraveler(String name) throws NoSuchElementException{
        try {
            Constructor constructor = Traveler.class.getConstructor(String.class);
            Traveler traveler = (Traveler) constructor.newInstance(name);
            return traveler;
        }
        catch(Exception e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public static List<Object> convertPassengerList(Object passengers) {
        if(passengers instanceof Traveler[]) {
            return Arrays.asList((Traveler[]) passengers);
        } else {
            return (List<Object>) passengers;
        }
    }

    public static boolean doesClassFieldUseCorrectModifier(String variableName, String expectedModifier, Class clazz) {
        try {
            if(expectedModifier == null || expectedModifier.isEmpty() || expectedModifier.isBlank() ) {
                throw new Exception("expectedModifier parameters must be provided and must be valid.");
            }

            Field field = clazz.getDeclaredField(variableName);

            int modifiers = field.getModifiers();

            if(Modifier.isProtected(modifiers)) {
                if(expectedModifier.equalsIgnoreCase("protected")) {
                    return true;
                }
            }
            else if(Modifier.isPrivate(modifiers)) {
                if(expectedModifier.equalsIgnoreCase("private")) {
                    return true;
                }
            }
            else if(Modifier.isPublic(modifiers)) {
                if(expectedModifier.equalsIgnoreCase("public")) {
                    return true;
                }
            }
            else if(Modifier.isAbstract(modifiers)) {
                if(expectedModifier.equalsIgnoreCase("abstract")) {
                    return true;
                }
            }
            else if(Modifier.isFinal(modifiers)) {
                if(expectedModifier.equalsIgnoreCase("final")) {
                    return true;
                }
            }
            else if(Modifier.isInterface(modifiers)) {
                if(expectedModifier.equalsIgnoreCase("interface")) {
                    return true;
                }
            }
            else if(Modifier.isStatic(modifiers)) {
                if(expectedModifier.equalsIgnoreCase("static")) {
                    return true;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static String getFieldValueInClassUsingReflection(String variableName, Object instance, Class clazz) {
        try {
            Field field = clazz.getDeclaredField(variableName);

            field.setAccessible(true);
            if (field.get(instance) == null){
                return "";
            }
            return field.get(instance).toString();

        } catch (IllegalAccessException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
