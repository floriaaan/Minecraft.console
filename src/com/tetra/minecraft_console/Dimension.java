package com.tetra.minecraft_console;

import java.util.Random;

public class Dimension {



    public int dimID;
    String[] dimName= {"overworld", "nether", "aether", "moon"};


    Biome biome = new Biome();

    public Dimension() {
        dimID = 0;
    }

    public int getDimID() {
        return dimID;
    }

    public void setDimID(int dimID) {
        this.dimID = dimID;
    }


}
