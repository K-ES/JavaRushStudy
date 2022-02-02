package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
//        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        LogParser logParser = new LogParser(Paths.get("K:\\Kes\\JRHome\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2025");
            date2 = new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2030");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Вызываем нашу функцию...");
        System.out.println(logParser.getUniqueIPs(null, date2));
//        System.out.println(logParser.getUniqueIPs(null, null));

    }
}