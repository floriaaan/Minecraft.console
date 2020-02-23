package com.tetra.minecraft_console;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


public class Languages {
    Locale currentLocale = Locale.getDefault();
    ResourceBundle Messages = ResourceBundle.getBundle("com/tetra/minecraft_console/langs/Messages", currentLocale);

    ResourceBundle Item = ResourceBundle.getBundle("com/tetra/minecraft_console/langs/Item", currentLocale);
    ResourceBundle Biome = ResourceBundle.getBundle("com/tetra/minecraft_console/langs/Biome", currentLocale);
    ResourceBundle Achievements = ResourceBundle.getBundle("com/tetra/minecraft_console/langs/Achievements", currentLocale);

    String getMessage(String key, Object[] args) {
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(this.currentLocale);
        formatter.applyPattern(this.Messages.getString(key));

        return formatter.format(args);
    }

    String getItem(String key, Object[] args) {
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(this.currentLocale);
        formatter.applyPattern(this.Item.getString(key));

        return formatter.format(args);
    }

    String getBiome(String key, Object[] args) {
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(this.currentLocale);
        formatter.applyPattern(this.Biome.getString(key));

        return formatter.format(args);
    }

    String getAchievements(String key, Object[] args) {
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(this.currentLocale);
        formatter.applyPattern(this.Achievements.getString(key));

        return formatter.format(args);
    }

}
