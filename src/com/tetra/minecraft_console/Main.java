package com.tetra.minecraft_console;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	System.out.println("Welcome to Minecraft.console !");

	System.out.println("What is your name ?");
	Scanner userName = new Scanner(System.in);
	String PlayerName = userName.nextLine();
	System.out.println("Oh! Hello " + PlayerName);

	Player Steve = new Player(PlayerName);

	WaitForInstructions(Steve);

    }

    static void WaitForInstructions(Player P){
        String Instruction = null;
        while(Instruction != "Exit") {
            System.out.println("What do you want to do? You can make /help to have a list of what you can do.");

            Scanner I = new Scanner(System.in);
            Instruction = I.nextLine();
            switch (Instruction) {
                case "Describe":
                    P.Describe();
                    break;
                case "Pick a block":
                    P.PickABlock();
                    break;
                case "Place a block":
                    P.PlaceABlock();
                    break;
                case "/help":
                    System.out.println("You can:");
                    System.out.println("\t - Describe");
                    System.out.println("\t - Pick a block");
                    System.out.println("\t - Place a block");
                    System.out.println("\t - Exit");
                    break;
                case "Exit":
                    System.out.println("Good Bye ;)");
                    Instruction = "Exit";
            }
        }
    }
}
