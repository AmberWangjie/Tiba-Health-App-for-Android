package com.example.tianyarong.tibaapplication;

import android.app.Application;
import com.firebase.client.Firebase;

/**
 * Created by tianyarong on 2017/2/23.
 */

public class TibaApp extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
