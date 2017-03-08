package com.example.zhanghaochong.tibaapplication.Data;

/**
 * Created by zhanghaochong on 17/3/7.
 */

public class Task {
    private String name;
    private String description;
    private String time;
    private String id;

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}