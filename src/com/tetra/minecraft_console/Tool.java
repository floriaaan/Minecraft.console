package com.tetra.minecraft_console;

public class Tool {
    Item item = new Item("nothing", 0, false);
    int durability;
    double fortune = 1;

    public Tool(String itemType){
        item.isBlock = false;
        item.setItemType(itemType);
        item.Amount = 1;

        if(itemType.startsWith("iron")){
            durability = 16;
        } else if (itemType.startsWith("gold")){
            durability = 24;
        } else if (itemType.startsWith("diamond")) {
            durability = 64;
        } else if (itemType.startsWith("wooden")) {
            durability = 4;
        } else if (itemType.startsWith("stone")) {
            durability = 8;
        } else {
            durability = -1;
        }
    }

    void copy(String item_type, int amount, boolean is_block){
        item.setItemType(item_type);
        item.setAmount(amount);
        item.isBlock = is_block;
    }

    void updateDurability(){
        if(this.item.getItemType().startsWith("iron")){
            durability = 16;
        } else if (this.item.getItemType().startsWith("gold")){
            durability = 24;
        } else if (this.item.getItemType().startsWith("diamond")) {
            durability = 64;
        } else if (this.item.getItemType().startsWith("wooden")) {
            durability = 4;
        } else if (this.item.getItemType().startsWith("stone")) {
            durability = 8;
        } else {
            durability = -1;
        }
    }

}
