package com.example.loginlandingpage.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.loginlandingpage.model.UserLog;


@Database(entities = {UserLog.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public static final String DB_NAME = "LOGINLANDING_DATABASE";
    public static final String USER_TABLE = "USERLOG_CLASS";

    public abstract AppDAO getAppDAO();
}
