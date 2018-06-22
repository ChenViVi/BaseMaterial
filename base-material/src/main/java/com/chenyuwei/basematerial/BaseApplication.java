package com.chenyuwei.basematerial;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vivi on 2016/11/3.
 */

public class BaseApplication extends Application{

    private List<Activity> activities = new LinkedList<Activity>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public void exitAllActivities(){
        for (Activity activity:activities) {
            if (activity != null)
                activity.finish();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
