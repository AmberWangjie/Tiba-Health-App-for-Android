package com.example.zhanghaochong.tibaapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhanghaochong.tibaapplication.R;

public class TrainActivity extends Activity {
    Button btnStart,btnStop;
    TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_screen);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        textViewTime.setText("00:30:00");

        final CounterClass timer = new CounterClass(60000,1000);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
            }
        });
    }



    public class CounterClass extends CountDownTimer {
        public CounterClass (long InMillisSeconds, long TimeGap) {
            super(InMillisSeconds,TimeGap);
        }
        @Override
        public  void onTick(long millisUniilFinished) {
            long millis = millisUniilFinished;
            String hms = String.format("%02d:%02d:%02d", java.util.concurrent.TimeUnit.MILLISECONDS.toHours(millis), java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(millis)- java.util.concurrent.TimeUnit.HOURS.toMinutes(java.util.concurrent.TimeUnit.MILLISECONDS.toHours(millis)),
                    java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(millis) - java.util.concurrent.TimeUnit.MINUTES.toSeconds(java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(millis)));
            textViewTime.setText(hms);
        }
        @Override
        public void onFinish() {
            textViewTime.setText("Done!");
        }
    }
}
