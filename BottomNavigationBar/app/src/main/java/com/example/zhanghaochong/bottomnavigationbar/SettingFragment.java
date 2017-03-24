package com.example.zhanghaochong.bottomnavigationbar;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by tianyarong on 2017/3/24.
 */

public class SettingFragment extends Fragment {

    Button btSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the fragment layout file
        View v = inflater.inflate(R.layout.setting, container, false);

        onClickButtonListener(v);


        return v;
    }

    public void onClickButtonListener(View v) {
        btSet = (Button) v.findViewById(R.id.btSet);
        btSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.zhanghaochong.bottomnavigationbar.BluetoothActivity");
                startActivity(intent);
            }
        });
    }
}
