package com.javarush.task.task37.task3703;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

/* 
Найти класс по описанию Ӏ Java Collections: 7 уровень, 6 лекция
*/

public class Solution {

    public static void main(String args[]) throws Exception {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        System.out.println("Выведем классы из Collections");
        Class m[] = Collections.class.getDeclaredClasses();
        for (int i = 0; i < m.length; i++) {
            System.out.println("Очередной класс: " + m[i]);
        }

        System.out.println("Выведем классы из Concurrent");
        Class c[] = Map.class.getDeclaredClasses();
        for (int i = 0; i < c.length; i++) {
            System.out.println("Очередной класс: " + c[i]);
        }
/*
            if (List.class.isAssignableFrom(m[i])) {
//                System.out.println("");
                if (Modifier.isPrivate(m[i].getModifiers())) {
                    System.out.println("Очередной класс: " + m[i]);
                    if (Modifier.isStatic(m[i].getModifiers())) {
//                        System.out.println("Методы класса: " +  Arrays.toString(clazz.getDeclaredMethods()));
                        try {
                            Method methodGet = m[i].getDeclaredMethod("get", int.class);
//                            System.out.println("Нашли подходящий метод");
                            methodGet.setAccessible(true);
//                            System.out.println("Конструкторы класса: " + Arrays.toString(clazz.getDeclaredConstructors()));
                            Constructor constructorMy = clazz.getDeclaredConstructors()[0];
                            constructorMy.setAccessible(true);

                            try {
//                                System.out.println("Пытаемся создать лист");;
                                List l = (List) constructorMy.newInstance();
                                l.get(0);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (IllegalArgumentException e) {
//                                System.out.println("Херь, двигаемся дальше");;
                            } catch (IndexOutOfBoundsException e) {
                                return clazz;
                            }

                        } catch (NoSuchMethodException e) {
                            System.out.println("А сейчас не нашли подходящий метод");;
                        }
                    }
                }
            }
*/
        return null;
    }

}
