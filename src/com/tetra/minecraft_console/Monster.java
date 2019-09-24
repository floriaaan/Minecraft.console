package com.tetra.minecraft_console;

import java.util.Random;
import java.util.Scanner;

import static com.tetra.minecraft_console.Main.lang;

public class Monster {
    String[] mobList = {lang.Messages.getString("zombie"),
                lang.Messages.getString("skeleton"),
                lang.Messages.getString("spider")};
    int mobHealth = 10;
    double mobStrenght = 3;
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
        if(P.Health >= this.mobStrenght) {
            P.Health -= mobStrenght;

            Object[] hit_the_player_args = {mobType, mobStrenght};
            String hit_the_player = lang.getMessage("hit_the_player", hit_the_player_args);
            System.out.println(hit_the_player);

            Object[] hit_the_player_hp_remaining_args = {P.Health};
            String hit_the_player_hp_remaining = lang.getMessage("hit_the_player_hp_remaining", hit_the_player_hp_remaining_args);
            System.out.println(hit_the_player_hp_remaining);

        } else {
            P.Health = 0;
            System.out.println(lang.Messages.getString("hit_the_player_dead"));
            Scanner I = new Scanner(System.in);
            String respawnChoice = I.nextLine();
            if (respawnChoice == "Yes") {
                P.Health = 20;
                P.Exp = 0;
                System.out.println(lang.Messages.getString("hit_the_player_respawn_yes"));

            } else {
                System.out.println(lang.Messages.getString("hit_the_player_respawn_no"));
                System.exit(0);
            }
        }
    }
    void hitByPlayer(Player P) {
        if (this.mobHealth > P.Strenght) {
            this.mobHealth -= P.Strenght;

            Object[] hit_by_player_args = {mobType, P.Strenght};
            String hit_by_player = lang.getMessage("hit_by_player", hit_by_player_args);
            System.out.println(hit_by_player);

            Object[] hit_by_player_hp_remaining_args = {mobHealth};
            String hit_by_player_hp_remaining = lang.getMessage("hit_by_player_hp_remaining", hit_by_player_hp_remaining_args);
            System.out.println(hit_by_player_hp_remaining);
        } else {
            this.mobHealth = 0;

            Object[] hit_by_player_dead_args = {mobType};
            String hit_by_player_dead = lang.getMessage("hit_by_player_dead", hit_by_player_dead_args);
            System.out.println(hit_by_player_dead);

            P.Exp += 20;
            P.Strenght = P.Exp * 0.5 + 5;

            Object[] hit_by_player_exp_args = {P.Exp};
            String hit_by_player_exp = lang.getMessage("hit_by_player_exp", hit_by_player_exp_args);
            System.out.println(hit_by_player_exp);



        }
    }
}
