package com.tetra.minecraft_console;

import java.util.Random;

public class Environnement {
    boolean isDay;
    boolean isSunny;

    Random r = new Random();
    int random = r.nextInt(100);

    public Environnement(){
        if (random > 50){
            isSunny = false;
        } else {
            isSunny = true;
        }
        if (random > 70){
            isDay = false;
        } else {
            isDay = true;
        }
    }

    void tellWeather(){

        if(isSunny) {
            System.out.println("The weather is clear today!");
        } else {
            System.out.println("The weather is pretty rainy today, it can be slippery.");
        }
        if (isDay){
            System.out.println("Have a great day!");
        } else {
            System.out.println("Night is coming fast, be extremely careful!");
        }
    }
}
