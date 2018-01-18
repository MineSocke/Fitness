package com.example.enesakbulut.fitness.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.enesakbulut.fitness.Tab1;
import com.example.enesakbulut.fitness.Tab2;

/**
 * Created by Enes on 17.01.2018.
 */

public class WorkoutsPagerAdapter extends FragmentPagerAdapter {


    public WorkoutsPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Tab1();
            case 1:
                return new Tab2();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Workouts";
            case 1:
                return "Progress";
            default:
                return null;
        }
    }
}
