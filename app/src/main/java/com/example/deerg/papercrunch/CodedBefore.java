package com.example.deerg.papercrunch;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CodedBefore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coded_before);
    }
    public void No(View view){
        Intent intent2= new Intent(this,WhatisCode.class);
        startActivity(intent2);
    }
}
