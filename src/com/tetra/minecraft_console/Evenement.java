package com.tetra.minecraft_console;

import java.util.Random;

public class Evenement {

    public static boolean MobApparition(Environnement e) {
        Random r = new Random();
        int random = r.nextInt(100);
        return random > 80;
    }

    public static boolean ChosenOne() {
        Random r = new Random();
        int random = r.nextInt(100);
        return random > 90;
    }

}
