package com.example.zhanghaochong.bottomnavigationbar.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by AmberWang on 3/30/17.
 */
 public class User {
        public static String username;
        public static String phone;
        public static String email;
        public static String pt;
        public static String birth;
        public static String password;

        public User(){}


        public String getUsername() {
            return username;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getBirth() {return birth;}

        public String getPt(){return pt;}

        public String getPassword() {return password;}

    }

