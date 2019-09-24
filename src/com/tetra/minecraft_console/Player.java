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
	Tool Hand = new Tool("Hand");



    void PickABlock() {
        Object[] pick_a_block_args = {inv.items[inv.currentSlot].getItemType()};
        String pick_a_block = lang.getMessage("pick_a_block", pick_a_block_args);
        System.out.println(pick_a_block);

        inv.items[inv.currentSlot].setAmount(inv.items[inv.currentSlot].getAmount() + 1);

        Object[] now_have_args = {inv.items[inv.currentSlot].getAmount()};
        String now_have = lang.getMessage("now_have", now_have_args);
        System.out.println(now_have);

        this.inv.setCurrentSlotToFirstEmpty();
        this.Exp += 5;
    }

    void PlaceABlock() {
        Object[] place_a_block_args = {inv.items[inv.currentSlot].getItemType()};
        String place_a_block = lang.getMessage("place_a_block", place_a_block_args);
        System.out.println(place_a_block);

        inv.items[inv.currentSlot].setAmount(inv.items[inv.currentSlot].getAmount() - 1);
        this.inv.setCurrentSlotToFirstEmpty();

        Object[] now_have_args = {inv.items[inv.currentSlot - 1].getAmount()};
        String now_have = lang.getMessage("now_have", now_have_args);
        System.out.println(now_have);

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

    void CutTrees(Tool tool){
        inv.setCurrentSlotToFirstEmpty();
        int WoodFortuneAmount = 64; //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTEMENTS !!!
        inv.items[inv.currentSlot] = new Item("wood_log", WoodFortuneAmount, true);
    }

    void Mine(Tool tool){
        inv.setCurrentSlotToFirstEmpty();
        int CobbleFortuneAmount = 64; //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTEMENTS !!!
        inv.items[inv.currentSlot] = new Item("cobblestone", CobbleFortuneAmount, true);
        inv.setCurrentSlotToFirstEmpty();
        int DirtFortuneAmount = 32; //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTEMENTS !!!
        inv.items[inv.currentSlot] = new Item("dirt", DirtFortuneAmount, true);
        inv.setCurrentSlotToFirstEmpty();
        int IronOreFortuneAmount = 8; //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTEMENTS !!!
        inv.items[inv.currentSlot] = new Item("iron_ore", IronOreFortuneAmount, true);
        inv.setCurrentSlotToFirstEmpty();
        int GoldOreFortuneAmount = 4; //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTEMENTS !!!
        inv.items[inv.currentSlot] = new Item("gold_ore", CobbleFortuneAmount, true);
        inv.setCurrentSlotToFirstEmpty();
        int DiamondFortuneAmount = 2; //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTEMENTS !!!
        inv.items[inv.currentSlot] = new Item("diamond", DiamondFortuneAmount, true);
        inv.setCurrentSlotToFirstEmpty();
        int EmeraldFortuneAmount = 1; //TODO : Make depending on tool.fortune !!! REQUIRE ENCHANTEMENTS !!!
        inv.items[inv.currentSlot] = new Item("emerald", EmeraldFortuneAmount, true);
    }
}
