package com.example.zhanghaochong.bottomnavigationbar.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by AmberWang on 3/30/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
 public class User implements Parcelable {
        private String username;
        private String phone;
        private String email;
        private String pt;
        private String birth;
        private String password;
        //private String id;

        public User(){}

        public User(String username){
            this.username = username;
        }

        public User(String username, String phone, String email, String birth, String pt, String password){
            this.username = username;
            this.phone = phone;
            this.email = email;
            this.birth = birth;
            this.pt = pt;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBirth() {return birth;}

        public void setBirth(String birth) {this.birth = birth;}

        public String getPt(){return pt;}

        public void setPt(String pt){this.pt = pt;}

        public String getPassword() {return password;}

        public void setPassword(String password) {this.password = password;}

        //Parcelling part
        public User(Parcel in){
            String[] data = new String[6];

            in.readStringArray(data);
            this.username = data[0];
            this.phone = data[1];
            this.email = data[2];
            this.birth = data[3];
            this.pt = data[4];
            this.password = data[5];
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeStringArray(new String[] {this.username, this.phone, this.email, this.birth, this.pt, this.password});
        }
        public static  final Parcelable.Creator CREATOR = new Parcelable.Creator(){
            public com.example.zhanghaochong.bottomnavigationbar.Data.User createFromParcel(Parcel in){
                return new com.example.zhanghaochong.bottomnavigationbar.Data.User(in);
            }

            public com.example.zhanghaochong.bottomnavigationbar.Data.User[] newArray(int size){
                return new com.example.zhanghaochong.bottomnavigationbar.Data.User[size];
            }
        };
    }

