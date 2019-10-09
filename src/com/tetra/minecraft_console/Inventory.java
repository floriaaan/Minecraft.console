package com.tetra.minecraft_console;

import static com.tetra.minecraft_console.Main.lang;

public class Inventory implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Item[] items = new Item[36];
    int currentSlot = 0;

    public Inventory() {
        for (int k = 0; k < 36; k++) {
            items[k] = new Item("nothing", 0, false);
        }

    }


    boolean IsEmpty(int Slot) {
        return items[Slot].isEmpty();
    }

    void clearSlot(int Slot) {
        items[Slot].setItemType("nothing");
        items[Slot].setAmount(0);
        items[Slot].isBlock = false;
    }

    void setCurrentSlotToFirstEmpty() {
        currentSlot = 0;
        while (!items[currentSlot].isEmpty()) {
            ++currentSlot;
            if (currentSlot == 35){
                System.out.println("can't mine no more");
                break;
            }
        }
    }

    boolean setCurrentSlotToFirstOccupied() {
        currentSlot = 0;
        while (items[currentSlot].isEmpty() && currentSlot < 35) {
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


    //Return the index of the First Occurrence of an Item (usage: Tool class)
    int FirstOccurrence(Item item) {
        for (int k = 0; k < 36; ++k) {
            if (items[k].isSameType(item)) {
                return k;
            }
        }
        return -1;
    }

    /*
    Make currentSlot points on the first Item that equals Item in parameters.
    Return true if there is an Item that equals, else false.
    */
    boolean setCurrentSlotToSameItemType(Item item) {
        currentSlot = 0;
        while (!items[currentSlot].isSameType(item) && currentSlot < 35) {
            currentSlot++;
        }
        return (items[currentSlot].isSameType(item));
    }

    void addAmount(Item item, int addingAmount) {

        if (setCurrentSlotToSameItemType(item)) {
            //If amount is greater than 64
            if (items[currentSlot].getAmount() + addingAmount > 64) {
                //Testing if the current amount is already 64 if not adding the difference
                if (!items[currentSlot].isFull()) {
                    for (; addingAmount > 64; addingAmount -=64){
                        items[currentSlot].setAmount(64);
                        setCurrentSlotToFirstEmpty();
                        items[currentSlot] = new Item(item.getItemType(), 0, item.isBlock);
                    }
                    items[currentSlot] = new Item(item.getItemType(), addingAmount, item.isBlock);
                } else { // Slot is already full
                    setCurrentSlotToFirstEmpty();
                    items[currentSlot] = new Item(item.getItemType(), 0, item.isBlock);
                    for (; addingAmount > 64; addingAmount -=64){
                        items[currentSlot].setAmount(64);
                        setCurrentSlotToFirstEmpty();
                        items[currentSlot] = new Item(item.getItemType(), 0, item.isBlock);
                    }
                    items[currentSlot] = new Item(item.getItemType(), addingAmount, item.isBlock);
                }
            } else {
                items[currentSlot].setAmount(items[currentSlot].getAmount() + addingAmount);
            }
        } else {
            if (FirstOccurrence(item) == -1) {
                setCurrentSlotToFirstEmpty();
                
                items[currentSlot] = new Item(item.getItemType(), addingAmount, item.isBlock);
            }
        }
    }


}
