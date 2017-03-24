package com.example.zhanghaochong.bottomnavigationbar;

import android.app.Fragment;
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

    Button btBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the fragment layout file
        View v = inflater.inflate(R.layout.setting, container, false);

        onClickButtonListener(v);

        return v;
    }

    public void onClickButtonListener(View v) {

    }
}
