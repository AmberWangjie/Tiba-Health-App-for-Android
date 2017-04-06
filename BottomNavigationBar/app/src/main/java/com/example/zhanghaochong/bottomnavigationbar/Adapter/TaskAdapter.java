package com.example.zhanghaochong.bottomnavigationbar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.example.zhanghaochong.bottomnavigationbar.R;

import java.util.ArrayList;

/**
 * Created by zhanghaochong on 17/3/29.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    Context c;
    ArrayList<Task> Tasks;
    LayoutInflater inflater;

    public TaskAdapter(Context context, ArrayList<Task> Tasks){
        super(context, R.layout.task_model, Tasks);

        this.c = context;
        this.Tasks = Tasks;
    }

    public class TaskHolder
    {
        TextView seqNum;
        TextView taskName;
        TextView taskTime;
        TextView taskAbstract;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.task_model, null);
        }

        TaskHolder holder = new TaskHolder();
        String sequence = position + 1 + ".";

        holder.seqNum = (TextView) convertView.findViewById(R.id.seqNum);
        holder.seqNum.setText(sequence);
        holder.taskName = (TextView) convertView.findViewById(R.id.taskName);
        holder.taskName.setText(Tasks.get(position).getName());
        long time = Integer.parseInt(Tasks.get(position).getTime());
        holder.taskTime = (TextView) convertView.findViewById(R.id.taskTime);
        holder.taskTime.setText(FormatConverter(time) + "sec");
        holder.taskAbstract = (TextView) convertView.findViewById(R.id.taskAbstract);
        holder.taskAbstract.setText(Tasks.get(position).getAbstraction());

        return convertView;
    }

    public String FormatConverter(long time){
        String taskTime = String.format("%02d", java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(time));
        return taskTime;
    }
}
