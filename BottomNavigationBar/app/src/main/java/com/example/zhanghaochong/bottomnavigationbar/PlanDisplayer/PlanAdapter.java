package com.example.zhanghaochong.bottomnavigationbar.PlanDisplayer;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
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
 * Created by zhanghaochong on 17/3/8.
 */

public class PlanAdapter extends ArrayAdapter<Task> {

    Context c;
    ArrayList<Task> plans;
    int[] images;
    LayoutInflater inflater;

    public PlanAdapter(Context context, ArrayList<Task> plans, int[] images){
        super(context, R.layout.plan_model, plans);

        this.c = context;
        this.plans = plans;
        this.images = images;
    }

    public class PlanHolder
    {
        TextView text;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.plan_model, null);
        }

        PlanHolder holder = new PlanHolder();
        holder.text = (TextView) convertView.findViewById(R.id.exerciseName);
        holder.img = (ImageView) convertView.findViewById(R.id.planImage);

        holder.text.setText(plans.get(position).getName());
        holder.img.setImageResource(images[position]);

        return convertView;
    }
}
