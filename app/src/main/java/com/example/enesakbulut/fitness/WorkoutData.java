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
    int totalTime;

    ArrayList<Integer> map = new ArrayList();
    ArrayList<Integer> mapGif = new ArrayList<>();
    ArrayList<String> workouts = new ArrayList<>();


    public void setTotalTime(int totalTime) {
        if(totalTime == 1){
            totalTime = 22;
        }else if(totalTime == 2){
            totalTime = 7;
        }
        this.totalTime = totalTime;
    }

    public void setFinalworkoutid(int finalworkoutid) {
        this.finalworkoutid = finalworkoutid;
    }

    public void setBreakTime(int breakTime) {
        if(breakTime == 1){
            breakTime = 30;
        }else if(breakTime == 2){
            breakTime = 20;
        }
        this.breakTime = breakTime;
    }

    public void setWorkoutTime(int workoutTime) {
        if(workoutTime == 1){
            workoutTime = 20;
        }else if(workoutTime == 2){
            workoutTime = 10;
        }
        this.workoutTime = workoutTime;
    }

    public void setCountWorkout(int countWorkout) {
        if (countWorkout == 1){
            countWorkout = 2;
        } else if (countWorkout == 2){
            countWorkout = 2;
        }
        this.countWorkout = countWorkout;
    }

    public void setMap(int i) {
        if(i==1){
            map.add(R.drawable.medal);
            map.add(R.drawable.bizeps);

            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);

            workouts.add("Liegestütze");
            workouts.add("Bizeps-Curls");

        }else if (i==2){
            map.add(R.drawable.medal);
            map.add(R.drawable.medal);
            map.add(R.drawable.medal);
            map.add(R.drawable.medal);
            map.add(R.drawable.medal);
            map.add(R.drawable.medal);
            map.add(R.drawable.medal);
            map.add(R.drawable.medal);
            map.add(R.drawable.medal);
            map.add(R.drawable.medal);

            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);
            mapGif.add(R.drawable.bizepsanimated);

            workouts.add("Liegestütze");
            workouts.add("Bizeps-Curls");
            workouts.add("Trizeps-Dips");
            workouts.add("Crunches");
            workouts.add("Liegestütze");
            workouts.add("Bizeps-Curls");
            workouts.add("Trizeps-Dips");
            workouts.add("Crunches");
            workouts.add("Crunches");
            workouts.add("Crunches");
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

    public int getTotalTime(){
        return totalTime;
    }

    public ArrayList<Integer> getMap() {
        return map;
    }

    public ArrayList<Integer> getMapGif() {
        return mapGif;
    }

    public String getWorkouts(int i) {
        String workout = workouts.get(i);
        return workout;
    }


}
