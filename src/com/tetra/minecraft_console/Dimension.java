package com.tetra.minecraft_console;

import java.util.ArrayList;
import java.util.List;

import static com.tetra.minecraft_console.Main.lang;

public class Dimension implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5456419763409925876L;

    public Dimension() {
        setDimID(0);
    }

    public int dimID;
    public List<Integer> dimVisited = new ArrayList<Integer>();

    String dimName;
    String[] dimNameList = { "overworld", "nether", "aether", "moon", "end" };

    Biome biome = new Biome(this.getDimID());

    public int getDimID() {
        return dimID;
    }

    public void setDimID(int dimID) {
        this.dimID = dimID;
        setDimName();
        biome.changeBiome(dimID);
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
        if (getDimID() != 0) {
            Object[] telldim_args = { getDimForDisplay() };
            String telldim = lang.getMessage("telldim", telldim_args);
            System.out.println("\t\t" + telldim);
        }
    }
}
