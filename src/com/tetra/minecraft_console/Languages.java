package com.tetra.minecraft_console;

import java.util.Locale;
import java.util.ResourceBundle;

public class Languages {
    Locale currentLocale = Locale.getDefault();
    ResourceBundle Messages = ResourceBundle.getBundle("com/tetra/minecraft_console/Messages", currentLocale);



}
