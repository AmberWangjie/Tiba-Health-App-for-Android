package com.example.zhanghaochong.bottomnavigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhanghaochong.bottomnavigationbar.Data.Task;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity {

    Button btnGo;
    TextView textviewExe, textviewDes;
    ArrayList<Task> mTasks;
    Bundle bundle;
    Integer id;
    public final static String MESSAGE_ID = "com.example.zhanghaochong.bottomnavigationbar.id";
    public final static String CONSTANT_TASK = "com.example.zhanghaochong.bottomnavigationbar.task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        bundle = getIntent().getExtras();

        if(bundle != null){
            mTasks = bundle.getParcelableArrayList(CONSTANT_TASK);
        }

        setContentView(R.layout.description_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        id = intent.getIntExtra(MESSAGE_ID, 0);

        textviewExe = (TextView) findViewById(R.id.textViewExe);
        textviewDes = (TextView) findViewById(R.id.textViewDes);

        textviewExe.setText(mTasks.get(id).getName());
        textviewDes.setText(mTasks.get(id).getDescription());

        onClickButtonListener();
    }

    public void onClickButtonListener(){
        btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.zhanghaochong.bottomnavigationbar.TrainActivity");

                intent.putExtras(bundle);
                intent.putExtra(MESSAGE_ID, id);

                startActivity(intent);
            }
        });
    }

}
