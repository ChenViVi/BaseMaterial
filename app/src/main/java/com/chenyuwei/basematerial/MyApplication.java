package com.chenyuwei.basematerial;

import android.app.Application;

/**
 * Created by vivi on 2016/8/31.
 */
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chenyuwei.basematerial.network.RequestMaker;

public class MyApplication extends Application {

    private RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(this);
        RequestMaker.setBaseUrl("");
    }

    public RequestQueue getQueue() {
        return queue;
    }
}