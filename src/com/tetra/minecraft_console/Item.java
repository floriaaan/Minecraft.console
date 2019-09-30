package com.tetra.minecraft_console;

import static com.tetra.minecraft_console.Main.lang;

public class Item {
    private String ItemType = "";
    int Amount = 0;
    boolean isBlock;

    public Item(String item_type, int amount, boolean is_block) {
        setItemType(item_type);
        setAmount(amount);
        isBlock = is_block;
    }


    boolean isEmpty() {
        return (getAmount() == 0);
    }

    boolean isFull() {
        return (getAmount() == 64);
    }

    boolean isEqual(Item item) {
        return (this.getItemType().equals(item.getItemType())
                && (this.getAmount() == item.getAmount())
                && (this.isBlock == item.isBlock));
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getItemType(){ return ItemType; }
    public String getItemTypeForDisplay() {
        return lang.Messages.getString(ItemType);
    }

    public void setItemType(String item_type) {
        ItemType = item_type;
    }

    public void resetItem(String item_type, int amount, boolean is_block) {
        setItemType(item_type);
        setAmount(amount);
        isBlock = is_block;
    }


}
