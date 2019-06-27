package com.example.deerg.papercrunch;


import java.util.List;

public class Post {

    private String token;
    private int currentLevel;
    private int totalStars;
    private int ID;
    private List<Integer> levelprogress;
    private String fname;
    private String lname;
    private int pno;

    public int getPno() {
        return pno;
    }

    public Post(){

    }

    public String getToken() {
        return token;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getTotalstars() {
        return totalStars;
    }

    public int getID() {
        return ID;
    }

    public List<Integer> getLevelprogress() {
        return levelprogress;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
}
