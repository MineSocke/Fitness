package com.example.enesakbulut.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkoutPreStart extends AppCompatActivity {

    int finalworkoutid;
    int breakTime;
    int workoutTime;
    int countWorkout;

    TextView tvWorkoutid;
    TextView tvTime;
    ImageView ivStart;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    ImageView iv6;
    ImageView iv7;
    ImageView iv8;
    ImageView iv9;
    ImageView ivMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_prestart);

        tvTime = (TextView) findViewById(R.id.tvTime);
        ivStart = (ImageView) findViewById(R.id.ivStart);
        iv1 = (ImageView) findViewById(R.id.imageView4);
        iv2 = (ImageView) findViewById(R.id.imageView5);
        iv3 = (ImageView) findViewById(R.id.imageView6);
        iv4 = (ImageView) findViewById(R.id.imageView7);
        iv5 = (ImageView) findViewById(R.id.imageView8);
        iv6 = (ImageView) findViewById(R.id.imageView9);
        iv7 = (ImageView) findViewById(R.id.imageView10);
        iv8 = (ImageView) findViewById(R.id.imageView11);
        iv9 = (ImageView) findViewById(R.id.imageView12);
        ivMusic = (ImageView) findViewById(R.id.ivMusic);

        enableImageview();
        pressStart();
        openMusicActivity();
        pressStart();

        //Get intent data from prestart class
        finalworkoutid = getIntent().getIntExtra("workoutid", 0);
        Log.i("finalworkoutid: ", finalworkoutid +"");
        Log.i("breaktime: ", breakTime + "");
        Log.i("workoutTime: ", workoutTime + "");
        Log.i("contWorkout: ", countWorkout+"");
    }


    private void enableImageview(){
        int getworkoutid = getIntent().getIntExtra("workoutid", 0);
        tvWorkoutid = (TextView) findViewById(R.id.tvWorkoutid);
        tvWorkoutid.setText(Integer.toString(getworkoutid));

        //Je nach workoutid werden dementsprechend eine Anzahl an Bildern abgerufen und ImageViews enabled.
        // Mit den ID's kann man auch den Titel und die Pausen abrufen etc.
        finalworkoutid = Integer.valueOf(getworkoutid);

        //Bilder und Anzahl an ImageView je nach Workout AB HIER!
        if (finalworkoutid == 1) {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.VISIBLE);
            iv4.setVisibility(View.VISIBLE);
            iv5.setVisibility(View.VISIBLE);

            iv1.setImageResource(R.drawable.bizeps);
            iv2.setImageResource(R.drawable.bizeps);

            tvTime.setText("10");
            breakTime = 5;   //change to 30, 5 just for test
            workoutTime = 5; //change to 60, 5 just for test
            countWorkout = 1;

        }else if(finalworkoutid==2){
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.VISIBLE);

            iv1.setImageResource(R.drawable.bizeps);
            iv2.setImageResource(R.drawable.bizeps);
            iv3.setImageResource(R.drawable.bizeps);

            tvTime.setText("7");
            breakTime = 30; //Seconds
            workoutTime = 12; //Seconds
            countWorkout = 3; //Anzahl Übungen
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
                intent.putExtra("workoutTime", workoutTime);
                intent.putExtra("countWorkout", countWorkout);
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
