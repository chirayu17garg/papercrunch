package com.example.deerg.papercrunch;


public class Post {

    private String name;
    private int currentLevel;
    private int totalstars;
    private int levelprogress[];

    public String getName() {
        return name;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getTotalstars() {
        return totalstars;
    }

    public int[] getLevelprogress() {
        return levelprogress;
    }
}
