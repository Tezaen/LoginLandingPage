package com.example.loginlandingpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginlandingpage.model.UserLog;
import com.example.loginlandingpage.model.db.AppDAO;
import com.example.loginlandingpage.model.db.AppDatabase;

public class Landing extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button logOut;
    AppDAO mAppDao;
    UserLog mUser;
    int mUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        getDatabase();
        logOut = findViewById(R.id.logoutBtn);

        Intent intent = getIntent();
        mUserId = intent.getIntExtra(TAG, -1);
        loginUser(mUserId);

        logOut.setText("Welcome, " + mUser.getUsername() + "... NOW LOG OUT BY CLICKING THIS BUTTON!");

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    public static Intent intentFactory(Context context, int id){
        Intent intent = new Intent(context, Landing.class);
        intent.putExtra(TAG, id);
        return intent;
    }

    private void loginUser(int mUserId) {
        mUser = mAppDao.getUserByUserId(mUserId);
    }

    private void logoutUser() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setMessage("Do you want to log out?");
        alertBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mUserId = -1;
                        mUser = null;
                        Intent mainActivityIntent = MainActivity.intentFactory(getApplicationContext());
                        startActivity(mainActivityIntent);
                    }
                });
        alertBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Nothin :D
                    }
                }
        );

        alertBuilder.create().show();
    }

    private void getDatabase() {
        mAppDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).allowMainThreadQueries().build().getAppDAO();
    }
}