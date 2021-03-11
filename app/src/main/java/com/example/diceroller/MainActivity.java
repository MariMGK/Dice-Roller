package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ImageView frntdice;
    CardView c1,c2,c3,c4;
    private Timer t;
    private Handler han =new Handler();
    MediaPlayer mplayer;
    private boolean start_flag=true;
    private Switch sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frntdice=(ImageView)findViewById(R.id.frontdice);
        c1=(CardView)findViewById(R.id.c1);
        c2=(CardView)findViewById(R.id.c2);
        c3=(CardView)findViewById(R.id.c3);
        c4=(CardView)findViewById(R.id.c4);
        sound=(Switch)findViewById(R.id.switch1);

        mplayer=MediaPlayer.create(this,R.raw.bgmusic2);
        mplayer.setLooping(true);
        mplayer.start();

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    if(mplayer==null){
                        mplayer=MediaPlayer.create(MainActivity.this,R.raw.bgmusic2);}
                    mplayer.start();
                }
                else{
                    mplayer.pause();
                }
            }
        });

//        t=new Timer();
//        t.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if(start_flag){
//                    han.post(new Runnable() {
//                        @Override
//                        public void run() {
//                          playSong();
//
//                        }
//                    });
//                }
//            }
//        },0,3000);


        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(MainActivity.this, playFrds.class);
                mplayer.setLooping(false);
                mplayer.pause();
                mplayer.release();
                mplayer=null;
                finish();
                startActivity(l);

            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,playCpu.class);
                mplayer.setLooping(false);
                mplayer.pause();
                mplayer.release();
                mplayer=null;
                finish();
                startActivity(i);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,get_help.class);
                mplayer.setLooping(false);
                mplayer.pause();
                mplayer.release();
                mplayer=null;
                finish();
                startActivity(i);

            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mplayer.setLooping(false);
                mplayer.pause();
                mplayer.release();
                mplayer=null;
                finish();
                System.exit(0);

            }
        });
    }

    public void playSong(){
        if(mplayer==null){
            mplayer=MediaPlayer.create(this,R.raw.bgmusic2);}
        mplayer.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}