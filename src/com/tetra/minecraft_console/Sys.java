package com.tetra.minecraft_console;

import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static com.tetra.minecraft_console.Main.lang;

public class Sys {
    private static String savePath = "saves/";

    static void printProgressBar(long startTime, long total, long current) {
        long eta = current == 0 ? 0 :
                (total - current) * (System.currentTimeMillis() - startTime) / current;

        String etaHms = current == 0 ? "N/A" :
                String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
                        TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1));

        StringBuilder string = new StringBuilder(140);
        int percent = (int) (current * 100 / total);
        string
                .append('\r')
                .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
                .append(String.format(" %d%% [", percent))
                .append(String.join("", Collections.nCopies(percent, "=")))
                .append('>')
                .append(String.join("", Collections.nCopies(100 - percent, " ")))
                .append(']')
                .append(String.join("", Collections.nCopies(current == 0 ? (int) (Math.log10(total)) : (int) (Math.log10(total)) - (int) (Math.log10(current)), " ")))
                .append(String.format(" " + lang.Messages.getString("ETA"), etaHms));

        System.out.print(string);
    }

    static void forProgressBar(int delay) {
        long total = 235;
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= total; i = i + 3) {
            try {
                Thread.sleep(delay);
                Sys.printProgressBar(startTime, total, i);
            } catch (InterruptedException e) {
            }

        }
        System.out.println();
    }


    static void saveGame(Player P) {
        System.out.println(lang.Messages.getString("save"));
        Scanner Input = new Scanner(System.in);
        String Instruction = Input.nextLine();
        if (Instruction.toLowerCase().equals(lang.Messages.getString("yes").toLowerCase())) {
            try {

                new File(Sys.savePath).mkdirs();
                File playerFile = new File(Sys.savePath + P.Name + ".ser");
                playerFile.createNewFile(); // if file already exists will do nothing
                FileOutputStream playerSaveFile = new FileOutputStream(playerFile);
                ObjectOutputStream out = new ObjectOutputStream(playerSaveFile);
                out.writeObject(P);
                out.close();
                playerSaveFile.close();
                System.out.println(lang.Messages.getString("game_saved"));
            } catch (IOException i) {
                i.printStackTrace();
            }
        }


    }

    static String AskForPlayerName() {
        System.out.println(lang.Messages.getString("ask_for_playername"));
        Scanner userName = new Scanner(System.in);
        String PlayerName = userName.nextLine();

        Object[] playername_set_args = {PlayerName};
        String playername_set = lang.getMessage("playername_set", playername_set_args);
        System.out.println(playername_set);

        return PlayerName;
    }

    static Player Load() { //TODO: if no saves folder, nullpointerexception
        System.out.println(lang.Messages.getString("title"));
        System.out.println("\t" + lang.Messages.getString("title_newgame")
                + " (" + lang.Messages.getString("title_newgame").substring(0, 1).toLowerCase() + ')');
        System.out.println("\t" + lang.Messages.getString("title_load")
                + " (" + lang.Messages.getString("title_load").substring(0, 1).toLowerCase() + ')');
        System.out.println("\t" + lang.Messages.getString("help_exit") + " (x)");

        Scanner Input = new Scanner(System.in);
        String Instruction = Input.nextLine();
        if (Instruction.toLowerCase().equals(lang.Messages.getString("title_newgame").toLowerCase())
                || Instruction.toLowerCase().equals(lang.Messages.getString("title_newgame").substring(0, 1).toLowerCase())) {
            String PlayerName = AskForPlayerName();
            return new Player(PlayerName);
        } else if (Instruction.toLowerCase().equals(lang.Messages.getString("title_load").toLowerCase())
                || Instruction.toLowerCase().equals(lang.Messages.getString("title_load").substring(0, 1).toLowerCase())) {
            File[] saves = new File(Sys.savePath).listFiles((dir, name) -> name.endsWith(".ser"));
            if (saves.length > 0) {
                for (int k = 0; k < saves.length; k++) {
                    int lastPeriodPos = saves[k].getName().lastIndexOf('.');
                    if (lastPeriodPos > 0)
                        System.out.println("#####\t" + saves[k].getName().substring(0, lastPeriodPos));
                }
            } else {
                System.out.println(lang.Messages.getString("title_nosave"));
                return null;
            }


            System.out.println(lang.Messages.getString("title_enterplayername"));
            String SaveName = Input.nextLine();
            File Save = new File(Sys.savePath + SaveName + ".ser");
            if (Save.exists()) {
                try {
                    FileInputStream playerSaveFile = new FileInputStream(Save);
                    ObjectInputStream in = new ObjectInputStream(playerSaveFile);
                    Player P = (Player) in.readObject();
                    in.close();
                    playerSaveFile.close();
                    System.out.println("\t" + lang.Messages.getString("title_welcomeback"));
                    return P;
                } catch (IOException | ClassNotFoundException i) {
                    i.printStackTrace();
                }
            } else {
                System.out.println(lang.Messages.getString("title_nosave"));
            }
        } else if (Instruction.toLowerCase().equals(lang.Messages.getString("help_exit").toLowerCase())
                || Instruction.toLowerCase().equals('x')) {
            System.out.println(lang.Messages.getString("bye"));
            System.exit(0);
        }
        System.out.println(lang.Messages.getString("NotExpectingThis"));
        return null;
    }


}
