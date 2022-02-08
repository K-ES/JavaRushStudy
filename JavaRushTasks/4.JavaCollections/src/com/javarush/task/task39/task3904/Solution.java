package com.javarush.task.task39.task3904;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Лестница
*/

public class Solution {
    static Map<Integer, Long> m = new HashMap();
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
        System.out.println("Тест");
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents1(n));
    }

    public static long numberOfPossibleAscents1(int n) {
        Long result = -1L;

        if (m.containsKey(n)) return m.get(n);

        if (n < 0)  result = 0L;
        if (n == 0) result = 1L;
        if (n == 1) result = 1L;
        if (n == 2) result = 3L;
        if (n == 3) result = 4L;

        if (n > 3) {
            result = (numberOfPossibleAscents(n-1) /*+ numberOfPossibleAscents(n - 1)*/) +
                    (numberOfPossibleAscents(n-2) /*+ numberOfPossibleAscents(n - 2)*/) +
                    (numberOfPossibleAscents(n-3) /*+ numberOfPossibleAscents(n - 3)*/);
        }

        m.put(n, result);
        return result;

    }
    public static long numberOfPossibleAscents(int n) {
        if (n < 0) {
            return 0;
        }
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);
        return numberOfPossibleAscents(n, memo);
    }

    private static long numberOfPossibleAscents(int n, long[] memo) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memo[n] > -1) {
            return memo[n];
        } else {
            memo[n] = numberOfPossibleAscents(n - 1, memo)
                    + numberOfPossibleAscents(n - 2, memo)
                    + numberOfPossibleAscents(n - 3, memo);
            return memo[n];
        }
    }
}

