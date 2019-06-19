package com.example.deerg.papercrunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {

    Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent i=new Intent(getApplicationContext(),CodedBefore.class);
        skip=(Button)findViewById(R.id.button3);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(i);
            }
        });
    }
}
