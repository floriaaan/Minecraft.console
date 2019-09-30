package com.tetra.minecraft_console;

import java.util.Random;

import static com.tetra.minecraft_console.Main.lang;

public class Environnement {
    boolean isDay;
    boolean isSunny;

    Random r = new Random();
    int random = r.nextInt(100);

    public Environnement() {
        isSunny = random <= 50;
        isDay = random <= 70;
    }

    void tellWeather() {

        if (isSunny) {
            System.out.println(lang.Messages.getString("is_sunny"));
        } else {
            System.out.println(lang.Messages.getString("is_rainy"));
        }
        if (isDay) {
            System.out.println(lang.Messages.getString("is_day"));
        } else {
            System.out.println(lang.Messages.getString("is_night"));
        }
    }
}
