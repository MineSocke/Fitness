package com.example.enesakbulut.fitness;

import java.util.ArrayList;

/**
 * Created by enesakbulut on 24.12.17.
 */

public class WorkoutData {
    int finalworkoutid;
    int breakTime;
    int workoutTime;
    int countWorkout;
    ArrayList<Integer> map = new ArrayList();
    ArrayList<String> workouts = new ArrayList<>();

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
            map.add(R.drawable.bizeps);
            map.add(R.drawable.bizeps);
            map.add(R.drawable.bizeps);
            map.add(R.drawable.bizeps);
            map.add(R.drawable.bizeps);
            map.add(R.drawable.bizeps);
            map.add(R.drawable.bizeps);
            map.add(R.drawable.bizeps);


            workouts.add("Liegestütze");
            workouts.add("Bizeps-Curls");
            workouts.add("Trizeps-Dips");
            workouts.add("Crunches");
            workouts.add("Liegestütze");
            workouts.add("Bizeps-Curls");
            workouts.add("Trizeps-Dips");
            workouts.add("Crunches");
            workouts.add("Crunches");

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

    public String getWorkouts(int i) {
        String workout = workouts.get(i);
        return workout;
    }
}