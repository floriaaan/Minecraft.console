package com.tetra.minecraft_console;

public class Player {
    String Name = "Steve";
	Inventory inv = new Inventory();
	int Health = 20;
	int Exp = 0;



    void PickABlock() {
        System.out.println("You mined a " + inv.items[inv.currentSlot].ItemType);
        ++inv.items[inv.currentSlot].Amount;
        System.out.println("You now have " + inv.items[inv.currentSlot].Amount);
        //this.inv.changeSlot();
    }

    void PlaceABlock() {
        System.out.println("You placed a " + inv.items[inv.currentSlot].ItemType);
        this.inv.changeSlot();
        System.out.println("You now have " + inv.items[inv.currentSlot].Amount);
    }

    void Describe(){
        System.out.println(Name + " has " + Health + " health points.");
        System.out.println(Name + " has " + Exp + " exp. points.");
        System.out.println(Name + " has a " + inv.items[inv.currentSlot].ItemType + " block in his hands.");
    }
}
