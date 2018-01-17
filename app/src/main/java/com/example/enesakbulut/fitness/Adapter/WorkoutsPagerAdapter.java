package com.example.enesakbulut.fitness.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.enesakbulut.fitness.Tab1;
import com.example.enesakbulut.fitness.Tab2;

/**
 * Created by Enes on 17.01.2018.
 */

public class WorkoutsPagerAdapter extends FragmentStatePagerAdapter{
    int countTabs;

    public WorkoutsPagerAdapter(FragmentManager fragmentManager, int mCountTabs){
        super(fragmentManager);
        this.countTabs = mCountTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return countTabs;
    }


}
