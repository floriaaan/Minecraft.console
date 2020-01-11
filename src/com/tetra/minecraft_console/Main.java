package com.tetra.minecraft_console;


public class Main {
    public static Languages lang = new Languages();
    public static double gameVersion = 0.11;

    public static void main(String[] args) {
        System.out.println("\t####\t" + lang.Messages.getString("welcome") + "\t####\t");
        Player P = null;
        while (P == null){
            P = Sys.Load();
        }
        testVersion(P);

        P.env.changeWeather();
        P.env.tellWeather();
        P.env.currBiome.tellBiome();

        // Instructions
        ConsoleInterface.WaitForInstructions(P);

    }



    static void testVersion(Player P) {
        try{
            if (gameVersion > P.gameVersion) {
                System.out.println(lang.Messages.getString("GameVersionSavedLower"));
                P.gameVersion = gameVersion;
            }
        } catch (NullPointerException i) {
            i.printStackTrace();
        }
    }

}