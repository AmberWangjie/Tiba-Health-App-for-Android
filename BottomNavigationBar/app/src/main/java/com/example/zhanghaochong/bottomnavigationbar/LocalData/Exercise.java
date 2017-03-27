package com.example.zhanghaochong.bottomnavigationbar.LocalData;

import org.litepal.crud.DataSupport;
/**
 * Created by tianyarong on 2017/3/27.
 */

public class Exercise extends DataSupport{
    private int id;
    private String time;
    private String name;
    private String description;
    private String code;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    private int clientId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
