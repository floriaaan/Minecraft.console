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
        return items[Slot].isEmpty();
    }

    void clearSlot(int Slot){
        items[Slot].setItemType("nothing");
        items[Slot].setAmount(0);
        items[Slot].isBlock = false;
    }
    void setCurrentSlotToFirstEmpty(){
        while(!items[currentSlot].isEmpty()) {
            ++currentSlot;
        }
    }

    boolean setCurrentSlotToFirstOccupied(){
        while(items[currentSlot].isEmpty() && currentSlot <= 34){
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

    int FirstOccurrence(Item item){
        for(int k = 0; k < 36; ++k){
            if(items[k].isEqual(item)) {
                return k;
            }
        }
        return -1;
    }

    void setCurrentSlotToSameItemType(Item item){
        int k = 0;
        while(!item.getItemType().equals(items[currentSlot].getItemType()) || (k < (currentSlot - k))){
            ++currentSlot;
            ++k;
        }
    }

    void addAmount(Item item, int addingAmount){
        this.setCurrentSlotToSameItemType(item);


    }


}
