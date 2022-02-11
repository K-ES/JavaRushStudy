package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("абваб"));
    }

    public static boolean isPalindromePermutation(String s) {
        Map<String, Integer> m = new HashMap<>();
        s = s.toLowerCase();
        boolean result = false;
        
//        System.out.println("Поехали");
        for (int i = 0; i < s.length(); i++) {
//            System.out.println("Рассматриваем символ: " + s.substring(i, i + 1));
            if (!m.containsKey(s.substring(i, i + 1))) {
//                System.out.println("Складываем первый раз");
                m.put(s.substring(i, i + 1), 1);
            } else {
//                System.out.println("Прибавляем");
                m.put(s.substring(i, i + 1), m.get(s.substring(i, i + 1)) + 1);
            }
        }

        Integer check = 0;
        for (String sk : m.keySet()) {
//              System.out.println(sk + " " + m.get(sk));
              if (m.get(sk) % 2 != 0) {
                  check++;
              }
        }
//        System.out.println("Количество нечетных: " + check);
        
        return check <= 1;
    }
}
