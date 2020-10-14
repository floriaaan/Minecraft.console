package com.tetra.minecraft_console;

import java.text.Normalizer;
import java.util.*;

import static com.tetra.minecraft_console.Main.lang;

public class ConsoleInterface {


    static void WaitForInstructions(Player P) {
        String PlayerWantsTo = "";
        while (!PlayerWantsTo.equals("Exit")) {
            System.out.println(lang.Messages.getString("wait_for_instructions"));

            Scanner I = new Scanner(System.in);
            PlayerWantsTo = I.nextLine();
            I.close();
            int Inst = Instructions(PlayerWantsTo);
            switch (Inst) {
                case 1:
                    P.Describe();
                    break;
                case 2:
                    P.PickABlock();
                    if (Evenement.mobApparition(P.env)) {
                        Monster mob = new Monster(P.env);
                        mob.Combat(P);
                        P.Regen();
                    }
                    break;
                case 3:
                    P.PlaceABlock();
                    if (Evenement.mobApparition(P.env)) {
                        Monster mob = new Monster(P.env);
                        mob.Combat(P);
                        P.Regen();
                    }
                    break;
                case 4:
                    P.Mine();
                    break;
                case 5:
                    P.CutTrees(P.favoriteTool);
                    break;
                case 6:
                    P.Enchant();
                    break;
                case 7:
                    P.env.dimension.biome.changeBiome(P.env.dimension.dimID);
                    P.env.dimension.biome.tellBiome();
                    break;
                case 8:
                    Monster mob = new Monster(P.env);
                    mob.Combat(P);
                    break;
                case 9:
                    P.Regen();
                    break;
                case 10:
                    P.MakePortal(1);
                    break;
                case 11:
                    P.MakePortal(0);
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
                    printHelp(11);
                    System.out.println("\t - " + lang.Messages.getString("help_exit") + "(x)");
                    break;
                default:
                    System.out.println(lang.Messages.getString("NotExpectingThis"));

            }
        }
    }


    static void printHelp(int nbInstruction) {
        int[] indexForSubstring = {1, 2, 2, 1, 3, 1, 1, 3, 1, 3, 1, 1};
        for (int k = 1; k <= nbInstruction; k++) {
            System.out.println("\t - " + lang.Messages.getString("help_" + k)
                    + " (" + lang.Messages.getString("help_" + k).substring(0, indexForSubstring[k - 1]).toLowerCase() + ")");
        }
    }


    static int Instructions(String instruction) {
        int[] indexForSubstring = {1, 2, 2, 1, 3, 1, 1, 3, 1, 3, 1, 1};
        instruction = instruction.toLowerCase();
        instruction = Normalizer
                .normalize(instruction, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        List<String> helps = new ArrayList<String>();

        String help_1 = lang.Messages.getString("help_1").toLowerCase();
        help_1 = Normalizer
                .normalize(help_1, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        helps.add(help_1);

        for (int k = 2; k <= 11; k++) {
            helps.add(lang.Messages.getString("help_" + k).toLowerCase());
        }

        helps.add(lang.Messages.getString("help_exit").toLowerCase());

        for (int k = 0; k < 12; k++) {
            if (instruction.equals(helps.get(k)) || instruction.equals(helps.get(k).substring(0, indexForSubstring[k]))) {
                return k + 1;
            }
        }

        if (instruction.equals("/help")) {
            return -2;
        } else {
            System.out.println("INSTRUCTION :: " + instruction);
            return 0;
        }
    }
}