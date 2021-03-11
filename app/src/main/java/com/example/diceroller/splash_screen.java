package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class splash_screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Timer t =new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent splash=new Intent(splash_screen.this,MainActivity.class);
                finish();
                startActivity(splash);


            }
        },1700);
    }
}