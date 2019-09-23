package com.tetra.minecraft_console;

import java.text.MessageFormat;

import static com.tetra.minecraft_console.Main.lang;

public class Player {
    String Name;

    public Player(String playerName) {
        Name = playerName;
    }

	Inventory inv = new Inventory();
	int Health = 20;
	int Exp = 0;
	double Strenght = Exp * 0.5 + 5;
	boolean ChosenOne = Evenement.ChosenOne();



    void PickABlock() {
        System.out.println(lang.Messages.getString("pick_a_block") + inv.items[inv.currentSlot].getItemType());
        inv.items[inv.currentSlot].setAmount(inv.items[inv.currentSlot].getAmount() + 1);
        System.out.println(lang.Messages.getString("now_have") + inv.items[inv.currentSlot].getAmount());
        this.inv.changeSlot();
        this.Exp += 5;
    }

    void PlaceABlock() {
        System.out.println(lang.Messages.getString("place_a_block") + inv.items[inv.currentSlot].getItemType());
        inv.items[inv.currentSlot].setAmount(inv.items[inv.currentSlot].getAmount() - 1);
        this.inv.changeSlot();
        System.out.println(lang.Messages.getString("now_have") + inv.items[inv.currentSlot - 1].getAmount());
        this.Exp += 1;
    }

    void Describe(){
        Object[] describe_health_args = {Name, Health};
        String describe_health = lang.getMessage("describe_health", describe_health_args);
        System.out.println(describe_health);

        Object[] describe_exp_args = {Name, Exp};
        String describe_exp = lang.getMessage("describe_exp", describe_exp_args);
        System.out.println(describe_exp);

        Object[] describe_currentSlot_args = {Name, inv.items[inv.currentSlot].getAmount(), inv.items[inv.currentSlot].getItemType()};
        String describe_currentSlot = lang.getMessage("describe_currentSlot", describe_currentSlot_args);
        System.out.println(describe_currentSlot);
    }

    void Regen(){
        this.Health += 3;
        Object[] regen_args = {Health};
        String regen = lang.getMessage("regen", regen_args);
        System.out.println(regen);
    }

    void TellMobEncountered() {
        System.out.println("hello");
    }
}
