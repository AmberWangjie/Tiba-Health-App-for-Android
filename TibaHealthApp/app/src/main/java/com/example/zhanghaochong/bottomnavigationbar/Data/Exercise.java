package com.example.zhanghaochong.bottomnavigationbar.Data;

import java.util.ArrayList;

/**
 * Created by zhanghaochong on 17/3/30.
 */

public class Exercise {

    private ArrayList<Task> mTask;
    private String exerciseName;
    private String exerciseDate;
    private String taskNum;
    private String exerciseDuration;
    private String exerciseBody;

    public ArrayList<Task> getmTask() {
        return mTask;
    }

    public void setmTask(ArrayList<Task> mTask) {
        this.mTask = mTask;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(String exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getExerciseDuration() {
        return exerciseDuration;
    }

    public void setExerciseDuration(String exerciseDuration) {
        this.exerciseDuration = exerciseDuration;
    }

    public String getExerciseBody() {
        return exerciseBody;
    }

    public void setExerciseBody(String exerciseBody) {
        this.exerciseBody = exerciseBody;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }


}
