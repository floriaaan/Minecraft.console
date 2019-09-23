package com.tetra.minecraft_console;

import static com.tetra.minecraft_console.Main.lang;

public class Item {
    private String ItemType = "";
    private static int Amount = 0;
    boolean isBlock = false;

    public Item(){
        setItemType(lang.Messages.getString("dirt"));
        setAmount(64);
        isBlock = true;
    }





    static boolean IsEmpty() {
        return (getAmount() == 0);
    }

    public static int getAmount() {
        return Amount;
    }

    public static void setAmount(int amount) {
        Amount = amount;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }
}
