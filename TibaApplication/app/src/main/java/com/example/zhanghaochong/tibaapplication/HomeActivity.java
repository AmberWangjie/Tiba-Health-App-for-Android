package com.example.zhanghaochong.tibaapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhanghaochong.tibaapplication.Data.Task;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import com.example.zhanghaochong.tibaapplication.Recycler.*;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

//    private Firebase mRef;
//    private ArrayList<String> mTasks = new ArrayList<>();
//    private ListView mListView;
//    private Button startBtn;

    final static String DB_URL="https://tibaapplication.firebaseio.com/";
    EditText nameEditText,descEditText,timeEditText,idEditText;
    Button saveBtn,startBtn;
    ArrayList<Task> tasks=new ArrayList<>();
    RecyclerView rv;
    MyAdapter adapter;
    Firebase fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mRef = new Firebase("https://tibaapplication.firebaseio.com/Tasks");
//        mListView = (ListView) findViewById(R.id.taskList);
//
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mTasks);
//        mListView.setAdapter(arrayAdapter);
//
//        mRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String value = dataSnapshot.getValue(String.class);
//                mTasks.add(value);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        onClickButtonListener();
//    }
//
//    public void onClickButtonListener() {
//        startBtn = (Button) findViewById(R.id.startMain);
//        startBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent("com.example.tianyarong.tibaapplication.TrainActivity");
//                startActivity(intent);
//            }
//        });
//    }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        rv= (RecyclerView) findViewById(R.id.mRecyclerID);
        rv.setLayoutManager(new LinearLayoutManager(this));


        //INITIALIZE
        Firebase.setAndroidContext(this);

        //INSANTIATE
        fire=new Firebase(DB_URL);

        //REFRESH
        this.refreshData();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });

        onClickButtonListener();
    }

    private void displayDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save Online");
        d.setContentView(R.layout.dialoglayout);

        nameEditText= (EditText) d.findViewById(R.id.nameEditText);
        descEditText= (EditText) d.findViewById(R.id.descEditText);
        timeEditText = (EditText) d.findViewById(R.id.timeEditText);
        idEditText = (EditText) d.findViewById(R.id.idEditText);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOnline(nameEditText.getText().toString(),descEditText.getText().toString(),timeEditText.getText().toString(),idEditText.getText().toString());

                nameEditText.setText("");
                descEditText.setText("");
                timeEditText.setText("");
                idEditText.setText("");
            }
        });

        //SHOW
        d.show();

    }

    //SAVE DATA
    private void saveOnline(String name,String desc,String time,String id)
    {
        Task task=new Task();
        task.setName(name);
        task.setDescription(desc);
        task.setTime(time);
        task.setId(id);

        fire.child("Task").push().setValue(task);
    }

    //RETRIEVE
    private void refreshData()
    {
        fire.addChildEventListener(new ChildEventListener() {
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

    private void getUpdates(DataSnapshot dataSnapshot)
    {
        tasks.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Task task=new Task();
            task.setName(ds.getValue(Task.class).getName());
            task.setDescription(ds.getValue(Task.class).getDescription());
            task.setTime(ds.getValue(Task.class).getTime());
            task.setId(ds.getValue(Task.class).getId());

            tasks.add(task);
        }

        if(tasks.size()>0)
        {
            adapter=new MyAdapter(HomeActivity.this,tasks);
            rv.setAdapter(adapter);
        }else {
            Toast.makeText(HomeActivity.this,"No data",Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickButtonListener() {
        startBtn = (Button) findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.zhanghaochong.tibaapplication.TrainActivity");
                startActivity(intent);
            }
        });
    }

}

