package com.tetra.minecraft_console;

public class Item {
    String ItemType;
    static int Amount;

    public Item(){
        ItemType = "Dirt";
        Amount = 64;
    }

    static boolean IsEmpty() {
        return (Amount == 0);
    }

}
