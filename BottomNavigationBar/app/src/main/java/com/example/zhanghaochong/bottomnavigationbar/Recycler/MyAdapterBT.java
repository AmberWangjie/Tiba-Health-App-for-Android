package com.example.zhanghaochong.bottomnavigationbar.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.example.zhanghaochong.bottomnavigationbar.R;

import java.nio.charset.Charset;
import java.util.ArrayList;
import com.example.zhanghaochong.bottomnavigationbar.BluetoothConnectionService;

/**
 * Created by tianyarong on 2017/3/31.
 */

public class MyAdapterBT extends RecyclerView.Adapter<MyAdapterBT.ViewHolder>{

    Context c;
    ArrayList<Task> tasks;
    BluetoothConnectionService mBluetoothConnection;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt;
        View TaskView;

        public ViewHolder(View view) {
            super(view);
            TaskView = view;
            nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        }
    }

    public MyAdapterBT(Context c, ArrayList<Task> tasks) {
        this.c = c;
        this.tasks = tasks;
    }

    @Override
    public MyAdapterBT.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        final ViewHolder holder = new ViewHolder(view);
//        holder.TaskView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = holder.getAdapterPosition();
//                Task task = tasks.get(position);
//                Toast.makeText(view.getContext(),"you clicked view"+task.getName(),Toast.LENGTH_SHORT).show();
//                byte[] bytes = tasks.get(position).getName().toString().getBytes(Charset.defaultCharset());
//                mBluetoothConnection.write(bytes);
//            }
//        });
       // MyAdapterBT.ViewHolder holder=new MyAdapterBT.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapterBT.ViewHolder holder, int position) {
        String name=tasks.get(position).getName();
        holder.nameTxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
