package com.tetra.minecraft_console;

public class Item {

    public Item(){
        ItemType = "Dirt";
        Amount = 64;
        isBlock = true;
    }

    String ItemType;
    static int Amount;
    boolean isBlock;



    static boolean IsEmpty() {
        return (Amount == 0);
    }

}
