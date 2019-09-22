package com.tetra.minecraft_console;

import java.util.Random;

public class Evenement {

    public static boolean MobApparition(Environnement e) {
        Random r = new Random();
        int random = r.nextInt(100);
        if (random > 80) {
            return true;
        }
        return false;
    }

    public static boolean ChosenOne(){
        Random r = new Random();
        int random = r.nextInt(100);
        if (random > 90) {
            return true;
        }
        return false;
    }

}
