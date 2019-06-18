package com.example.deerg.papercrunch;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pg;
    Handler mHandler=new Handler();
    private int mprogressbar=0;
    SQLiteDatabase datavase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
