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

import com.example.enesakbulut.fitness.Start.WelcomeActivity;


public class WorkoutList extends AppCompatActivity {

    public static final String PREFS_SETUP = "PREFS";
    public int workoutid;
    public ImageView ivBizeps;
    public ImageView ivAbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workout);
        ivBizeps = (ImageView) findViewById(R.id.ivBizeps);
        ivAbs = (ImageView) findViewById(R.id.ivAbs);
        workoutClick();
        changeStatusBarColor();

        SharedPreferences settings = getSharedPreferences(WelcomeActivity.PREFS_SETUP, 0);
        boolean setupDone = settings.getBoolean("setupDone", false);
        // Checking for first time launch - before calling setContentView()
        if (!setupDone) {
            startActivity(new Intent(WorkoutList.this, WelcomeActivity.class));
            finish();
        }
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






    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        }
    }


}
