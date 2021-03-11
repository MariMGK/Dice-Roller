package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class playFrds extends AppCompatActivity {
    private ImageView dice;
    private TextView score1,score2,turn1,turn2;
    private Button player1;
    private Button player2;
    private Timer t;
    private Handler han =new Handler();
    Random rng=new Random();
    private int player1Count=0,player2count=0, max=0;
    boolean p1=true,p2=true, limtcheck=true,start_flag=true;
    Dialog dialog,limitDialog;
    MediaPlayer diceplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_frds);
        dice=(ImageView)findViewById(R.id.dice);
        player1=(Button)findViewById(R.id.bt1);
        player2=(Button)findViewById(R.id.bt2);
        score1=(TextView)findViewById(R.id.pl1score) ;
        score2=(TextView)findViewById(R.id.pl2score);
        turn1=(TextView)findViewById(R.id.turn1) ;
        turn2=(TextView)findViewById(R.id.turn2);

        player1.setVisibility(View.VISIBLE);
        player2.setVisibility(View.VISIBLE);

        limitDialog=new Dialog(this);

        limitDialog.setContentView(R.layout.get_limit);
        limitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView clslimt=limitDialog.findViewById(R.id.closelimt);
        EditText lmt=limitDialog.findViewById(R.id.editlimit);
        TextView txt=limitDialog.findViewById(R.id.textlimt);
        Button  submt=limitDialog.findViewById(R.id.btlimit);
        clslimt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limitDialog.dismiss();
            }
        });
        submt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limtcheck=false;
                String l=lmt.getText().toString();
                if(l.length()==0){
                    max=50;
                }
                else{
                max=Integer.parseInt(l);
                limitDialog.dismiss();}

            }
        });

        if(limtcheck==true) {
            max=50;
        }
        limitDialog.setCanceledOnTouchOutside(false);
        limitDialog.show();

        dialog=new Dialog(this);


        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p1){
                    player2.setEnabled(false);
                    turn2.setVisibility(View.INVISIBLE);
                    p2=false;
                    p1=false;
                    dice.setImageResource(R.drawable.dice3d160);
                    dice.animate().setDuration(1000);

                    dice.animate().rotationYBy(360);
                    diceplayer=MediaPlayer.create(playFrds.this,R.raw.rollingdice);
                    diceplayer.start();
                    Handler h=new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            diceplayer.release();
                            rollDice();
                            player1.setEnabled(false);
                            player2.setEnabled(true);
                        }
                    },800);

                }
            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p2){
                    player1.setEnabled(false);
                    dice.setImageResource(R.drawable.dice3d160);
                    turn1.setVisibility(View.INVISIBLE);
                    p2=false;
                    p1=false;

                    dice.animate().setDuration(1000);
                    dice.animate().rotationYBy(360);
                    diceplayer=MediaPlayer.create(playFrds.this,R.raw.rollingdice);
                    diceplayer.start();
                    Handler h=new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            diceplayer.release();
                            rollDice2();
                            player2.setEnabled(false);
                            player1.setEnabled(true);
                        }
                    },800);
                }
            }
        });

    }

    public void rollDice2(){
        int randomNumber=rng.nextInt(6)+1;


        switch(randomNumber){
            case 1:
                dice.setImageResource(R.drawable.one);
                break;
            case 2:
                dice.setImageResource(R.drawable.two);
                break;
            case 3:
                dice.setImageResource(R.drawable.three);
                break;
            case 4:
                dice.setImageResource(R.drawable.four);
                break;
            case 5:
                dice.setImageResource(R.drawable.five);
                break;
            case 6:
                dice.setImageResource(R.drawable.six);
                break;
        }
        player2count+=randomNumber;

        score2.setText("Player 2 : "+player2count);
        if(player2count==max){
            //dialoguebox("palyer 2");
            player1.setVisibility(View.INVISIBLE);
            player2.setVisibility(View.INVISIBLE);
            winDailog("Player 2 ");

        }
        if(player2count>max){
            player2count-=randomNumber;
        }
        score2.setText("Player 2 : "+player2count);
        turn2.setVisibility(View.INVISIBLE);
        turn1.setVisibility(View.VISIBLE);
        p1=true;


    }


    public void rollDice(){
        int randomNumber=rng.nextInt(6)+1;

        switch(randomNumber){
            case 1:
                dice.setImageResource(R.drawable.one);
                break;
            case 2:
                dice.setImageResource(R.drawable.two);
                break;
            case 3:
                dice.setImageResource(R.drawable.three);
                break;
            case 4:
                dice.setImageResource(R.drawable.four);
                break;
            case 5:
                dice.setImageResource(R.drawable.five);
                break;
            case 6:
                dice.setImageResource(R.drawable.six);
                break;
        }
        player1Count+=randomNumber;

        score1.setText("Player 1 : "+player1Count);
        if(player1Count==max){
          // dialoguebox("Player 1");
            winDailog("Player 1 ");

        }
        if(player1Count>max){
            player1Count-=randomNumber;
        }
        score1.setText("Player 1 : "+player1Count);
        turn2.setVisibility(View.VISIBLE);
        turn1.setVisibility(View.INVISIBLE);
        p2=true;


    }
    public void winDailog(String s){

        dialog.setContentView(R.layout.win_dailog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close=dialog.findViewById(R.id.close);
        ImageView winimg=dialog.findViewById(R.id.winlogo);
        Button home=dialog.findViewById(R.id.homebt);
        Button retry=dialog.findViewById(R.id.retry);
        TextView txt=dialog.findViewById(R.id.wintext);
        txt.setText(s+"WIN");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r=new Intent(playFrds.this,MainActivity.class);
                finish();
                startActivity(r);
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                finish();
                startActivity(intent);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

    }



    public void dialoguebox(String s){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(s+"WIN the Match \n Are you want to Quite?");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                       finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onBackPressed() {

        Intent main=new Intent(playFrds.this,MainActivity.class);
        finish();
        startActivity(main);

    }
}