package com.tetra.minecraft_console;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.tetra.minecraft_console.Main.lang;

public class Languages {
    Locale currentLocale = Locale.getDefault();
    ResourceBundle Messages = ResourceBundle.getBundle("com/tetra/minecraft_console/langs/Messages", currentLocale);

    String getMessage(String key, Object[] args) {
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(lang.currentLocale);
        formatter.applyPattern(lang.Messages.getString(key));
        String message = formatter.format(args);

        return message;
    }


}
