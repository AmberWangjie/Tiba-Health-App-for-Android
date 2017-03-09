package com.example.zhanghaochong.bottomnavigationbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.example.zhanghaochong.bottomnavigationbar.PlanDisplayer.PlanAdapter;
import com.example.zhanghaochong.bottomnavigationbar.Recycler.MyAdapter;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by zhanghaochong on 17/3/5.
 */

public class CareplanFragment extends ListFragment{

    private Firebase mRef;
    private ArrayList<Task> mTasks = new ArrayList<>();
    private ListView myView;
    private int[] images = {R.mipmap.a08, R.mipmap.a04, R.mipmap.a06};
    private PlanAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.careplan, container, false);

        myView = (ListView) v.findViewById(android.R.id.list);

        Firebase.setAndroidContext(getActivity());
        mRef = new Firebase("https://tibaapplication.firebaseio.com/");

        retrieveData();

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        TextView editText = (TextView)v.findViewById(R.id.textView);
        String myPlan = String.format("%s\n%s", mTasks.get(position).getName(), mTasks.get(position).getDescription());

        editText.setText(myPlan);
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

        if(mTasks.size() > 0){
            adapter = new PlanAdapter(getActivity(),mTasks, images);
            myView.setAdapter(adapter);
        }else{
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        }
    }
}
