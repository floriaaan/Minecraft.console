package com.tetra.minecraft_console;

import static com.tetra.minecraft_console.Main.lang;

public class Dimension {

    public Dimension() {
        setDimID(0);
    }

    public int dimID;
    String dimName;
    String[] dimNameList = {"overworld", "nether", "aether", "moon", "end"};

    Biome biome = new Biome(this.getDimID());



    public int getDimID() {
        return dimID;
    }

    public void setDimID(int dimID) {
        this.dimID = dimID;
        setDimName();
    }

    public String getDimName() {
        return dimName;
    }

    public void setDimName() {
        this.dimName = dimNameList[getDimID()];
    }

    public String getDimForDisplay() {
        return lang.Messages.getString(getDimName());
    }

    void tellDimension() {
        Object[] telldim_args = {getDimForDisplay()};
        String telldim = lang.getMessage("telldim", telldim_args);
        System.out.println("\t" + telldim);
    }
}
