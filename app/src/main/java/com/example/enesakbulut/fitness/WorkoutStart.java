package com.example.enesakbulut.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class WorkoutStart extends AppCompatActivity {

    ArrayList<ListData> data = new ArrayList<>();
    ListData listData;

    int progress = 0;
    int workoutid;
    int id;
    int countWorkout;
    ImageView ivWorkout;
    TextView tvTest;
    TextView tvTimer;
    TextView tvInfo;
    int breakTime;
    int workoutTime;
    CircularProgressBar circularProgressBar;
    boolean doubleBackToExitPressedOnce;
    int imageType;
    ImageView ivSwapPicture;
    GifImageView gifView;
    private int seconds = 10;
    private int staticSeconds = seconds;
    private int status = 0;
    private int swapPictureLogoInt = 0;
    int doublePressed;


    private MediaPlayer music;
    private MediaPlayer getReady;
    private MediaPlayer three;
    private MediaPlayer two;
    private MediaPlayer one;
    private MediaPlayer go;
    private MediaPlayer rest;
    private MediaPlayer finish;
    private MediaPlayer halftime;

    Handler handler;

    ImageView ivBack;
    ImageView ivPlay;
    ImageView ivForward;
    TextView tvName;

    int currentNumber = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_start);

        handler = new Handler();
        listData = new ListData();


        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivPlay = (ImageView) findViewById(R.id.ivPlay);
        ivForward = (ImageView) findViewById(R.id.ivForward);
        tvName = (TextView) findViewById(R.id.tvName);

        getReady = MediaPlayer.create(getApplicationContext(), R.raw.getready);
        three = MediaPlayer.create(getApplicationContext(), R.raw.three);
        two = MediaPlayer.create(getApplicationContext(), R.raw.two);
        one = MediaPlayer.create(getApplicationContext(), R.raw.one);
        go = MediaPlayer.create(getApplicationContext(), R.raw.go);
        rest = MediaPlayer.create(getApplicationContext(), R.raw.rest);
        finish = MediaPlayer.create(getApplicationContext(), R.raw.finish);
        halftime = MediaPlayer.create(getApplicationContext(), R.raw.halftime);
        music = new MediaPlayer();

        id = 1;
        imageType = 1;
        circularProgressBar = (CircularProgressBar) findViewById(R.id.CircularProgressbar);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        ivWorkout = (ImageView) findViewById(R.id.ivWorkout);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvTest = (TextView) findViewById(R.id.tvTest);
        ivSwapPicture = (ImageView) findViewById(R.id.ivSwapPicture);
        gifView = (GifImageView) findViewById(R.id.gifView);

        //Get intent data from prestart class
        workoutid = getIntent().getIntExtra("workoutid", 0);
        breakTime = getIntent().getIntExtra("breakTime", 0);
        workoutTime = getIntent().getIntExtra("workoutTime", 0);
        countWorkout = getIntent().getIntExtra("countWorkout", 0);

        //Launch Methods
        timer();
        changeImageTypeButtonPressed();
        getArrayAndFillArrayList();
        setBarData();

    }
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            if(music.isPlaying()) {
                music.stop();
            }
            super.onBackPressed();
            doublePressed = 1;
            killActivity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press twice to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1500);
    }

    public void killActivity(){
        finish();
    }

    public void changeImageType() {

        //FIX THIS MESS :)
        if (workoutid == 1) { //workoutid welches workout angeklickt wurde
            Log.i("workoutid:", "erfolgreich");
            if (id == 1) {
                Log.i("workoutreihenfolgeID", "erfolgreich");
                if (imageType == 1) { // wenn standbild
                    Log.i("standbild", "erfolgreich");
                    ivWorkout.setVisibility(View.INVISIBLE);
                    gifView.setVisibility(View.VISIBLE);
                    gifView.setImageResource(R.drawable.bizepsanimated);

                    imageType = 2; //Gif gesetzt
                } else { //wenn gif
                    ivWorkout.setVisibility(View.VISIBLE);
                    gifView.setVisibility(View.INVISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizeps);
                    imageType = 1;
                }
            } else if (id == 2) {
                if (imageType == 1) {
                    ivWorkout.setVisibility(View.INVISIBLE);
                    gifView.setVisibility(View.VISIBLE);
                    gifView.setImageResource(R.drawable.bizepsanimated);
                    imageType = 2;
                } else {
                    ivWorkout.setVisibility(View.VISIBLE);
                    gifView.setVisibility(View.INVISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizeps);
                    imageType = 1;
                }
            }


        } else if (workoutid == 2) {
            if (id == 1) {
                if (imageType == 1) {
                    ivWorkout.setVisibility(View.INVISIBLE);
                    gifView.setVisibility(View.VISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizepsanimated);
                    imageType = 2;
                } else {
                    ivWorkout.setVisibility(View.VISIBLE);
                    gifView.setVisibility(View.INVISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizeps);
                    imageType = 1;
                }
            } else if (id == 2) {
                if (imageType == 1) {
                    ivWorkout.setVisibility(View.INVISIBLE);
                    gifView.setVisibility(View.VISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizepsanimated);
                    imageType = 2;
                } else {
                    ivWorkout.setVisibility(View.VISIBLE);
                    gifView.setVisibility(View.INVISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizeps);
                    imageType = 1;
                }
            }


        } else if (workoutid == 3) {
            if (id == 1) {
                if (imageType == 1) {
                    ivWorkout.setVisibility(View.INVISIBLE);
                    gifView.setVisibility(View.VISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizepsanimated);
                    imageType = 2;
                } else if (imageType == 2) {
                    ivWorkout.setVisibility(View.INVISIBLE);
                    gifView.setVisibility(View.VISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizeps);
                    imageType = 1;
                }
            } else if (id == 2) {
                if (imageType == 1) {
                    ivWorkout.setVisibility(View.INVISIBLE);
                    gifView.setVisibility(View.VISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizepsanimated);
                    imageType = 2;
                } else if (imageType == 2) {
                    ivWorkout.setVisibility(View.INVISIBLE);
                    gifView.setVisibility(View.VISIBLE);
                    ivWorkout.setImageResource(R.drawable.bizeps);
                    imageType = 1;
                }
            }
        }
    }

    public void changeImage(){
        WorkoutData workoutData = new WorkoutData();
        workoutData.setMap(workoutid);
        ivWorkout.setImageResource(workoutData.getMap().get(id));
        id++;

    }

    public void changeSwapPictureLogo(){
        if (swapPictureLogoInt == 0) {
            ivSwapPicture.setImageResource(R.drawable.swappicturetwo);
            swapPictureLogoInt = 1;
        }else {
            ivSwapPicture.setImageResource(R.drawable.swappicture);
            swapPictureLogoInt = 0;
        }
    }

    public void changeImageTypeButtonPressed() {
        ivSwapPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImageType();
                changeSwapPictureLogo();
                Log.i("Swapbutton: ", "Gedrückt");
            }
        });

    }

    private void timer(){
        WorkoutData workoutData = new WorkoutData();
        workoutData.setMap(workoutid);
        ivWorkout.setImageResource(workoutData.getMap().get(id));
        getReady.start();

        tvInfo.setText("Get Ready!");
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(seconds>0){
                    seconds--;
                    progress++;
                    handler.postDelayed(this, 1000);
                    tvTimer.setText(seconds + "");
                    circularProgressBar.setProgressWithAnimation(((int) progress * 100) / staticSeconds, 1000); //Hier +1000

                    if (seconds == 3) {
                        three.start();  //Audio: "Three"
                    } else if(seconds == 2) {
                        two.start();    //Audio: "Two"
                    } else if(seconds == 1) {
                        one.start();    //Audio: "One"
                    }

                    circularProgressBar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (status == 0){
                                onPause();
                                status = 1;
                            } else if (status == 1){
                                onResume();
                                status = 0;
                            }
                        }
                    });
                    if(doublePressed == 1){
                        handler.removeCallbacks(this);
                    }
                } else {
                    //TIMER COMPLETE
                    handler.removeCallbacks(this);
                    timer2();
                }
            }
            private void onPause(){
                handler.removeCallbacks(this);
            }

            private void onResume(){
                run();
            }
        });


    }

    private void timer2(){
        go.start(); //Audio: "Go!"
        seconds = getIntent().getIntExtra("workoutTime", 0);
        staticSeconds = seconds;
        Log.i("seconds", staticSeconds+"");
        tvInfo.setText("Workout!");

        progress = 0;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (seconds > 0) {
                    seconds--;
                    progress++;
                    handler.postDelayed(this, 1000);
                    tvTimer.setText(seconds + "");
                    circularProgressBar.setProgressWithAnimation(((int) progress * 100) / staticSeconds, 1000); //Hier +1000

                    if (seconds == 3) {
                        three.start();  //Audio: "Three"
                    } else if (seconds == 2) {
                        two.start();    //Audio: "Two"
                    } else if (seconds == 1) {
                        one.start();    //Audio: "One"
                    } else if (seconds == staticSeconds / 2) {
                        halftime.start();
                    }

                    circularProgressBar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (status == 0) {
                                onPause();
                                status = 1;
                            } else if (status == 1) {
                                onResume();
                                status = 0;
                            }
                        }
                    });
                } else {
                    //TIMER COMPLETE
                    handler.removeCallbacks(this);
                    if (id == countWorkout) {
                        Intent intent = new Intent(getApplicationContext(), WorkoutFinish.class);
                        intent.putExtra("countWorkout", countWorkout);
                        startActivity(intent);
                        finish();
                    } else {
                        timer3();
                    }
                }
                if (doublePressed == 1) {
                    handler.removeCallbacks(this);
                }
            }
            private void onPause(){
                handler.removeCallbacks(this);
            }

            private void onResume(){
                run();
            }
        });

    }

    private void timer3() {
        rest.start(); //Audio: "Rest!"

        changeImage();
        seconds = getIntent().getIntExtra("breakTime", 0);
        staticSeconds = seconds;
        tvInfo.setText("Rest!");

        progress = 0;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (seconds > 0) {
                    seconds--;
                    progress++;
                    handler.postDelayed(this, 1000);
                    tvTimer.setText(seconds + "");
                    circularProgressBar.setProgressWithAnimation(((int) progress * 100) / staticSeconds, 1000); //Hier +1000

                    if (seconds == 3) {
                        three.start();  //Audio: "Three"
                    } else if (seconds == 2) {
                        two.start();    //Audio: "Two"
                    } else if (seconds == 1) {
                        one.start();    //Audio: "One"
                    } else if (seconds == staticSeconds / 2) {
                        halftime.start();
                    }

                    circularProgressBar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (status == 0) {
                                onPause();
                                status = 1;
                            } else if (status == 1) {
                                onResume();
                                status = 0;
                            }
                        }
                    });
                } else {
                    //TIMER COMPLETE
                    handler.removeCallbacks(this);
                    timer2();
                }
                if (doublePressed == 1) {
                    handler.removeCallbacks(this);
                }
            }

            private void onPause() {
                handler.removeCallbacks(this);
            }

            private void onResume() {
                run();
            }
        });

    }

    public void getArrayAndFillArrayList(){
        SharedPreferences sharedArrayList = getSharedPreferences("sharedArrayList", 0 );
        Gson gson = new Gson();
        String json = sharedArrayList.getString("jsonList", null);
        Type type = new TypeToken<ArrayList<ListData>>(){}.getType();
        data = gson.fromJson(json, type);

        if(data==null){
            data = new ArrayList<>();
        }
    }

    public void setBarData() {

        if (data.size() > 0) {
            tvName.setText(data.get(currentNumber).getName());

            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentNumber >= 0) {
                        currentNumber--;
                        tvName.setText(data.get(currentNumber).getName());
                        playMusic();
                    }
                }
            });

            ivForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentNumber < (data.size()) - 1) {
                        currentNumber++;
                        tvName.setText(data.get(currentNumber).getName());
                        playMusic();
                    }
                }
            });

            ivPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(music.isPlaying()){
                        music.pause();
                    }else{
                        playMusic();
                    }
                }

            });
        }else{
            tvName.setText("No Playlist");
        }
    }

    public void playMusic(){
        music.reset();
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            music = new MediaPlayer();
            music.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                Uri uri = Uri.parse(data.get(currentNumber).getUri());
                music.setDataSource(getApplicationContext(), uri);
                music.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        music.start();
                    }
                });
                music.prepareAsync();

            } catch (IOException e) {
                e.printStackTrace();
        }
    }
}