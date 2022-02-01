package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
    Path path;
    List<LogString> logStrings = new ArrayList<>();

    public LogParser(Path path) {
        this.path = path;
        
//        System.out.println("Читаем все логи...");
        File folder = new File(path.toString()); //path указывает на директорию
        for (File file : folder.listFiles())
        {
            System.out.println(file.getName());
//            System.out.println("Считываем очередной файл...");
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = new String();
                while( ( line = br.readLine() ) != null ) {
//                    System.out.println("Очередная строка: " + line);
                    logStrings.add(new LogString(line));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        System.out.println("Анализ списка объектов...");
//        System.out.println(logStrings.size());
//        System.out.println(logStrings.get(0));
//        System.out.println(logStrings.get(1));
//        System.out.println(logStrings.get(2));
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> setUniqueIPs = new HashSet<>();
        for (int i = 0; i < logStrings.size(); i++) {
            System.out.println(logStrings.get(i).ip);
            setUniqueIPs.add(logStrings.get(i).ip);
        }
        return setUniqueIPs.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return null;
    }

    class LogString {
        String ip;
        String user;
        String dateString;
        Event event;
        Long number;
        Status status;

        public LogString(String fullString) {
            String[] massString = fullString.split("\t");

            int parNumber = 0;
            ip = massString[parNumber++];
            user = massString[parNumber++];
            dateString = massString[parNumber++];



            String[] massOneTask = massString[parNumber++].split(" ");
            event = Event.valueOf(massOneTask[0]);
            if (event == Event.SOLVE_TASK || event == Event.DONE_TASK) {
                number = Long.parseLong(massOneTask[1]);
            }
            status = Status.valueOf(massString[parNumber++]);
        }

        @Override
        public String toString() {
            return "LogString{" +
                    "ip='" + ip + '\'' +
                    ", user='" + user + '\'' +
                    ", dateString='" + dateString + '\'' +
                    ", event=" + event +
                    ", number=" + number +
                    ", status=" + status +
                    '}';
        }
    }
}