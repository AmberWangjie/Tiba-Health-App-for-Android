package com.example.zhanghaochong.bottomnavigationbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import com.example.zhanghaochong.bottomnavigationbar.Recycler.*;
import com.firebase.client.authentication.Constants;

import java.util.ArrayList;

/**
 * Created by zhanghaochong on 17/3/5.
 */

public class WorkoutFragment extends Fragment {

    private Firebase mRef;
    private ArrayList<Task> mTasks = new ArrayList<>();
    private Button startBtn;
    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    public final static String MESSAGE_ID = "com.example.zhanghaochong.bottomnavigationbar.id";
    public final static String CONSTANT_TASK = "com.example.zhanghaochong.bottomnavigationbar.task";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the fragment layout file
        View v = inflater.inflate(R.layout.workout, container, false);

        //mListView = (ListView) v.findViewById(android.R.id.list);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerID);
        //adapter = new MyAdapter(getActivity(),mTasks);
        //mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Firebase.setAndroidContext(getActivity());
        mRef = new Firebase("https://tibaapplication.firebaseio.com/");

        retrieveData();

        onClickButtonListener(v);

        return v;
    }

        /*final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,mTasks);
        mListView.setAdapter(arrayAdapter);*/

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

        if(mTasks.size() > 0){
            //ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mTasks);
            adapter = new MyAdapter(getActivity(),mTasks);
            mRecyclerView.setAdapter(adapter);
        }else{
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickButtonListener(View v) {
        startBtn = (Button) v.findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.zhanghaochong.bottomnavigationbar.DescriptionActivity");
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(CONSTANT_TASK, mTasks);
                Integer id = 0;

                intent.putExtras(bundle);
                intent.putExtra(MESSAGE_ID, id);

                startActivity(intent);
            }
        });
    }

}
