package com.example.loginlandingpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginlandingpage.model.UserLog;
import com.example.loginlandingpage.model.db.AppDAO;
import com.example.loginlandingpage.model.db.AppDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    Button mLoginBtn;
    AppDAO mAppDao;
    UserLog mUser = null;
    List<UserLog> listOfUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatabase();

        //Check insertDefaultAcc() comment below for the functionality
        if (mAppDao.getRowCount() <= 0) {
            insertDefaultAcc();
            Toast.makeText(this, "Default Account Made", Toast.LENGTH_SHORT).show();
        }

        mUsername = findViewById(R.id.usernameText);
        mPassword = findViewById(R.id.passwordText);
        mLoginBtn = findViewById(R.id.loginBtn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                if (checkUser(name)) {
                    if (!validatePassword(password)) {
                        Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT)
                                .show();
                        mPassword.setBackgroundColor(Color.RED);
                        mUsername.setBackgroundColor(Color.WHITE);
                    } else {
                        Intent intent = Landing.intentFactory(MainActivity.this, mUser.getUserId());
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No Account like that exists",
                            Toast.LENGTH_SHORT).show();
                    mUsername.setBackgroundColor(Color.RED);
                    mPassword.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    private void getDatabase() {
        mAppDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().getAppDAO();
    }

    //On first creation of the app, the app generates a default account. Opening the app any
    // other time will not allow this to run
    private void insertDefaultAcc() {
        UserLog daclink = new UserLog("din_djarin", "baby_yoda_ftw");
        mAppDao.insert(daclink);
    }

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    //This checks if the user exists in the database
    public boolean checkUser(String name) {
        mUser = mAppDao.getUserByUsername(name);
        if (mUser == null) {
            Toast.makeText(this, "No User: " + name + " found! :(", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // After checking that the user exists and the mUser object is set, it checks if the password
    // entered matches the password of the user
    public boolean validatePassword(String pw) {
        return mUser.getPassword().equals(pw);
    }
}