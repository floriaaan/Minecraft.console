package com.tetra.minecraft_console;

import static com.tetra.minecraft_console.Main.lang;

public class Item {
    private String ItemType = "";
    int Amount = 0;
    boolean isBlock;

    public Item(String item_type, int amount, boolean is_block){
        setItemType(lang.Messages.getString(item_type));
        setAmount(amount);
        isBlock = is_block;
    }





    boolean IsEmpty() {
        return (getAmount() == 0);
    }
    boolean IsFull() { return (getAmount() == 64); }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }
}
