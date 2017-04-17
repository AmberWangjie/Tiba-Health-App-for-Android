package com.example.zhanghaochong.bottomnavigationbar.Recycler;

import android.bluetooth.BluetoothDevice;
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
import java.util.List;

/**
 * Created by tianyarong on 2017/4/6.
 */

public class MyAdapterBTListView extends ArrayAdapter<Task> {
    private LayoutInflater mLayoutInflater;
    private ArrayList<Task> mtasks;
    private int  mViewResourceId;

    public MyAdapterBTListView(Context context, int tvResourceId, ArrayList<Task> tasks){
        super(context, tvResourceId,tasks);
        this.mtasks = tasks;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = tvResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayoutInflater.inflate(mViewResourceId, null);

        Task task = mtasks.get(position);

        if (task != null) {
//            TextView deviceName = (TextView) convertView.findViewById(R.id.tvDeviceName);
//            TextView deviceAdress = (TextView) convertView.findViewById(R.id.tvDeviceAddress);
            TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);

            if (nameTxt != null) {
                String name=mtasks.get(position).getName();
                nameTxt.setText(name);
            }

        }

        return convertView;
    }

}
