package com.example.deerg.papercrunch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pg;
    Handler mHandler=new Handler();
    private int mprogressbar=0;
    SQLiteDatabase datavase;
    LevelDbHelper levelDbHelper;
    DataDbHelper dataDbHelper;
    public static int totalstars;
    public static String token;
    public static int avid;
    public static String fname;
    public static String lname;
    public static int pno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        avid = sp.getInt("id_avatar", 0);

        levelDbHelper=new LevelDbHelper(this);
        //levelDbHelper.onUpgrade(datavase, 1, 1);
        levelDbHelper.checktable(datavase);

        dataDbHelper=new DataDbHelper(this);
        dataDbHelper.checktable(datavase);
        pg=(ProgressBar)findViewById(R.id.progressBar);
        final Intent i = new Intent(this,login.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mprogressbar<100)
                {
                    mprogressbar++;
                    android.os.SystemClock.sleep(30);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            pg.setProgress(mprogressbar);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(i);
                    }
                });
                finish();
            }
        }).start();
    }

}