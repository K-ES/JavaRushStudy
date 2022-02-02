package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
//        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
//        LogParser logParser = new LogParser(Paths.get("K:\\Kes\\JRHome\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));

        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss");
        String str = "13.09.2013 5:04:50";
        try {
            Date parsingDate = ft.parse(str);
            System.out.println(parsingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}