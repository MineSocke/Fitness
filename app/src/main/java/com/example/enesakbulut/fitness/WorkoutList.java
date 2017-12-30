package com.example.enesakbulut.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.enesakbulut.fitness.Start.WelcomeActivity;


public class WorkoutList extends AppCompatActivity {

    public static final String PREFS_SETUP = "PREFS";
    public int workoutid;
    public ImageView ivBizeps;
    public ImageView ivAbs;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workouts);
        ivBizeps = (ImageView) findViewById(R.id.ivBizeps);
        ivAbs = (ImageView) findViewById(R.id.ivAbs);
        workoutClick();

        SharedPreferences settings = getSharedPreferences(WelcomeActivity.PREFS_SETUP, 0);
        boolean setupDone = settings.getBoolean("setupDone", false);
        // Checking for first time launch - before calling setContentView()
        if (!setupDone) {
            startActivity(new Intent(WorkoutList.this, WelcomeActivity.class));
            finish();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    private void workoutClick() {
        //BIZEPS WORKOUT
        ivBizeps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutid = 1;
                clickedButton();
            }
        });

        //ABS WORKOUT
        ivAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutid = 2;
                clickedButton();
            }
        });
    }


    private void clickedButton(){
        Intent intent = new Intent(getApplicationContext(), WorkoutPreStart.class);
        intent.putExtra("workoutid", workoutid);
        startActivity(intent);
    }


}
