package com.example.deerg.papercrunch;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CodedBefore extends AppCompatActivity {

    Button yes,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coded_before);
        yes= (Button)findViewById(R.id.button4);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CodedBefore.this,Main2Activity.class);
                startActivity(i);
            }
        });

        no=(Button)findViewById(R.id.button5);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CodedBefore.this,WhatisCode.class);
                startActivity(i);
            }
        });
    }
}
