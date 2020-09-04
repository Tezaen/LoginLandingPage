package com.example.loginlandingpage;

import android.content.Context;
import android.content.Intent;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AppDAOUnitTest {
    private AppDatabase db;
    private AppDAO userDAO;
    private Context context;

    @Before
    public void createDB() {
        context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDAO = db.getAppDAO();
    }

    @After
    public void closeDB() {
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
        String enteredPassword = "baby_yoda_ftw";
        UserLog mUser = new UserLog("din_djarin", "baby_yoda_ftw");
        userDAO.insert(mUser);
        assertEquals(mUser.getPassword(), enteredPassword);
    }

    @Test
    public void verifyPasswordFailsTest() {
        String enteredPassword = "baeskar_4_ever";
        UserLog mUser = new UserLog("din_djarin", "baby_yoda_ftw");
        userDAO.insert(mUser);
        assertNotEquals(mUser.getPassword(), enteredPassword);
    }

    @Test
    public void verifyGoToLandingTest() {
        Intent intent = Landing.intentFactory(context, 1);
        assertNotNull(intent);
    }
}
