package ru.krivonogov.springexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music{
    private ClassicalMusic() {
    }

    public static ClassicalMusic getClassicalMusic() {
        return new ClassicalMusic();
    }

    public void doMyInit() {
        System.out.println("Doing my initialization");
    }

    public void doByDestroy() {
        System.out.println("Doing my destrcution");
    }

    @Override
    public String getSong() {
        return "Венгерская рапсодия";
    }
}