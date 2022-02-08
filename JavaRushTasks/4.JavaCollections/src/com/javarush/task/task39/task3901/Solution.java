package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null) return 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
//            System.out.println("Пробуем с символа: " + i);

            for (int j = i; j <= s.length(); j++) {
                String sForAnalyse = s.substring(i, j);
//                System.out.println("Подстрока для анализа: " + sForAnalyse);
                if (checkUnique(sForAnalyse)) {
//                    System.out.println("Хорошая строка");
                    if (result < sForAnalyse.length()) {
                        result = sForAnalyse.length();
                    }
                }
            }
        }
        return result;
    }
    
    public static boolean checkUnique (String s) {
        Set<String> setChar = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
//            System.out.println("i: " + i);
//            System.out.println(s.substring(i, i + 1));
            setChar.add(s.substring(i, i + 1));
        }
        return setChar.size() == s.length();
    }
}
