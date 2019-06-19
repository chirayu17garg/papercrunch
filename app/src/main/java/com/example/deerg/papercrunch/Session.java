package com.example.deerg.papercrunch;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Collections;
import java.util.List;

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();
    }
    public void progress(List prog){
        prefs.edit().putString("progress", String.valueOf(prog)).commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }
    public List getprog(){
        List prog = (List) prefs.getStringSet("progress", Collections.singleton(""));
        return prog;
    }
}
