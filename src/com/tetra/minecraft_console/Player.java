package com.tetra.minecraft_console;

import java.util.Scanner;

import static com.tetra.minecraft_console.Main.lang;

public class Player implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String Name;
    double gameVersion = 0.1;

    public Player(String playerName) {
        Name = playerName;
    }

    int Health = 20;
    double Exp = 0;
    double Strength = Exp * 0.5 + 5;

    Inventory inv = new Inventory();
    boolean chosenOne = Evenement.chosenOne();
    Tool favoriteTool = new Tool("Hand");
    Environnement env = new Environnement();
    Achievements ach = new Achievements();


    void PickABlock() {
        // if (!inv.items[inv.currentSlot].getItemType().equals("dirt")) {
        //     if(!inv.setCurrentSlotToSameItemType(new Item("dirt", 0, true))){
        //         this.inv.setCurrentSlotToFirstEmpty();
        //         inv.items[inv.currentSlot].resetItem("dirt", 0, true);
        //     }

        // }
        inv.addAmount(new Item("dirt", 0, true), 1);

        Object[] pick_a_block_args = {inv.items[inv.currentSlot].getItemTypeForDisplay()};
        String pick_a_block = lang.getMessage("pick_a_block", pick_a_block_args);
        System.out.println(pick_a_block);


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

        env.tellWeather();
        env.dimension.tellDimension();
        env.dimension.biome.tellBiome();

        System.out.println(lang.Messages.getString("describe_inv"));
        Scanner I = new Scanner(System.in);
        String choice = I.nextLine();
        I.close();

        if (choice.toLowerCase().equals(lang.Messages.getString("yes").toLowerCase())) {

            for (int k = 0; k < 36; k++) {
                if (inv.items[k].getAmount() > 0 && !inv.items[k].getItemType().equals("nothing")) {
                    if (inv.items[k].isBlock) {
                        Object[] describe_currentSlot_args = {Name, inv.items[k].getAmount(), inv.items[k].getItemTypeForDisplay()};
                        String describe_currentSlot = lang.getMessage("describe_currentBlock", describe_currentSlot_args);
                        System.out.println("\t\t - " + describe_currentSlot);
                    } else {
                        Object[] describe_currentSlot_args = {Name, inv.items[k].getAmount(), inv.items[k].getItemTypeForDisplay()};
                        String describe_currentSlot = lang.getMessage("describe_currentItem", describe_currentSlot_args);
                        System.out.println("\t\t - " + describe_currentSlot);
                    }
                }
            }
            if (inv.isInventoryEmpty()) {
                System.out.println(lang.Messages.getString("nothing_in_inv"));
            }
        }


    }

    void Regen() {
        if (Health < 20) {
            this.Health += (20 - this.Health);
            Object[] regen_args = {Health};
            String regen = lang.getMessage("regen", regen_args);
            System.out.println("\t" + regen);
        } else {
            // Health already full
        }
    }

    void TellMobEncountered() {
        System.out.println("hello");
    }

    void CutTrees(Tool tool) {
        if (!env.dimension.biome.woodType.equals("nothing")) {
            int WoodFortuneAmount = (int) (64 * favoriteTool.fortune);
            inv.addAmount(new Item(env.dimension.biome.woodType, 0, true), WoodFortuneAmount);

            Sys.forProgressBar(50);

            System.out.println("\t" + lang.Messages.getString("cut_trees"));
            this.Exp += (WoodFortuneAmount / 10);
        } else {
            System.out.println("\t" + lang.Messages.getString("cut_notreesinbiome"));
        }

    }

    void Mine() {

        if (env.dimension.dimID == 0) {

            inv.addAmount(new Item("cobblestone", 0, true), 64);
            inv.addAmount(new Item("dirt", 0, true), 32);

            int CoalFortuneAmount = (int) (16 * favoriteTool.fortune);
            inv.addAmount(new Item("coal", 0, false), CoalFortuneAmount);

            inv.addAmount(new Item("iron_ore", 0, true), 8);
            inv.addAmount(new Item("gold_ore", 0, true), 4);

            int LapisFortuneAmount = (int) (24 * favoriteTool.fortune);
            inv.addAmount(new Item("lapislazuli", 0, false), LapisFortuneAmount);

            int DiamondFortuneAmount = (int) (2 * favoriteTool.fortune);
            inv.addAmount(new Item("diamond", 0, false), DiamondFortuneAmount);

            int EmeraldFortuneAmount = (int) (1 * favoriteTool.fortune);
            inv.addAmount(new Item("emerald", 0, false), EmeraldFortuneAmount);

            inv.addAmount(new Item("obsidian", 0, true), 12);

            Sys.forProgressBar(20);

            this.Exp += (24 + (4 * 1.5)
                    + (DiamondFortuneAmount * 2) + (EmeraldFortuneAmount * 4)) / 2;
            System.out.println("\t" + lang.Messages.getString("mine"));

        } else if (env.dimension.dimID == 1) {
            int QuartzFortuneAmount = (int) (64 * favoriteTool.fortune);
            inv.addAmount(new Item("quartz", 0, false), QuartzFortuneAmount);

            Sys.forProgressBar(10);
            System.out.println("\t" + lang.Messages.getString( "nether_mine"));
        } else {
            //TODO: print other dim
        }
    }

    void Equip() {
        int index = inv.FirstOccurrence(new Item("diamond_pickaxe", 1, false));
        if (index < 0) {
            //Can't Equip
        } else {
            this.favoriteTool.copy(new Item("diamond_pickaxe", 1, false));
            this.favoriteTool.updateDurability();
            this.inv.clearSlot(index);
        }
    }

    void Craft(String ItemWanted) {
        //FirstOccurrence of woodlog to make 4* planks
        //FirstOccurrence of woodplanks (need 2 planks) to make 4* woodstick
        //FirstOccurrence of diamond and woodsticks (need 3 diamonds and 2 woodstick)
        //clearSlots
    }

    /*  Increments FavoriteTool.enchant if lower than 5.
     *   Augments FavoriteTool.fortune
     *   Requires LapisLazuli
     */
    void Enchant() {
        if (favoriteTool.enchant < 4) {
            if (inv.setCurrentSlotToSameItemType(new Item("lapislazuli", 0, false))) {
                if (inv.items[inv.currentSlot].getAmount() >= 32) {
                    inv.addAmount(new Item("lapislazuli", 0, false), -32);
                    favoriteTool.fortune = favoriteTool.fortune * 1.75;
                    favoriteTool.enchant++;
                    System.out.println("\t" + lang.Messages.getString("enchant_done"));

                    Object[] enchant_done2_args = {favoriteTool.item.getItemTypeForDisplay(), favoriteTool.enchant + 1};
                    String enchant_done2 = lang.getMessage("enchant_done2", enchant_done2_args);
                    System.out.println("\t" + enchant_done2);
                } else {
                    System.out.println("\t" + lang.Messages.getString("enchant_notenoughlapis"));
                }
            } else {
                System.out.println("\t" + lang.Messages.getString("enchant_nolapis"));
            }
        } else {
            System.out.println("\t" + lang.Messages.getString("enchant_toolmax"));
        }
    }

    void MakePortal(int dimID) {
        switch (dimID) {
            case 0:
                if (env.dimension.getDimID() != 0) {
                    System.out.println("\t" + lang.Messages.getString("portal_goback"));
                    env.dimension.setDimID(0);
                }
                break;
            case 1:
                if (env.dimension.getDimID() != 1) {
                    if (!env.dimension.dimVisited.contains(1)) {
                        if (inv.setCurrentSlotToSameItemType(new Item("obsidian", 0, true))) {
                            if (inv.items[inv.currentSlot].getAmount() >= 10) {
                                inv.addAmount(new Item("obsidian", 0, true), -10);
                                env.dimension.setDimID(1);
                                System.out.println("\t" + lang.Messages.getString("portal_nether"));
                                env.dimension.tellDimension();
                                env.dimension.biome.tellBiome();
                                env.dimension.dimVisited.add(1);
                            } else {
                                System.out.println("\t" + lang.Messages.getString("portal_notenoughobsidian"));
                            }

                        } else {
                            System.out.println("\t" + lang.Messages.getString("portal_noobsidian"));
                        }
                    } else {
                        env.dimension.setDimID(1);
                        System.out.println("\t" + lang.Messages.getString("portal_nether"));
                        env.dimension.tellDimension();
                        env.dimension.biome.tellBiome();
                    }
                } else {
                    //TODO: already nether
                    System.out.println("\t" + lang.Messages.getString("portal_alreadynether"));
                }

                break;
            case 2:
                break;
            case 3:
                break;

        }
    }
}
