package com.example.zhanghaochong.bottomnavigationbar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.example.zhanghaochong.bottomnavigationbar.LocalData.Exercise;
import com.example.zhanghaochong.bottomnavigationbar.LocalData.User;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class LocalDBActivity extends AppCompatActivity {


    private Firebase mRef;
    private ArrayList<Task> mTasks = new ArrayList<>();
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_db);
        mContext = getApplicationContext();

        mRef = new Firebase("https://tibaapplication.firebaseio.com/");

        retrieveData();

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
                for(Task task:mTasks) {
                    Exercise exercise = new Exercise();
                    exercise.setName(task.getName());
                    exercise.setDescription(task.getDescription());
                    exercise.setTime(task.getTime());
                    //exercise.setCode(task.getCode());
                    exercise.save();
                }
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
                DataSupport.deleteAll(Exercise.class);
            }
        });


        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Exercise> exercises = DataSupport.findAll(Exercise.class);
                for (Exercise exercise: exercises) {
                    Log.d("LocalDBActivity", "task name is " + exercise.getName());
                    Log.d("LocalDBActivity", "task Description is " + exercise.getDescription());
                    Log.d("LocalDBActivity", "task time is " + exercise.getTime());
                    Log.d("LocalDBActivity", "task code is " + exercise.getCode());

                    Toast.makeText(mContext,"task name is " + exercise.getName()+"\n"+"task time is " + exercise.getTime(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void retrieveData(){
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getUpdates(DataSnapshot ds) {
        mTasks.clear();

        for(DataSnapshot data : ds.getChildren()) {
            Task t = new Task();
            t.setName(data.getValue(Task.class).getName());
            t.setDescription(data.getValue(Task.class).getDescription());
            t.setTime(data.getValue(Task.class).getTime());
            t.setId(data.getValue(Task.class).getId());

            mTasks.add(t);
        }
    }


}
