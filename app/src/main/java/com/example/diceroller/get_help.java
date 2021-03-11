package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class get_help extends AppCompatActivity {

    private TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help);

        t1=(TextView)findViewById(R.id.mail);
        t2=(TextView)findViewById(R.id.thank);
    }

    @Override
    public void onBackPressed() {
        Intent main=new Intent(get_help.this,MainActivity.class);
        finish();
        startActivity(main);

    }

}