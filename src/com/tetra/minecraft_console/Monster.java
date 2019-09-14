package com.tetra.minecraft_console;

import java.util.Random;
import java.util.Scanner;

public class Monster {
    String[] mobList = {"Zombie", "Skeleton", "Spider"};
    int mobHealth = 10;
    int mobStrenght = 3;
    String mobType = new String();

    Random r = new Random();
    int random = r.nextInt(3);



    public Monster(Environnement environnement){
        mobType = mobList[random];
        if (!environnement.isDay) {
            mobStrenght += 4;
        }
        if (!environnement.isSunny) {
            mobStrenght += 1;
        }
    }


    void hitThePlayer(Player P){
        if(P.Health > this.mobStrenght) {
            P.Health -= mobStrenght;
            System.out.println("You have been hit by " + mobType + " -" + mobStrenght + "HP.");
            System.out.println("You have " + P.Health + " health points left.");
        } else {
            P.Health = 0;
            System.out.println("You died... Would you respawn?");
            Scanner I = new Scanner(System.in);
            String respawnChoice = I.nextLine();
            if (respawnChoice == "Yes") {
                P.Health = 20;
                P.Exp = 0;
                System.out.println("Hello, what a new day in this world!");

            } else {
                System.out.println("Oh, sorry, good bye...");
                System.exit(0);
            }
        }
    }
    void hitByPlayer(Player P) {
        if (this.mobHealth > P.Strenght) {
            this.mobHealth -= P.Strenght;
            System.out.println("You hit " + mobType + " -" + P.Strenght + " HP.");
            System.out.println(mobHealth + " health points left.");
        } else {
            this.mobHealth = 0;
            System.out.println(mobType + " is defeated.");
            P.Exp += 20;
            P.Strenght = P.Exp * 0.5 + 5;
            System.out.println("You earned 20 exp points. You now have " + P.Exp + " exp points.");
            P.Health += 1;
            System.out.println("You slowly regen yourself. You have " + P.Health + " health points.");

        }
    }
}
