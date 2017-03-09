package com.example.zhanghaochong.bottomnavigationbar.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zhanghaochong.bottomnavigationbar.R;

/**
 * Created by zhanghaochong on 17/3/7.
 */

public class MyHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;

    public MyHolder(View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
    }
}