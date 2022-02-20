package ru.krivonogov.springexample;

public class ClassicalMusic implements Music{
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
