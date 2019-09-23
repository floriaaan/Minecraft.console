package com.tetra.minecraft_console;


import java.util.Scanner;

public class Main {
    public static Languages lang = new Languages();

    public static void main(String[] args) {
        System.out.println(lang.Messages.getString("welcome"));

        // Setting Up the Game
        String PlayerName = AskForPlayerName();
        Environnement e = new Environnement();
        Player Steve = new Player(PlayerName);
        e.tellWeather();

        // Instructions
        WaitForInstructions(Steve, e);

    }

    static void WaitForInstructions(Player P, Environnement E) {
        String Instruction = null;
        while (Instruction != "Exit") {
            System.out.println(lang.Messages.getString("wait_for_instructions"));

            Scanner I = new Scanner(System.in);
            Instruction = I.nextLine();
            switch (Instruction) {
                case "Describe":
                    P.Describe();
                    break;
                case "Pick a block":
                    P.PickABlock();
                    if (Evenement.MobApparition(E)) {
                        Monster mob = new Monster(E);
                        //while ???
                        while (mob.mobHealth >= 0 || P.Health >= 0) {
                            mob.hitThePlayer(P);
                            mob.hitByPlayer(P);
                        }
                        P.Regen();
                    }
                    break;
                case "Place a block":
                    P.PlaceABlock();
                    if (Evenement.MobApparition(E)) {
                        Monster mob = new Monster(E);
                        while (P.Health >= 0 || mob.mobHealth >= 0) {
                            mob.hitThePlayer(P);
                            mob.hitByPlayer(P);
                        }
                    }
                    break;
                case "/help":
                    System.out.println(lang.Messages.getString("help_0"));
                    System.out.println("\t - " + lang.Messages.getString("help_1"));
                    System.out.println("\t - " + lang.Messages.getString("help_2"));
                    System.out.println("\t - " + lang.Messages.getString("help_3"));
                    System.out.println("\t - " + lang.Messages.getString("help_4"));
                    break;
                case "Mob Bestiary":
                    P.TellMobEncountered();
                    break;
                case "Exit":
                    System.out.println(lang.Messages.getString("bye"));
                    Instruction = "Exit";
            }
        }
    }


    static String AskForPlayerName() {
        System.out.println(lang.Messages.getString("ask_for_playername"));
        Scanner userName = new Scanner(System.in);
        String PlayerName = userName.nextLine();

        Object[] playername_set_args = {PlayerName, };
        String playername_set = lang.getMessage("playername_set", playername_set_args);
        System.out.println(playername_set);

        return PlayerName;
    }
}