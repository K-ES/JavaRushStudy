package ru.krivonogov.springexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ClassicalMusic implements Music{
    @PostConstruct
    public void doMyInit() {
        System.out.println("Doing my initialization");
    }

    @PreDestroy
    public void doByDestroy() {
        System.out.println("Doing my destrcution");
    }

    @Override
    public String getSong() {
        return "Венгерская рапсодия";
    }
}
