package ru.krivonogov.springexample;

import org.springframework.stereotype.Component;

@Component("someRockMusic")
public class RockMusic implements Music{
    @Override
    public String getSong() {
        return "Wind cries Mary";
    }
}
