package com.example.zhanghaochong.bottomnavigationbar;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.example.zhanghaochong.bottomnavigationbar.Data.Exercise;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;

/**
 * Created by zhanghaochong on 17/4/6.
 */

public class MainUiActivity extends AppCompatActivity implements WorkoutFragment.OnExercisePass {

    private BottomBar mBottomBar;
    private ArrayList<Exercise> mExercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int id) {
                if(id == R.id.BottombaritemOne){
                    WorkoutFragment f = new WorkoutFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                else if(id == R.id.BottombaritemTwo){
                    CareplanFragment f = new CareplanFragment();
                    f.mExercise = mExercises;
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                else if(id == R.id.BottombaritemThree) {
                    EmailFragment f = new EmailFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                else if(id == R.id.BottombaritemFour) {
                    AccountFragment f = new AccountFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                else if(id == R.id.BottombaritemFive) {
                    SettingFragment f = new SettingFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }

        });
        mBottomBar.mapColorForTab(0, "#0288D1");
        mBottomBar.mapColorForTab(1, "#039BE5");
        mBottomBar.mapColorForTab(2, "#03A9F4");
        mBottomBar.mapColorForTab(3, "#29B6F6");
        mBottomBar.mapColorForTab(4, "#4FC3F7");

        BottomBarBadge unread;
        unread = mBottomBar.makeBadgeForTabAt(3, "#FF0000", 13);
        unread.show();
    }

    @Override
    public void setExercise(Exercise e){
        mExercises.clear();
        mExercises.add(e);
    }
}
