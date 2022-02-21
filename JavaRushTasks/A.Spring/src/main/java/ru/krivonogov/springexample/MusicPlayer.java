package ru.krivonogov.springexample;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicPlayer {
    private ClassicalMusic classicalMusic;

    public MusicPlayer(ClassicalMusic classicalMusic) {        this.classicalMusic = classicalMusic;    }

    public void playMusic() {        System.out.println(classicalMusic.getSong());    }
}
