package com.yuri.v20.HELPER;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Stack;

public class ActivityHelper {
    private static Stack<Activity> activityStack = new Stack<>();

    public static void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }

    public static void finishAllActivities() {
        while (!activityStack.isEmpty()) {
            Activity activity = activityStack.pop();
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static int qntActivities(){
        int qnt = activityStack.size();
        return qnt;
    }
}
