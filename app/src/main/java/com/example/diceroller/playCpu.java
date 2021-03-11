package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.Edits;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class playCpu extends AppCompatActivity {
    private EditText limit;
    private ImageView dice;
    private Button roll;
    private TextView yrScore,CpuScore,turn;
    private int max,userCount=0,cpuCount=0;
    private Random rng=new Random();
    boolean playerCheck=true,limtcheck=false;
    Dialog dialog,limitDialog;
    MediaPlayer diceplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_cpu);

        limit=(EditText)findViewById(R.id.editTextNumber);
        dice=(ImageView) findViewById(R.id.dice);
        roll=(Button) findViewById(R.id.roll);
        yrScore=(TextView) findViewById(R.id.youscore);
        CpuScore=(TextView) findViewById(R.id.cpuscore);
        turn=(TextView) findViewById(R.id.turn);
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
        limitDialog.show();


        dialog=new Dialog(this);

        turn.setText("Your Turn!");

       String temp=limit.getText().toString();
        if (temp.length()==0)
        {
            max=50;
        }
        else{
            max=Integer.parseInt(temp);
        }
       // max=Integer.parseInt(limit.getText().toString());
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerCheck){

                    Handler time1=new Handler();
                    time1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            roll.setEnabled(false);
                            dice.setImageResource(R.drawable.dice3d160);
                            dice.animate().setDuration(1080);
                            dice.animate().rotationYBy(360);
                            diceplayer= MediaPlayer.create(playCpu.this,R.raw.rollingdice);
                            diceplayer.start();
                        }
                    },100);



                    Handler h=new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            diceplayer.release();
                            rollDice();

                        }
                    },1000);


                }
            }
        });



    }
    public void rollDice(){
        int randomNumber=rng.nextInt(6)+1;
        playerCheck=false;

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

        userCount+=randomNumber;
        yrScore.setText("Your Score : "+userCount);
        if(userCount==max){
            turn.setText("You WIN the Match");
            roll.setVisibility(View.INVISIBLE);
            //dialoguebox("You ");
            winDailog();

        }
        if(userCount>max){
            userCount-=randomNumber;
        }
        yrScore.setText("Your Score : "+userCount);
        turn.setText("CPU Turn!");

        Handler time2=new Handler();
        time2.postDelayed(new Runnable() {
            @Override
            public void run() {
                dice.setImageResource(R.drawable.dice3d160);
                dice.animate().setDuration(1080);
                dice.animate().rotationYBy(360);
                diceplayer= MediaPlayer.create(playCpu.this,R.raw.rollingdice);
                diceplayer.start();
            }
        },1700);


        Handler h2=new Handler();
        h2.postDelayed(new Runnable() {
            @Override
            public void run() {
                diceplayer.release();
                cpuRoll();
            }
        },2700);



    }

    private void cpuRoll() {
        int randNumber=rng.nextInt(6)+1;

        switch(randNumber){
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
        cpuCount+=randNumber;
        CpuScore.setText("Your Score : "+cpuCount);
        roll.setEnabled(true);

        if(cpuCount==max){
            turn.setText("CPU WIN the Match");
            roll.setVisibility(View.INVISIBLE);
           //
            loseDailog();

        }
        if(cpuCount>max){
            cpuCount-=randNumber;
            CpuScore.setText("Your Score : "+cpuCount);
        }
        turn.setText("Your Turn!");
        playerCheck=true;




    }


    public void winDailog(){

        dialog.setContentView(R.layout.win_dailog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close=dialog.findViewById(R.id.close);
        ImageView winimg=dialog.findViewById(R.id.winlogo);
        Button home=dialog.findViewById(R.id.homebt);
        Button retry=dialog.findViewById(R.id.retry);
        TextView txt=dialog.findViewById(R.id.wintext);
        txt.setText(" YOU WIN ");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r=new Intent(playCpu.this,MainActivity.class);
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


    public void loseDailog(){

        dialog.setContentView(R.layout.lose_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView close=dialog.findViewById(R.id.close);
        ImageView losimg=dialog.findViewById(R.id.winlogo);
        Button home=dialog.findViewById(R.id.homebt);
        Button retry=dialog.findViewById(R.id.retry);
        TextView txt=dialog.findViewById(R.id.wintext);
        txt.setText(" YOU LOSE ");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r=new Intent(playCpu.this,MainActivity.class);
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





    private void dialoguebox(String s) {
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
        Intent main=new Intent(playCpu.this,MainActivity.class);
        finish();
        startActivity(main);
    }
}