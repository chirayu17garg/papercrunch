package com.example.deerg.papercrunch;

public class CardData {
    private String Level;
    private String level_name;
    private int img;
    private int id;
    private String prog;
    public CardData(String Level,String level_name,String prog,int img,int id){
        this.Level = Level;
        this.level_name = level_name;
        this.img = img;
        this.prog = prog;
        this.id = id;
    }
    public String getlevelnum(){
        return Level;
    }

    public String getlevelname(){
        return level_name;
    }
    public String geprog(){
        return prog;
    }
    public int getimg(){
        return img;
    }
    public int getid(){
        return id;
    }

}
