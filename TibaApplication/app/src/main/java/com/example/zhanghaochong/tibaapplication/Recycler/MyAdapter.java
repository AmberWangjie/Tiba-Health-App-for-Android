package com.example.zhanghaochong.tibaapplication.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhanghaochong.tibaapplication.Data.Task;
import com.example.zhanghaochong.tibaapplication.R;

import java.util.ArrayList;

/**
 * Created by Oclemmy on 4/11/2016 for ProgrammingWizards Channel.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Task> tasks;

    public MyAdapter(Context c, ArrayList<Task> tasks) {
        this.c = c;
        this.tasks = tasks;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String name=tasks.get(position).getName();
        holder.nameTxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
