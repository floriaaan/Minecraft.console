package com.tetra.minecraft_console;

public class Player {
    String Name;

    public Player(String playerName) {
        Name = playerName;
    }

	Inventory inv = new Inventory();
	int Health = 20;
	int Exp = 0;



    void PickABlock() {
        System.out.println("You mined a " + inv.items[inv.currentSlot].getItemType());
        inv.items[inv.currentSlot].setAmount(inv.items[inv.currentSlot].getAmount() + 1);
        System.out.println("You now have " + inv.items[inv.currentSlot].getAmount());
        this.inv.changeSlot();
    }

    void PlaceABlock() {
        System.out.println("You placed a " + inv.items[inv.currentSlot].getItemType());
        this.inv.changeSlot();
        System.out.println("You now have " + inv.items[inv.currentSlot].getAmount());
    }

    void Describe(){
        System.out.println(Name + " has " + Health + " health points.");
        System.out.println(Name + " has " + Exp + " exp. points.");
        System.out.println(Name + " has " + inv.items[inv.currentSlot].getAmount() + " block of "+ inv.items[inv.currentSlot].getItemType() + " in his hand.");
    }
}
