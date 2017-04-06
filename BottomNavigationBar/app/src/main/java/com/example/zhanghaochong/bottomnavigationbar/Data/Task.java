package com.example.zhanghaochong.bottomnavigationbar.Data;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firebase.client.utilities.PushIdGenerator;


/**
 * Created by zhanghaochong on 17/3/6.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task implements Parcelable{

    private String description;
    private String abstraction;
    private String name;
    private String time;
    private String id;

    public Task(){

    }

    public Task(String name){
        this.name = name;
   }

    public Task(String name, String description, String time, String id, String abstraction){
        this.name = name;
        this.description = description;
        this.time = time;
        this.id = id;
        this.abstraction = abstraction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbstraction() {
        return abstraction;
    }

    public void setAbstraction(String abstraction) {
        this.abstraction = abstraction;
    }

    //Parcelling part
    public Task(Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        this.name = data[0];
        this.description = data[1];
        this.time = data[2];
        this.id = data[3];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name, this.description, this.time, this.id});
    }
    public static  final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Task createFromParcel(Parcel in){
            return new Task(in);
        }

        public Task[] newArray(int size){
            return new Task[size];
        }
    };
}
