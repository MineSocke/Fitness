package com.example.enesakbulut.fitness;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enesakbulut.fitness.Adapter.WorkoutsPagerAdapter;

import java.util.ArrayList;


public class Tab1 extends Fragment {
    String finalworkoutid;
    String breakTime;
    String workoutTime;
    String countWorkout;
    ArrayList<Integer> map = new ArrayList<>();
    ArrayList<String> workouts = new ArrayList<>();

    LinearLayout linearLayout;
    LinearLayout linearLayout2;

    WorkoutData workoutData = new WorkoutData();
    ImageView[] imageViews;
    Bundle bundle = this.getArguments();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        finalworkoutid = bundle.getString("workoutid");
        createImageViews();
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    public void createImageViews(){
        workoutData.setCountWorkout(Integer.valueOf(finalworkoutid));
        imageViews = new ImageView[workoutData.getCountWorkout()];
        Log.e("finalworkoutID: ", finalworkoutid + "");
        for(int i=0; i<workoutData.getCountWorkout();i++){
            WorkoutData workoutData = new WorkoutData();
            workoutData.setFinalworkoutid(Integer.valueOf(finalworkoutid));
            workoutData.setBreakTime(Integer.valueOf(breakTime));
            workoutData.setWorkoutTime(Integer.valueOf(workoutTime));
            workoutData.setCountWorkout(Integer.valueOf(countWorkout));
            workoutData.setMap(Integer.valueOf(finalworkoutid));

            imageViews[i] = new ImageView(getActivity());
            imageViews[i].setTag("i");
            imageViews[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));

            map = workoutData.getMap();
            imageViews[i].setImageResource(map.get(i));
            Log.e("Picture: ", map.get(i) + "");
            linearLayout.addView(imageViews[i]);

            TextView textView = new TextView(getActivity());
            textView.setTag("i");
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            textView.setTextSize(19);
            textView.setText(workoutData.getWorkouts(i));
            linearLayout2.addView(textView);

        }
    }

}
