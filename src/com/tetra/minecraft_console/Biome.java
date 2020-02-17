package com.tetra.minecraft_console;

import java.util.Random;

import static com.tetra.minecraft_console.Main.lang;

public class Biome implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    String BiomeName;
    String woodType = null;

    String[] biomeListOverworld = {"oak_forest", "birch_forest", "mesa", "roofed_forest"};


    public Biome(int dimID) {
        changeBiome(dimID);
    }

    public String getBiomeForDisplay() {
        return lang.Messages.getString(BiomeName);
    }

    void tellBiome() {
        Object[] tellbiome_args = {getBiomeForDisplay()};
        String tellbiome = lang.getMessage("tellbiome", tellbiome_args);
        System.out.println("\t" + tellbiome);
    }

    void changeBiome(int dimID) {

        if(dimID == 0) {
            BiomeName = biomeListOverworld[(new Random()).nextInt(biomeListOverworld.length)];
            if (BiomeName.contains("oak")) {
                setWoodType("oak_log");
            } else if (BiomeName.contains("birch")) {
                setWoodType("birch_log");
            } else if (BiomeName.contains("roofed")) {
                setWoodType("dark_oak_log");
            } else if (BiomeName.contains("mesa")) {
                setWoodType("acacia_log");
            } else {
                setWoodType("oak_log");
            }
        } else if (dimID == 1) {
            setWoodType("nether_log");
        } else {
            setWoodType("oak_log");
        }

    }

    public String getBiomeName() {
        return BiomeName;
    }

    public void setBiomeName(String biomeName) {
        BiomeName = biomeName;
    }

    public String getWoodType() {
        return woodType;
    }

    public void setWoodType(String woodType) {
        this.woodType = woodType;
    }
}