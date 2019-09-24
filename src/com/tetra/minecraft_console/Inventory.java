package com.tetra.minecraft_console;

public class Inventory {
    Item[] items = new Item[36];
    int currentSlot = 0;
    public Inventory(){
        for(int k = 0; k < 36; k++){
            items[k] = new Item(null, 0, false);
        }

    }


    boolean IsEmpty(int Slot) {
        return items[Slot].IsEmpty();
    }

    void clearSlot(){
        items[currentSlot].setItemType("");
        items[currentSlot].setAmount(0);
        items[currentSlot].isBlock = false;
    }
    void setCurrentSlotToFirstEmpty(){
        while(!items[currentSlot].IsEmpty()) {
            ++currentSlot;
        }
    }
}
