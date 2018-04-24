package com.example.enesakbulut.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enesakbulut.fitness.Adapter.WorkoutsPagerAdapter;

import java.util.ArrayList;

public class WorkoutPreStartTwo extends AppCompatActivity {

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

    SharedPreferences workoutidShared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_prestarttwo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new WorkoutsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);



        tvTime = (TextView) findViewById(R.id.tvTime);
        ivStart = (ImageView) findViewById(R.id.ivStart);
        ivMusic = (ImageView) findViewById(R.id.ivMusic);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);


        //Get intent data from prestart class
        finalworkoutid = getIntent().getIntExtra("workoutid", 0);
        if (finalworkoutid != 0){
            workoutidShared = getSharedPreferences("workoutidShared", 0);
            SharedPreferences.Editor editor = workoutidShared.edit();
            editor.clear();
            editor.putInt("workoutid", finalworkoutid);
            editor.apply();
        }




        rebuildFromMusicActivity();

        workoutData.setTotalTime(finalworkoutid);
        tvTime.setText(String.valueOf(workoutData.getTotalTime()));


        openMusicActivity();
        pressStart();

        workoutData.setCountWorkout(finalworkoutid);


        Log.i("finalworkoutid: ", finalworkoutid +"");
        Log.i("breaktime: ", breakTime + "");
        Log.i("workoutTime: ", workoutTime + "");
        Log.i("countWorkout: ", countWorkout+"");

    }

    public void rebuildFromMusicActivity(){
        if (finalworkoutid == 0) {
            workoutidShared = getSharedPreferences("workoutidShared", 0);
            finalworkoutid = workoutidShared.getInt("workoutid", 0 );
        }
    }


    public void pressStart(){
        ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), WorkoutStart.class);
                Intent intent = new Intent(getApplicationContext(), WorkoutStart.class);
                intent.putExtra("workoutid", finalworkoutid);
                intent.putExtra("breakTime", breakTime);
                intent.putExtra("countWorkout", workoutData.getCountWorkout());

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


}