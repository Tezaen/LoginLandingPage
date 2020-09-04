package com.example.loginlandingpage.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.loginlandingpage.model.UserLog;

import java.util.List;

@Dao
public interface AppDAO {
    //users
    @Insert
    void insert(UserLog... userLogs);

    @Update
    void update(UserLog... userLogs);

    @Delete
    void delete(UserLog userLog);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<UserLog> getAllUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUsername = :username")
    UserLog getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserId = :userId")
    UserLog getUserByUserId(int userId);

    @Query("select count(mUserId) from " + AppDatabase.USER_TABLE)
    Integer getRowCount();
}
