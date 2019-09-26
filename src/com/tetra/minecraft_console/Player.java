package com.tetra.minecraft_console;

import java.util.Scanner;

import static com.tetra.minecraft_console.Main.lang;

public class Player {
    String Name;

    public Player(String playerName) {
        Name = playerName;
    }

	Inventory inv = new Inventory();
	int Health = 20;
	double Exp = 0;
	double Strenght = Exp * 0.5 + 5;
	boolean ChosenOne = Evenement.ChosenOne();
	Tool FavoriteTool = new Tool("Hand");



    void PickABlock() {
        if(!inv.items[inv.currentSlot].getItemType().equals(lang.Messages.getString("dirt"))){
            this.inv.setCurrentSlotToFirstEmpty();
            inv.items[inv.currentSlot].resetItem("dirt",0, true);
        }
        Object[] pick_a_block_args = {inv.items[inv.currentSlot].getItemType()};
        String pick_a_block = lang.getMessage("pick_a_block", pick_a_block_args);
        System.out.println(pick_a_block);

        inv.items[inv.currentSlot].setAmount(inv.items[inv.currentSlot].getAmount() + 1);

        Object[] now_have_args = {inv.items[inv.currentSlot].getAmount()};
        String now_have = lang.getMessage("now_have", now_have_args);
        System.out.println(now_have);

        this.Exp += 1;
    }

    void PlaceABlock() {
        if(inv.items[inv.currentSlot].getAmount() > 0) {
            Object[] place_a_block_args = {inv.items[inv.currentSlot].getItemType()};
            String place_a_block = lang.getMessage("place_a_block", place_a_block_args);
            System.out.println(place_a_block);

            inv.items[inv.currentSlot].setAmount(inv.items[inv.currentSlot].getAmount() - 1);


            Object[] now_have_args = {inv.items[inv.currentSlot].getAmount()};
            String now_have = lang.getMessage("now_have", now_have_args);
            System.out.println(now_have);
            this.Exp += 1;
        } else {
            if(inv.setCurrentSlotToFirstOccupied()){
                System.out.println(lang.Messages.getString("place_a_block_slot_changed"));
            }
        }


    }

    void Describe(){
        Object[] describe_health_args = {Name, Health};
        String describe_health = lang.getMessage("describe_health", describe_health_args);
        System.out.println(describe_health);

        Object[] describe_exp_args = {Name, Exp};
        String describe_exp = lang.getMessage("describe_exp", describe_exp_args);
        System.out.println(describe_exp);

        System.out.println(lang.Messages.getString("describe_inv"));
        Scanner I = new Scanner(System.in);
        String choice = I.nextLine();

        if (choice.equals("Yes")){

            for(int k = 0; k < 36; k++) {
                if (inv.items[k].getAmount() > 0){
                    if(inv.items[k].isBlock) {
                        Object[] describe_currentSlot_args = {Name, inv.items[k].getAmount(), inv.items[k].getItemType()};
                        String describe_currentSlot = lang.getMessage("describe_currentBlock", describe_currentSlot_args);
                        System.out.println(describe_currentSlot);
                    } else {
                        Object[] describe_currentSlot_args = {Name, inv.items[k].getAmount(), inv.items[k].getItemType()};
                        String describe_currentSlot = lang.getMessage("describe_currentItem", describe_currentSlot_args);
                        System.out.println(describe_currentSlot);
                    }
                }
            }
        }




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

    //TODO : Make items stackable
    void CutTrees(Tool tool){
        inv.setCurrentSlotToFirstEmpty();
        int WoodFortuneAmount = 64; //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTEMENTS !!!
        inv.items[inv.currentSlot] = new Item("wood_log", WoodFortuneAmount, true);
        System.out.println(lang.Messages.getString("cut_trees"));
        this.Exp += (WoodFortuneAmount/10);
    }

    void Mine(Tool tool){
        //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTMENTS !!!
        inv.setCurrentSlotToFirstEmpty();
        int CobbleFortuneAmount = 64;
        inv.items[inv.currentSlot] = new Item("cobblestone", CobbleFortuneAmount, true);
        inv.setCurrentSlotToFirstEmpty();
        int DirtFortuneAmount = 32;
        inv.items[inv.currentSlot] = new Item("dirt", DirtFortuneAmount, true);
        inv.setCurrentSlotToFirstEmpty();
        int IronOreFortuneAmount = 8;
        inv.items[inv.currentSlot] = new Item("iron_ore", IronOreFortuneAmount, true);
        inv.setCurrentSlotToFirstEmpty();
        int GoldOreFortuneAmount = 4;
        inv.items[inv.currentSlot] = new Item("gold_ore", GoldOreFortuneAmount, true);
        inv.setCurrentSlotToFirstEmpty();
        int DiamondFortuneAmount = 2;
        inv.items[inv.currentSlot] = new Item("diamond", DiamondFortuneAmount, false);
        inv.setCurrentSlotToFirstEmpty();
        int EmeraldFortuneAmount = 1;
        inv.items[inv.currentSlot] = new Item("emerald", EmeraldFortuneAmount, false);
        this.Exp += ((CobbleFortuneAmount/8) + (DirtFortuneAmount/4) + IronOreFortuneAmount + (GoldOreFortuneAmount*1.5)
                + (DiamondFortuneAmount*2) + (EmeraldFortuneAmount*4))/2;
        System.out.println(lang.Messages.getString("mine"));

    }

    void Equip(){
        int index = inv.FirstOccurrence(new Item("diamond_pickaxe", 1, false));
        if(index < 0) {
            //Can't Equip
        } else {
            this.FavoriteTool.copy("diamond_pickaxe", 1, false);
            this.FavoriteTool.updateDurability();
            this.inv.clearSlot(index);
        }
    }

    void Craft(String ItemWanted){
        //FirstOccurrence of woodlog to make 4* planks
        //FirstOccurrence of woodplanks (need 2 planks) to make 4* woodstick
        //FirstOccurrence of diamond and woodsticks (need 3 diamonds and 2 woodstick)
        //clearSlots
    }
}
