package com.example.zhanghaochong.bottomnavigationbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhanghaochong.bottomnavigationbar.Data.Exercise;
import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.example.zhanghaochong.bottomnavigationbar.Adapter.PlanAdapter;
import com.example.zhanghaochong.bottomnavigationbar.Data.User;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;

/**
 * Created by zhanghaochong on 17/3/5.
 */

public class CareplanFragment extends ListFragment{

    private ArrayList<Task> mTasks = new ArrayList<>();
    ArrayList<Exercise> mExercise = new ArrayList<>();
    private ListView myView;
    private TextView mUsername;
    private PlanAdapter adapter;
    private DonutProgress dp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.careplan, container, false);
        myView = (ListView) v.findViewById(android.R.id.list);

        dp = (DonutProgress)v.findViewById(R.id.donutProgress);
        dp.setProgress(20);
        mUsername = (TextView) v.findViewById(R.id.userName);
        mUsername.setText(User.username + "!");

        getUpdates();

        return v;
    }

    private void getUpdates() {
        mTasks.clear();
        mTasks = mExercise.get(0).getmTask();

        if(mTasks.size() > 0){
            adapter = new PlanAdapter(getActivity(),mExercise);
            myView.setAdapter(adapter);
        }else{
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        }
    }
}
