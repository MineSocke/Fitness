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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.enesakbulut.fitness.Adapter.CustomWorkoutAdapter;
import com.example.enesakbulut.fitness.Start.WelcomeActivity;

import java.util.ArrayList;


public class WorkoutListTwo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String PREFS_SETUP = "PREFS";
    public int workoutid;
    int i;

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

        setupList();
        clickWorkout();

        SharedPreferences settings = getSharedPreferences(WelcomeActivity.PREFS_SETUP, 0);
        boolean setupDone = settings.getBoolean("setupDone", false);
        // Checking for first time launch - before calling setContentView()
        if (!setupDone) {
            startActivity(new Intent(WorkoutListTwo.this, WelcomeActivity.class));
            finish();
        }

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                if (i == 1) {
                    Intent intent = new Intent(getApplicationContext(), WorkoutListTwo.class);
                    startActivity(intent);
                } else if (i == 2) {
                    Intent intent = new Intent(getApplicationContext(), WorkoutListTwo.class);
                    startActivity(intent);
                } else if (i == 3) {
                    Intent intent = new Intent(getApplicationContext(), MusicActivityTwo.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        lvWorkoutList.setDivider(this.getResources().getDrawable(R.drawable.transperent_color));
        lvWorkoutList.setDividerHeight(20);
        lvWorkoutList.setPadding(10,0,10,0);
        View headerView = getLayoutInflater().inflate(R.layout.listview_header, lvWorkoutList, false);
        lvWorkoutList.addHeaderView(headerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupList() {
        for (int i = 0; i < 10; i++) {
            WorkoutDataList workoutDataList = new WorkoutDataList();
            workoutDataList.setWorkoutListMap(i);
            workoutDataList.setProgress(i + 1);

            data.add(workoutDataList);
            customWorkoutAdapter.notifyDataSetChanged();
            lvWorkoutList.setAdapter(customWorkoutAdapter);
        }
    }

    private void clickWorkout() {
        lvWorkoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                workoutid = i + 1;
                Log.e("WORKOUTIDXD: ", workoutid + "");
                WorkoutData workoutData = new WorkoutData();
                SharedPreferences sharedPreferences = getSharedPreferences("workoutid", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putInt("workoutid", workoutid);
                editor.apply();
                clickedButton();
            }
        });
    }


    private void clickedButton() {
        Intent intent = new Intent(getApplicationContext(), WorkoutPreStartTwo.class);
        intent.putExtra("workoutid", workoutid);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_workout) {
            if (!classname.equals(WorkoutListTwo.class.getSimpleName())) {
                i = 1;
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.closeDrawers();
            }


        } else if (id == R.id.nav_progress) {
            if (!classname.equals(WorkoutListTwo.class.getSimpleName())) {
                i = 2;
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.closeDrawers();
            }


        } else if (id == R.id.nav_music_playlist) {
            if (!classname.equals(MusicActivityTwo.class.getSimpleName())) {
                i = 3;
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.closeDrawers();
            }


        } else if (id == R.id.nav_settings) {


        } else if (id == R.id.nav_bug) {


        } else if (id == R.id.nav_rating) {


        }
        return false;
    }
}
