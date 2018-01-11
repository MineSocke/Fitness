package com.example.enesakbulut.fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.enesakbulut.fitness.Adapter.CustomWorkoutAdapter;
import com.example.enesakbulut.fitness.Start.WelcomeActivity;

import java.util.ArrayList;


public class WorkoutListTwo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String PREFS_SETUP = "PREFS";
    public int workoutid;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    String classname = this.getClass().getSimpleName();
    NavigationView navigationView;

    WorkoutDataList workoutDataList;
    ArrayList<WorkoutDataList> data = new ArrayList<>();
    ListView lvWorkoutList;
    CustomWorkoutAdapter customWorkoutAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workouts_two);


        workoutDataList = new WorkoutDataList();
        lvWorkoutList = (ListView) findViewById(R.id.lvWorkoutList);
        customWorkoutAdapter = new CustomWorkoutAdapter(getApplicationContext(), data);
        lvWorkoutList.setAdapter(customWorkoutAdapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutTwo);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        workoutClick();

        SharedPreferences settings = getSharedPreferences(WelcomeActivity.PREFS_SETUP, 0);
        boolean setupDone = settings.getBoolean("setupDone", false);
        // Checking for first time launch - before calling setContentView()
        if (!setupDone) {
            startActivity(new Intent(WorkoutListTwo.this, WelcomeActivity.class));
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
        for (int i = 0; i<2; i++){
        WorkoutDataList workoutDataList = new WorkoutDataList();
        workoutDataList.setWorkoutListMap(i);
        workoutDataList.setProgress(i+1);

        data.add(workoutDataList);
        customWorkoutAdapter.notifyDataSetChanged();
        lvWorkoutList.setAdapter(customWorkoutAdapter);
        }
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
            if(!classname.equals(WorkoutListTwo.class.getSimpleName())){
                drawerLayout.closeDrawers();
                Intent intent = new Intent(this, WorkoutListTwo.class);
                startActivity(intent);
            }else {
                drawerLayout.closeDrawers();
            }


        }else if (id == R.id.nav_progress){
            Toast.makeText(this, "This is progress", Toast.LENGTH_SHORT).show();
            if(!classname.equals(WorkoutListTwo.class.getSimpleName())){
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
