package com.example.zhanghaochong.bottomnavigationbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.zhanghaochong.bottomnavigationbar.LocalData.Exercise;
import com.example.zhanghaochong.bottomnavigationbar.LocalData.User;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LocalDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_db);

        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {

                                              @Override
                                              public void onClick(View v) {
                                                  Connector.getDatabase();
                                              }
                                          }
        );

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exercise exercise = new Exercise();
                exercise.setName("plank");
                exercise.setDescription("plank,plank");
                exercise.setTime("30");
                exercise.setCode("1-9838677328-#^$%@45");
                exercise.save();
            }
        });

        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exercise exercise = new Exercise();
                exercise.setName("crunch");
                exercise.setDescription("ccccc");
                exercise.setTime("50");
                exercise.setCode("1-9838677328-#^$%@45");
                exercise.save();

            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Exercise.class, "time < ?", "5");
            }
        });


        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Exercise> exercises = DataSupport.findAll(Exercise.class);
                for (Exercise exercise: exercises) {
                    Log.d("LocalDBActivity", "book name is " + exercise.getName());
                    Log.d("LocalDBActivity", "book author is " + exercise.getDescription());
                    Log.d("LocalDBActivity", "book pages is " + exercise.getDescription());
                    Log.d("LocalDBActivity", "book price is " + exercise.getCode());

                }
            }
        });
    }


}
