package com.example.deerg.papercrunch;

public class Register {

    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String avatarId;
    private boolean google;

    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getFirst_name(){
        return first_name;
    }
    public String getLast_name(){
        return last_name;
    }
    public String getAvatarId(){
        return avatarId;
    }
    public boolean isGoogle(){
        return google;
    }
}
