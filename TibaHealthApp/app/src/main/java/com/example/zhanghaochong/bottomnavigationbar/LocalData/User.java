package com.example.zhanghaochong.bottomnavigationbar.LocalData;

import org.litepal.crud.DataSupport;
/**
 * Created by tianyarong on 2017/3/27.
 */

public class User extends DataSupport{
    private int id;
    private String firstname;
    private String lastname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}
