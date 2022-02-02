package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.*;
import java.nio.file.*;
import java.text.DateFormat;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
    Path path;
    List<Path> listFiles = new ArrayList<>();
    List<LogString> logStrings = new ArrayList<>();

    public LogParser(Path path) {
        this.path = path;
        
//        System.out.println("Читаем все логи...");
        File folder = new File(path.toString()); //path указывает на директорию

        MyVisitor myVisitor = new MyVisitor();
        try {
            Files.walkFileTree(Paths.get(path.toString()), myVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Список полученных файлов: " + listFiles);

        for (int i = 0; i < listFiles.size(); i++) {
//            System.out.println("Считываем очередной файл...");
//            System.out.println(listFiles.get(i).toString());

            File file = new File(listFiles.get(i).toString());
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(listFiles.get(i).toString())));
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
//        for (int i = 0; i < logStrings.size(); i++) {
//            System.out.println(logStrings.get(i));
//        }
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> setUniqueIPs = new HashSet<>();
        for (int i = 0; i < logStrings.size(); i++) {
            Date date = logStrings.get(i).date;
//            System.out.println("Анализируем дату: " + date);
            boolean addFlag = false;
            if (
                    (after == null ? true : date.compareTo(after) >= 0)
                    &&
                    (before == null ? true : date.compareTo(before) <= 0)

            )
                setUniqueIPs.add(logStrings.get(i).ip);

        }
        return setUniqueIPs;
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
        Date date;
        Event event;
        Long number;
        Status status;

        public LogString(String fullString) {
            String[] massString = fullString.split("\t");

            int parNumber = 0;
            ip = massString[parNumber++];
            user = massString[parNumber++];
            dateString = massString[parNumber++];

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("dd.MM.yy HH:mm:ss");
            try {
                date = simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

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
                    ", date='" + date + '\'' +
                    ", event=" + event +
                    ", number=" + number +
                    ", status=" + status +
                    '}';
        }
    }

    private class MyVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//            System.out.println("Очередной путь: " + file.getFileName());
            if (Files.isRegularFile(file) && file.getFileName().toString().endsWith(".log")) {
                listFiles.add(file);
            }
            return super.visitFile(file, attrs);
        }
    }
}