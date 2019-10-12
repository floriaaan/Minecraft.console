package com.tetra.minecraft_console;

import static com.tetra.minecraft_console.Main.lang;

public class Biome implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String BiomeName;
    String woodType = null;

    public Biome(String name){
        BiomeName = name;
        if(BiomeName.contains("oak")) {
            woodType = "oak_log";
        } else if (BiomeName.contains("birch")) {
            woodType = "birch_log";
        }
        
    }
    public String getBiomeForDisplay() {
        return lang.Messages.getString(BiomeName);
    }

    void tellBiome(){
        Object[] tellbiome_args = {getBiomeForDisplay()};
                        String tellbiome = lang.getMessage("tellbiome", tellbiome_args);
                        System.out.println("\t" + tellbiome);
    }
}