package com.chenyuwei.basematerial;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by vivi on 2016/8/31.
 */

public class BaseApplication extends Application {

    protected RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(this);
    }

    public RequestQueue getQueue() {
        return queue;
    }
}