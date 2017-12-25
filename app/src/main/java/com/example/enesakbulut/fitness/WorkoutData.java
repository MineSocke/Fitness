package com.example.enesakbulut.fitness;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by enesakbulut on 24.12.17.
 */

public class WorkoutData {
    int finalworkoutid;
    int breakTime;
    int workoutTime;
    int countWorkout;
    ArrayList<Integer> map = new ArrayList();

    public void setFinalworkoutid(int finalworkoutid) {
        this.finalworkoutid = finalworkoutid;
    }

    public void setBreakTime(int breakTime) {
        this.breakTime = breakTime;
    }

    public void setWorkoutTime(int workoutTime) {
        this.workoutTime = workoutTime;
    }

    public void setCountWorkout(int countWorkout) {
        this.countWorkout = countWorkout;
    }

    public void setMap(int i) {
        if(i==1){
            map.add(R.drawable.bizeps);
            map.add(R.drawable.alarmcheck);
            map.add(R.drawable.medal);
            map.add(R.drawable.bizeps);
            map.add(R.drawable.alarmcheck);
            map.add(R.drawable.medal);
            map.add(R.drawable.bizeps);
            map.add(R.drawable.alarmcheck);
            map.add(R.drawable.medal);
        }else if (i==2){
            map.add(R.drawable.run);
            map.add(R.drawable.run);
        }
    }

    public int getFinalworkoutid() {
        return finalworkoutid;
    }

    public int getBreakTime() {
        return breakTime;
    }

    public int getWorkoutTime() {
        return workoutTime;
    }

    public int getCountWorkout() {
        return countWorkout;
    }

    public ArrayList<Integer> getMap() {
        return map;
    }
}
