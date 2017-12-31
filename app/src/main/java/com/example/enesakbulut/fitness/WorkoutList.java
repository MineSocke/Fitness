package com.example.enesakbulut.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.enesakbulut.fitness.Start.WelcomeActivity;


public class WorkoutList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String PREFS_SETUP = "PREFS";
    public int workoutid;
    public ImageView ivBizeps;
    public ImageView ivAbs;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    String classname = this.getClass().getSimpleName();
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workouts);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.nav_workout){
            Toast.makeText(this, "This is Workout", Toast.LENGTH_SHORT).show();
            if(!classname.equals(WorkoutList.class.getSimpleName())){
                drawerLayout.closeDrawers();
                Intent intent = new Intent(this, WorkoutList.class);
                startActivity(intent);
            }else {
                drawerLayout.closeDrawers();
            }


        }else if (id == R.id.nav_progress){
            Toast.makeText(this, "This is progress", Toast.LENGTH_SHORT).show();
            if(!classname.equals(WorkoutList.class.getSimpleName())){
                drawerLayout.closeDrawers();
                Intent intent = new Intent(this, MusicActivityTwo.class);
                startActivity(intent);
            }else {
                drawerLayout.closeDrawers();
            }


        }else if(id == R.id.nav_music_playlist){
            Toast.makeText(this, "This is Music-Playlist", Toast.LENGTH_SHORT).show();
            if(!classname.equals(MusicActivityTwo.class.getSimpleName())) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(this, MusicActivityTwo.class);
                startActivity(intent);
            }else {
                drawerLayout.closeDrawers();
            }


        }else if(id == R.id.nav_settings){
            Toast.makeText(this, "This is Settings", Toast.LENGTH_SHORT).show();


        }else if(id == R.id.nav_bug){
            Toast.makeText(this, "This is FoundABug?", Toast.LENGTH_SHORT).show();


        }else if(id == R.id.nav_rating){
            Toast.makeText(this, "This is Rating", Toast.LENGTH_SHORT).show();



        }

        return false;
    }
}
