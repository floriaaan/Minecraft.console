package com.tetra.minecraft_console;

import static com.tetra.minecraft_console.Main.lang;

public class Inventory {
    Item[] items = new Item[36];
    int currentSlot = 0;
    public Inventory(){
        for(int k = 0; k < 36; k++){
            items[k] = new Item("nothing", 0, false);
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

    boolean setCurrentSlotToFirstOccupied(){
        while(items[currentSlot].IsEmpty() && currentSlot <= 34){
            ++currentSlot;
            //System.out.println("SLOT CHANGED :: " + currentSlot);
        }
        if (currentSlot == 35) {
            currentSlot = 0;
            System.out.println(lang.Messages.getString("nothing_in_inv"));
            return false;
        }
        return true;
    }


}
