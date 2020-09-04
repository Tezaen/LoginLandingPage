package com.example.loginlandingpage.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.loginlandingpage.model.db.AppDatabase;

@Entity(tableName = AppDatabase.USER_TABLE)
public class UserLog {
    @PrimaryKey(autoGenerate = true)
    private int mUserId;

    private String mUsername;
    private String mPassword;

    public UserLog(String mUsername, String mPassword){
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
