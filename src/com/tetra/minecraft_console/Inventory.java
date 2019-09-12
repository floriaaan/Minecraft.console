package com.tetra.minecraft_console;

public class Inventory {
    Item items[] = new Item[36];
    int currentSlot = 0;

    boolean IsEmpty(int Slot) {
        return items[Slot].IsEmpty();
    }

    void changeSlot(){
        if(!items[currentSlot].IsEmpty()) {
            ++currentSlot;
        }
    }

    void clearSlot(){
        items[currentSlot].setItemType("");
        items[currentSlot].setAmount(0);
        items[currentSlot].isBlock = false;
    }
}
