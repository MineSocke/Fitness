package com.example.enesakbulut.fitness;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WorkoutPreStart extends AppCompatActivity {

    int finalworkoutid;
    int breakTime;
    int workoutTime;
    int countWorkout;
    ArrayList<Integer> map = new ArrayList<>();
    ArrayList<String> workouts = new ArrayList<>();

    TextView tvTime;
    ImageView ivStart;
    ImageView ivMusic;
    LinearLayout linearLayout;
    LinearLayout linearLayout2;

    WorkoutData workoutData = new WorkoutData();
    ImageView[] imageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_prestart);

        tvTime = (TextView) findViewById(R.id.tvTime);
        ivStart = (ImageView) findViewById(R.id.ivStart);
        ivMusic = (ImageView) findViewById(R.id.ivMusic);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);


        //Get intent data from prestart class
        finalworkoutid = getIntent().getIntExtra("workoutid", 0);


        pressStart();
        openMusicActivity();
        pressStart();
        createImageViews();

        workoutData.setTotalTime(finalworkoutid);
        tvTime.setText(String.valueOf(workoutData.getTotalTime()));

        Log.i("finalworkoutid: ", finalworkoutid +"");
        Log.i("breaktime: ", breakTime + "");
        Log.i("workoutTime: ", workoutTime + "");
        Log.i("countWorkout: ", countWorkout+"");
    }

    public void pressStart(){
        ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), WorkoutStart.class);
                Intent intent = new Intent(getApplicationContext(), WorkoutStart.class);
                intent.putExtra("workoutid", finalworkoutid);
                intent.putExtra("breakTime", breakTime);
                intent.putExtra("countWorkout", countWorkout);

                workoutData.setBreakTime(finalworkoutid);
                intent.putExtra("breakTime", workoutData.getBreakTime());

                workoutData.setWorkoutTime(finalworkoutid);
                intent.putExtra("workoutTime", workoutData.getWorkoutTime());

                startActivity(intent);
            }
        });
    }

    public void openMusicActivity(){
            ivMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MusicActivityTwo.class);
                    intent.putExtra("workoutid", finalworkoutid);
                    startActivity(intent);
                }
            });
    }

    public void createImageViews(){
        workoutData.setCountWorkout(finalworkoutid);
        imageViews = new ImageView[workoutData.getCountWorkout()];
        Log.e("finalworkoutID: ", finalworkoutid + "");
        for(int i=0; i<workoutData.getCountWorkout();i++){
            WorkoutData workoutData = new WorkoutData();
            workoutData.setFinalworkoutid(finalworkoutid);
            workoutData.setBreakTime(breakTime);
            workoutData.setWorkoutTime(workoutTime);
            workoutData.setCountWorkout(countWorkout);
            workoutData.setMap(finalworkoutid);

            imageViews[i] = new ImageView(this);
            imageViews[i].setTag("i");
            imageViews[i].setLayoutParams(new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));

            map = workoutData.getMap();
            imageViews[i].setImageResource(map.get(i));
            Log.e("Picture: ", map.get(i) + "");
            linearLayout.addView(imageViews[i]);

            TextView textView = new TextView(this);
            textView.setTag("i");
            textView.setLayoutParams(new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            textView.setTextSize(19);
            textView.setText(workoutData.getWorkouts(i));
            linearLayout2.addView(textView);

        }
    }
}