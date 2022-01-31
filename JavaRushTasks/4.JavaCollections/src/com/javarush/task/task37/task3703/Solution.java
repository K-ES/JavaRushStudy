package com.javarush.task.task37.task3703;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/* 
Найти класс по описанию Ӏ Java Collections: 7 уровень, 6 лекция
*/

public class Solution {
    Map m = new HashMap();

    public static void main(String[] args) {
        System.out.println("Тестирование разных Map");

        Solution s = new Solution();
        s.go();

    }

    public Solution () {
    }

    public void go () {
        ThreadAdd ta = new ThreadAdd();
        ta.start();

        ThreadGet tg = new ThreadGet();
        tg.start();

    }

    public class ThreadAdd extends Thread {
        @Override
        public void run() {
            System.out.println("Я поток добавления!");
            for (int i = 0; i < 10000000; i++) {
                m.put(i,i);
            }
            System.out.println("Все элементы добавлены!");
        }
    }

    public class ThreadGet extends Thread {
        @Override
        public void run() {
            System.out.println("Я поток взятия!");
            for (int i = 10000000; i < 20000000; i++) {
                m.put(i,i);
//                m.get(i);
            }
            System.out.println("Все элементы взяты!");
        }
    }




    public static Class getExpectedClass() {
        return ConcurrentSkipListMap.class;
    }
}
