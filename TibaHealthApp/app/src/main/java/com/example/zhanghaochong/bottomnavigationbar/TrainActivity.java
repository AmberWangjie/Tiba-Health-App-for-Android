package com.example.zhanghaochong.bottomnavigationbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhanghaochong.bottomnavigationbar.Data.Exercise;
import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.example.zhanghaochong.bottomnavigationbar.R;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;

public class TrainActivity extends Activity {
    ArrayList<Task> mTasks;
    Button btnStart,btnStop;
    Bundle bundle;
    Integer id;
    ImageView trainImage;
    TextView textViewTime;
    int[] images = {R.drawable.a01, R.drawable.a02, R.drawable.a03, R.drawable.a04, R.drawable.a05, R.drawable.a06, R.drawable.a07, R.drawable.a08, R.drawable.a09, R.drawable.a10, R.drawable.a11, R.drawable.a12};

    public final static String MESSAGE_ID = "com.example.zhanghaochong.bottomnavigationbar.id";
    public final static String CONSTANT_TASK = "com.example.zhanghaochong.bottomnavigationbar.task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        bundle = getIntent().getExtras();
        id = intent.getIntExtra(MESSAGE_ID, 0);

        if(bundle != null){
            mTasks = bundle.getParcelableArrayList(CONSTANT_TASK);
        }
        String message = mTasks.get(id).getTime();
        String task_id = mTasks.get(id).getId();
        int taskId = Integer.parseInt(task_id);

        setContentView(R.layout.training_screen);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        trainImage = (ImageView) findViewById(R.id.trainImage);

        long time = Integer.parseInt(message);
        trainImage.setImageResource(images[taskId - 1]);
        FormatConverter(time);

        final CounterClass timer = new CounterClass(time,1000);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.zhanghaochong.bottomnavigationbar.RateActivity");
                intent.putExtras(bundle);
                timer.cancel();

                startActivity(intent);
            }
        });

        //save data to local database

        Connector.getDatabase();
        DataSupport.deleteAll(com.example.zhanghaochong.bottomnavigationbar.LocalData.Exercise.class);

        for(Task task:mTasks) {
            com.example.zhanghaochong.bottomnavigationbar.LocalData.Exercise exercise = new com.example.zhanghaochong.bottomnavigationbar.LocalData.Exercise();
            exercise.setName(task.getName());
            exercise.setDescription(task.getDescription());
            exercise.setTime(task.getTime());
            //exercise.setCode(task.getCode());
            exercise.save();
        }

    }

    public class CounterClass extends CountDownTimer {
        public CounterClass (long InMillisSeconds, long TimeGap) {
            super(InMillisSeconds,TimeGap);
        }
        @Override
        public  void onTick(long millisUniilFinished) {
            long millis = millisUniilFinished;
            FormatConverter(millis);
        }
        @Override
        public void onFinish() {
            textViewTime.setText("Done!");
            id += 1;
            if(id >= mTasks.size()){
                Intent intent = new Intent("com.example.zhanghaochong.bottomnavigationbar.RateActivity");
                intent.putExtras(bundle);

                startActivity(intent);
            }else {
                Intent intent = new Intent("com.example.zhanghaochong.bottomnavigationbar.DescriptionActivity");
                intent.putExtras(bundle);
                intent.putExtra(MESSAGE_ID, id);

                startActivity(intent);
            }
        }
    }

    public void FormatConverter(long time){
        String hms = String.format("%02d:%02d:%02d", java.util.concurrent.TimeUnit.MILLISECONDS.toHours(time), java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(time)- java.util.concurrent.TimeUnit.HOURS.toMinutes(java.util.concurrent.TimeUnit.MILLISECONDS.toHours(time)),
                java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(time) - java.util.concurrent.TimeUnit.MINUTES.toSeconds(java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(time)));
        textViewTime.setText(hms);
    }
}