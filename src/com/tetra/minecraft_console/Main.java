package com.tetra.minecraft_console;



import java.text.Normalizer;
import java.util.Scanner;

public class Main {
    public static Languages lang = new Languages();


    public static void main(String[] args) {
        System.out.println("\t####\t" + lang.Messages.getString("welcome") + "\t####\t");
        Player P = null;
        while (P == null){
            P = Sys.Load();
        }
        Environnement e = new Environnement();
        e.tellWeather();

        // Instructions
        WaitForInstructions(P, e);

    }

    static void WaitForInstructions(Player P, Environnement E) {
        String PlayerWantsTo = null;
        while (PlayerWantsTo != "Exit") {
            System.out.println(lang.Messages.getString("wait_for_instructions"));

            Scanner I = new Scanner(System.in);
            PlayerWantsTo = I.nextLine();
            int Inst = Instructions(PlayerWantsTo);
            switch (Inst) {
                case 1:
                    P.Describe();
                    break;
                case 2:
                    P.PickABlock();
                   if (Evenement.MobApparition(E)) {
                        Monster mob = new Monster(E);
                        mob.Combat(P);
                        P.Regen();
                   }
                    break;
                case 3:
                    P.PlaceABlock();
                    if (Evenement.MobApparition(E)) {
                        Monster mob = new Monster(E);
                        mob.Combat(P);
                        P.Regen();
                    }
                    break;
                case 4:
                    P.Mine(P.FavoriteTool);
                    break;
                case 5:
                    P.CutTrees(P.FavoriteTool);
                    break;
                case 6:
                    P.Enchant();
                    break;
                case 7:
                    P.TellMobEncountered();
                    break;
                case -1:
                    Sys.saveGame(P);
                    System.out.println(lang.Messages.getString("bye"));
                    PlayerWantsTo = "Exit";
                    break;
                case -2:
                    System.out.println(lang.Messages.getString("help_0"));
                    System.out.println("\t - " + lang.Messages.getString("help_1"));
                    System.out.println("\t - " + lang.Messages.getString("help_2"));
                    System.out.println("\t - " + lang.Messages.getString("help_3"));
                    System.out.println("\t - " + lang.Messages.getString("help_4"));
                    System.out.println("\t - " + lang.Messages.getString("help_5"));
                    System.out.println("\t - " + lang.Messages.getString("help_6"));
                    System.out.println("\t - " + lang.Messages.getString("help_exit"));
                    break;
                default:
                    System.out.println(lang.Messages.getString("NotExpectingThis"));

            }
        }
    }




    static int Instructions(String instruction) {
        instruction = instruction.toLowerCase();
        instruction = Normalizer
                .normalize(instruction, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        String help_1 = lang.Messages.getString("help_1").toLowerCase();
        help_1 = Normalizer
                .normalize(help_1, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        String help_2 = lang.Messages.getString("help_2").toLowerCase();
        String help_3 = lang.Messages.getString("help_3").toLowerCase();
        String help_4 = lang.Messages.getString("help_4").toLowerCase();
        String help_5 = lang.Messages.getString("help_5").toLowerCase();
        String help_6 = lang.Messages.getString("help_6").toLowerCase();
        String help_exit = lang.Messages.getString("help_exit").toLowerCase();

        if (instruction.equals(help_1) || instruction.equals("dcrire") 
                                       || instruction.equals("d crire")) { //Due to Windows
            return 1;
        } else if (instruction.equals(help_2)) {
            return 2;
        } else if (instruction.equals(help_3)) {
            return 3;
        } else if (instruction.equals(help_4)) {
            return 4;
        } else if (instruction.equals(help_5)) {
            return 5;
        } else if (instruction.equals(help_6)) {
            return 6;
        } else if (instruction.equals(help_exit)) {
            return -1;
        } else if (instruction.equals("/help")) {
            return -2;
        } else {
            System.out.println("INSTRUCTION :: " + instruction);
            return 0;
        }
    }
}