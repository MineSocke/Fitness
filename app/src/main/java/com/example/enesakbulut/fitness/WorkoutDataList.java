package com.example.enesakbulut.fitness;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by enesakbulut on 24.12.17.
 */

public class WorkoutDataList {

    //WorkoutList part
    int progress;
    ArrayList<Integer> workoutListMap = new ArrayList();

    public void setWorkoutListMap(int i) {
        if(i==0){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==1){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==2){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==3){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==4){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==5){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==6){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==7){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==8){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==9){
            workoutListMap.add(R.drawable.bizeps);
        }else if (i==10){
            workoutListMap.add(R.drawable.bizeps);
        }
    }

    public int getWorkoutListMap() {
        Log.e("OUTPUTLISTWORKOUTDATA: ", workoutListMap.size() + "");
        return workoutListMap.get(0);
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgress(){
        return progress;
    }
}
