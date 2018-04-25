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
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkoutFinish extends AppCompatActivity {

    Button bDone;
    Button bShare;
    TextView tvCounter;
    WorkoutData workoutData = new WorkoutData();
    private int workoutCounter;
    int workoutid;

    ArrayList<WorkoutProgress> data = new ArrayList<>();
    WorkoutProgress workoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_finish);
        getSupportActionBar().hide();

        bDone = (Button) findViewById(R.id.bDone);
        bShare = (Button) findViewById(R.id.bShare);
        tvCounter = (TextView) findViewById(R.id.tvCounter);

        workoutCounter++;

        workoutCounter = getIntent().getIntExtra("countWorkout", 0);
        tvCounter.setText(getResources().getString(R.string.workout_count) + workoutCounter);
        workoutid = getIntent().getIntExtra("workoutid", 0);

        saveData2();
        bSharePressed();
        bDonePressed();
    }

    private void bDonePressed(){
        bDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WorkoutListTwo.class);
                startActivity(intent);
            }
        });
    }


    private void bSharePressed(){
        bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String textBody = getString(R.string.share_text_main);
                String textSub = getString(R.string.share_text_sub);
                intent.putExtra(Intent.EXTRA_SUBJECT, textSub);
                intent.putExtra(Intent.EXTRA_TEXT, textBody);
                startActivity(Intent.createChooser(intent, "Share using"));
            }
        });

    }

    public void saveData2(){
        SharedPreferences sharedProgresslist = getSharedPreferences("sharedProgresslist", 0);
        SharedPreferences.Editor editor = sharedProgresslist.edit();

        int progress = sharedProgresslist.getInt(String.valueOf(workoutid), 0);
        editor.putInt(String.valueOf(workoutid), progress+1);
        editor.apply();

    }
}
