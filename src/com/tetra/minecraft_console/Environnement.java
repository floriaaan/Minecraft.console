package com.tetra.minecraft_console;

import java.util.Random;

import static com.tetra.minecraft_console.Main.lang;

public class Environnement implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    boolean isDay;
    boolean isSunny;

    Random r = new Random();
    int weatherRand = r.nextInt(100);


    Dimension dimension = new Dimension();



    public Environnement() {
        isSunny = weatherRand <= 50;
        isDay = weatherRand <= 70;
    }

    void tellWeather() {

        if (isSunny) {
            System.out.println("\t" + lang.Messages.getString("is_sunny"));
        } else {
            System.out.println("\t" + lang.Messages.getString("is_rainy"));
        }
        if (isDay) {
            System.out.println("\t" + lang.Messages.getString("is_day"));
        } else {
            System.out.println("\t" + lang.Messages.getString("is_night"));
        }
    }


    void changeWeather(){
        weatherRand = r.nextInt(100);
        isSunny = weatherRand <= 50;
        isDay = weatherRand <= 70;
    }
}
