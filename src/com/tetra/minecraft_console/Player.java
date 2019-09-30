package com.tetra.minecraft_console;

import java.util.Scanner;

import static com.tetra.minecraft_console.Main.lang;

public class Player implements java.io.Serializable {
    String Name;

    public Player(String playerName) {
        Name = playerName;
    }

    Inventory inv = new Inventory();
    int Health = 20;
    double Exp = 0;
    double Strength = Exp * 0.5 + 5;
    boolean ChosenOne = Evenement.ChosenOne();
    Tool FavoriteTool = new Tool("Hand");


    void PickABlock() {
        if (!inv.items[inv.currentSlot].getItemType().equals("dirt")) {
            this.inv.setCurrentSlotToFirstEmpty();
            inv.items[inv.currentSlot].resetItem("dirt", 0, true);
        }
        Object[] pick_a_block_args = {inv.items[inv.currentSlot].getItemTypeForDisplay()};
        String pick_a_block = lang.getMessage("pick_a_block", pick_a_block_args);
        System.out.println(pick_a_block);

        inv.addAmount(inv.items[inv.currentSlot], 1);

        Object[] now_have_args = {inv.items[inv.currentSlot].getAmount()};
        String now_have = lang.getMessage("now_have", now_have_args);
        System.out.println(now_have);

        this.Exp += 1;
    }

    void PlaceABlock() {
        if (inv.items[inv.currentSlot].getAmount() > 0) {
            Object[] place_a_block_args = {inv.items[inv.currentSlot].getItemTypeForDisplay()};
            String place_a_block = lang.getMessage("place_a_block", place_a_block_args);
            System.out.println(place_a_block);

            inv.addAmount(inv.items[inv.currentSlot], -1);

            Object[] now_have_args = {inv.items[inv.currentSlot].getAmount()};
            String now_have = lang.getMessage("now_have", now_have_args);
            System.out.println(now_have);
            this.Exp += 1;
        } else {
            if (inv.setCurrentSlotToFirstOccupied()) {
                System.out.println(lang.Messages.getString("place_a_block_slot_changed"));
            }
        }


    }

    void Describe() {
        Object[] describe_health_args = {Name, Health};
        String describe_health = lang.getMessage("describe_health", describe_health_args);
        System.out.println(describe_health);

        Object[] describe_exp_args = {Name, Exp};
        String describe_exp = lang.getMessage("describe_exp", describe_exp_args);
        System.out.println(describe_exp);

        System.out.println(lang.Messages.getString("describe_inv"));
        Scanner I = new Scanner(System.in);
        String choice = I.nextLine();

        if (choice.toLowerCase().equals(lang.Messages.getString("yes").toLowerCase())) {

            for (int k = 0; k < 36; k++) {
                if (inv.items[k].getAmount() > 0) {
                    if (inv.items[k].isBlock) {
                        Object[] describe_currentSlot_args = {Name, inv.items[k].getAmount(), inv.items[k].getItemTypeForDisplay()};
                        String describe_currentSlot = lang.getMessage("describe_currentBlock", describe_currentSlot_args);
                        System.out.println(describe_currentSlot);
                    } else {
                        Object[] describe_currentSlot_args = {Name, inv.items[k].getAmount(), inv.items[k].getItemTypeForDisplay()};
                        String describe_currentSlot = lang.getMessage("describe_currentItem", describe_currentSlot_args);
                        System.out.println(describe_currentSlot);
                    }
                }
            }
            if (inv.items[0].isEmpty()) {
                System.out.println(lang.Messages.getString("nothing_in_inv"));
            }
        }


    }

    void Regen() {
        this.Health += 3;
        Object[] regen_args = {Health};
        String regen = lang.getMessage("regen", regen_args);
        System.out.println(regen);
    }

    void TellMobEncountered() {
        System.out.println("hello");
    }

    void CutTrees(Tool tool) {
        int WoodFortuneAmount = 64; //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTEMENTS !!!
        inv.addAmount(new Item("wood_log", 0, true), WoodFortuneAmount);

        long total = 235;
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= total; i = i + 3) {
            try {
                Thread.sleep(50);
                Sys.printProgressBar(startTime, total, i);
            } catch (InterruptedException e) {
            }

        }

        System.out.println();
        System.out.println(lang.Messages.getString("cut_trees"));
        this.Exp += (WoodFortuneAmount / 10);
    }

    void Mine(Tool tool) {
        //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTMENTS !!!
        int CobbleFortuneAmount = 64;
        inv.addAmount(new Item("cobblestone", 0, true), CobbleFortuneAmount);
        int DirtFortuneAmount = 32;
        inv.addAmount(new Item("dirt", 0, true), DirtFortuneAmount);
        int IronOreFortuneAmount = 8;
        inv.addAmount(new Item("iron_ore", 0, true), IronOreFortuneAmount);
        int GoldOreFortuneAmount = 4;
        inv.addAmount(new Item("gold_ore", 0, true), GoldOreFortuneAmount);
        int DiamondFortuneAmount = 2;
        inv.addAmount(new Item("diamond", 0, false), DiamondFortuneAmount);
        int EmeraldFortuneAmount = 1;
        inv.addAmount(new Item("emerald", 0, false), EmeraldFortuneAmount);

        long total = 235;
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= total; i = i + 3) {
            try {
                Thread.sleep(200);
                Sys.printProgressBar(startTime, total, i);
            } catch (InterruptedException e) {
            }

        }
        System.out.println();
        this.Exp += ((CobbleFortuneAmount / 8) + (DirtFortuneAmount / 4) + IronOreFortuneAmount + (GoldOreFortuneAmount * 1.5)
                + (DiamondFortuneAmount * 2) + (EmeraldFortuneAmount * 4)) / 2;
        System.out.println(lang.Messages.getString("mine"));
    }

    void Equip() {
        int index = inv.FirstOccurrence(new Item("diamond_pickaxe", 1, false));
        if (index < 0) {
            //Can't Equip
        } else {
            this.FavoriteTool.copy("diamond_pickaxe", 1, false);
            this.FavoriteTool.updateDurability();
            this.inv.clearSlot(index);
        }
    }

    void Craft(String ItemWanted) {
        //FirstOccurrence of woodlog to make 4* planks
        //FirstOccurrence of woodplanks (need 2 planks) to make 4* woodstick
        //FirstOccurrence of diamond and woodsticks (need 3 diamonds and 2 woodstick)
        //clearSlots
    }
}
