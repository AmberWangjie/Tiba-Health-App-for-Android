package com.example.zhanghaochong.bottomnavigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class rateActivity extends AppCompatActivity {

    Bundle bundle;
    Button subMit;
    ImageButton rateButton1, rateButton2, rateButton3, rateButton4, rateButton5, rateButton6, rateButton7, rateButton8, rateButton9, rateButton10;

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        Intent intent = getIntent();
        bundle = getIntent().getExtras();

        subMit = (Button) findViewById(R.id.rate_finish);
        subMit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent Homeintent = new Intent(rateActivity.this, MainActivity.class);
                Homeintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Homeintent);
            }
        });

        rateExercise();
    }


    void rateExercise(){
        rateButton1 = (ImageButton)findViewById(R.id.imageButton2);
        rateButton2 = (ImageButton)findViewById(R.id.imageButton3);
        rateButton3 = (ImageButton)findViewById(R.id.imageButton4);
        rateButton4 = (ImageButton)findViewById(R.id.imageButton5);
        rateButton5 = (ImageButton)findViewById(R.id.imageButton10);
        rateButton6 = (ImageButton)findViewById(R.id.imageButton6);
        rateButton7 = (ImageButton)findViewById(R.id.imageButton7);
        rateButton8 = (ImageButton)findViewById(R.id.imageButton8);
        rateButton9 = (ImageButton)findViewById(R.id.imageButton9);
        rateButton10 = (ImageButton)findViewById(R.id.imageButton11);

        rateButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rateButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rateButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rateButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rateButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rateButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rateButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rateButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rateButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
