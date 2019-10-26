package com.tetra.minecraft_console;

import java.text.Normalizer;
import java.util.Scanner;

import static com.tetra.minecraft_console.Main.lang;

public class ConsoleInterface {


    static void WaitForInstructions(Player P) {
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
                   if (Evenement.MobApparition(P.Env)) {
                        Monster mob = new Monster(P.Env);
                        mob.Combat(P);
                        P.Regen();
                   }
                    break;
                case 3:
                    P.PlaceABlock();
                    if (Evenement.MobApparition(P.Env)) {
                        Monster mob = new Monster(P.Env);
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
                    P.Env.changeBiome();
                    P.Env.currBiome.tellBiome();
                    break;
                case 8:
                    Monster mob = new Monster(P.Env);
                    mob.Combat(P);
                    break;
                case 9:
                    P.Regen();
                    break;
                case 20:
                    P.TellMobEncountered();
                    break;
                case -1:
                    Sys.saveGame(P);
                    System.out.println(lang.Messages.getString("bye"));
                    PlayerWantsTo = "Exit";
                    break;
                case -2:
                    System.out.println(lang.Messages.getString("help_0"));
                    printHelp(9);
                    System.out.println("\t - " + lang.Messages.getString("help_exit") + "(x)");
                    break;
                default:
                    System.out.println(lang.Messages.getString("NotExpectingThis"));

            }
        }
    }


    static void printHelp(int nbInstruction){
        String help;
        nbInstruction++;
        for (int k = 1; k < nbInstruction; k++) {
            help = "help_" + k;
            System.out.println("\t - " + lang.Messages.getString(help) 
                                    + " (" + lang.Messages.getString(help).substring(0, 1).toLowerCase() + ")");
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
        String help_7 = lang.Messages.getString("help_7").toLowerCase();
        String help_8 = lang.Messages.getString("help_8").toLowerCase();
        String help_9 = lang.Messages.getString("help_9").toLowerCase();
        String help_exit = lang.Messages.getString("help_exit").toLowerCase();

        if (instruction.equals(help_1) || instruction.equals("dcrire") 
                                       || instruction.equals("d crire")) { //Due to Windows
            return 1;
        } else if (instruction.equals(help_2) || instruction.equals(help_2.substring(0, 1).toLowerCase())) {
            return 2;
        } else if (instruction.equals(help_3) || instruction.equals(help_3.substring(0, 1).toLowerCase())) {
            return 3;
        } else if (instruction.equals(help_4) || instruction.equals(help_4.substring(0, 1).toLowerCase())) {
            return 4;
        } else if (instruction.equals(help_5) || instruction.equals(help_5.substring(0, 1).toLowerCase())) {
            return 5;
        } else if (instruction.equals(help_6) || instruction.equals(help_6.substring(0, 1).toLowerCase())) {
            return 6;
        } else if (instruction.equals(help_7) || instruction.equals(help_7.substring(0, 1).toLowerCase())) {
            return 7;
        } else if (instruction.equals(help_8) || instruction.equals(help_8.substring(0, 1).toLowerCase())) {
            return 8;
        } else if (instruction.equals(help_9) || instruction.equals(help_9.substring(0, 1).toLowerCase())) {
            return 9;
        } else if (instruction.equals(help_exit) || instruction.equals("x")) {
            return -1;
        } else if (instruction.equals("/help")) {
            return -2;
        } else {
            System.out.println("INSTRUCTION :: " + instruction);
            return 0;
        }
    }
}