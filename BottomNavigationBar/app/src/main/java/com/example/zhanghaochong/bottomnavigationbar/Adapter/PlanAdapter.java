package com.example.zhanghaochong.bottomnavigationbar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhanghaochong.bottomnavigationbar.Data.Exercise;
import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.example.zhanghaochong.bottomnavigationbar.R;

import java.util.ArrayList;

/**
 * Created by zhanghaochong on 17/3/8.
 */

public class PlanAdapter extends ArrayAdapter<Exercise> {

    Context c;
    ArrayList<Exercise> plans;
    LayoutInflater inflater;

    public PlanAdapter(Context context, ArrayList<Exercise> plans){
        super(context, R.layout.plan_model, plans);

        this.c = context;
        this.plans = plans;
    }

    public class PlanHolder
    {
        TextView text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.plan_model, null);
        }

        PlanHolder holder = new PlanHolder();
        holder.text = (TextView) convertView.findViewById(R.id.exerciseName);

        holder.text.setText(plans.get(position).getExerciseName());

        return convertView;
    }
}
