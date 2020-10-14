package com.tetra.minecraft_console;

import java.io.Serializable;

public class Achievements implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5016779669983916613L;
    public boolean[] isAchieved;
    public String[] achievementsList;
    public String[] achievementsDisplay = {};

}