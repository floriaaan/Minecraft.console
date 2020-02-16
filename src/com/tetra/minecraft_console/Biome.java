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

    String[] biomeList = {"oak_forest", "birch_forest", "mesa", "roofed_forest"};


    public Biome() {
        changeBiome();
    }

    public String getBiomeForDisplay() {
        return lang.Messages.getString(BiomeName);
    }

    void tellBiome() {
        Object[] tellbiome_args = {getBiomeForDisplay()};
        String tellbiome = lang.getMessage("tellbiome", tellbiome_args);
        System.out.println("\t" + tellbiome);
    }

    void changeBiome() {

        BiomeName = biomeList[(new Random()).nextInt(biomeList.length)];
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