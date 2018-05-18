package com.example.enesakbulut.fitness;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    String classname = this.getClass().getSimpleName();

    int i;

    TextView[] tvWorkoutTitle;
    ImageView[] ivProgressDots;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);







        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutThree);
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
                    Intent intent = new Intent(getApplicationContext(), ProgressActivity.class);
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

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_workout) {
            if (!classname.equals(ProgressActivity.class.getSimpleName())) {
                i = 1;
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.closeDrawers();
            }


        } else if (id == R.id.nav_progress) {
            if (!classname.equals(ProgressActivity.class.getSimpleName())) {
                i = 2;
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.closeDrawers();
            }


        } else if (id == R.id.nav_music_playlist) {
            if (!classname.equals(ProgressActivity.class.getSimpleName())) {
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

