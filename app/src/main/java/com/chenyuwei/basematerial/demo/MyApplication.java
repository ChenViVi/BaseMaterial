package com.chenyuwei.basematerial.demo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chenyuwei.basematerial.BaseApplication;
import com.chenyuwei.basematerial.network.RequestMaker;

/**
 * Created by vivi on 2016/8/31.
 */

public class MyApplication extends BaseApplication {

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