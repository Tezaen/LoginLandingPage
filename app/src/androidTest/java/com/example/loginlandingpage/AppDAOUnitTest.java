package com.example.loginlandingpage;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.loginlandingpage.model.UserLog;
import com.example.loginlandingpage.model.db.AppDAO;
import com.example.loginlandingpage.model.db.AppDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AppDAOUnitTest {
    private AppDatabase db;
    private AppDAO userDAO;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDAO = db.getAppDAO();
    }

    @After
    public void closeDB() throws IOException {
        db.close();
    }

    @Test
    public void verifyUsernamePassesTest() {
        boolean pass = false;
        UserLog testUser = new UserLog("din_djarin", "baby_yoda_ftw");
        UserLog mUser = new UserLog("din_djarin", "baby_yoda_ftw");
        userDAO.insert(mUser);
        UserLog dummyUser = userDAO.getUserByUsername(testUser.getUsername());
        if (dummyUser != null) {
            pass = true;
        }
        assertTrue(pass);
    }

    @Test
    public void verifyUsernameFailsTest() {
        boolean pass = false;
        UserLog testUser = new UserLog("din_djarinNOTTT", "baby_yoda_ftw");
        UserLog mUser = new UserLog("din_djarin", "baby_yoda_ftw");
        userDAO.insert(mUser);
        UserLog dummyUser = userDAO.getUserByUsername(testUser.getUsername());
        if (dummyUser != null) {
            pass = true;
        }
        assertFalse(pass);
    }

    @Test
    public void verifyPasswordPassesTest() {
        boolean pass = false;
        UserLog testUser = new UserLog("din_djarin", "baby_yoda_ftw");
        UserLog mUser = new UserLog("din_djarin", "baby_yoda_ftw");
        userDAO.insert(mUser);
        List<UserLog> listOfAllUsers = userDAO.getAllUsers();
        System.out.println("Size of list: " + listOfAllUsers.size());
        for (UserLog user : listOfAllUsers) {
            if (user.getUsername().equals(testUser.getUsername())) {
                if (user.getPassword().equals(testUser.getPassword())) {
                    pass = true;
                }
            }
        }
        assertTrue(pass);
    }

    @Test
    public void verifyPasswordFailsTest() {
        boolean pass = false;
        UserLog testUser = new UserLog("din_djarin", "beskar_4_ever");
        UserLog mUser = new UserLog("din_djarin", "baby_yoda_ftw");
        userDAO.insert(mUser);
        List<UserLog> listOfAllUsers = userDAO.getAllUsers();
        System.out.println("Size of list: " + listOfAllUsers.size());
        for (UserLog user : listOfAllUsers) {
            if (user.getUsername().equals(testUser.getUsername())) {
                if (user.getPassword().equals(testUser.getPassword())) {
                    pass = true;
                }
            }
        }
        assertFalse(pass);
    }
}
