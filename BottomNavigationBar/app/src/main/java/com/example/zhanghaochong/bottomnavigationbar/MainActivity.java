package com.example.zhanghaochong.bottomnavigationbar;

import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.zhanghaochong.bottomnavigationbar.Data.Exercise;
import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements WorkoutFragment.OnExercisePass{

    private BottomBar mBottomBar;
    private ArrayList<Exercise> mExercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        mBottomBar.mapColorForTab(0, "#F44336");
        mBottomBar.mapColorForTab(1, "#E91E63");
        mBottomBar.mapColorForTab(2, "#FF5722");
        mBottomBar.mapColorForTab(3, "#2196F3");

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
