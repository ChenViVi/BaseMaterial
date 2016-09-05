package com.chenyuwei.basematerial.network;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chenyuwei.basematerial.MyApplication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by vivi on 2016/8/31.
 */
public abstract class RequestMaker {

    private static String baseUrl = "";
    protected Activity activity;
    protected String url;
    protected String tag;
    private int failedTime = 5000;
    private RequestQueue queue;

    public RequestMaker(Activity activity, Method method,String url) {
        this.activity = activity;
        this.url = url;
        queue = ((MyApplication) activity.getApplication()).getQueue();
        switch(method) {
            case GET:
                requestGet();
                break;
            case POST:
                requestPost();
                break;
        }
    }

    public RequestMaker(Activity activity,Method method,String url,String tag) {
        this.activity = activity;
        this.url = url;
        this.tag = tag;
        queue = ((MyApplication) activity.getApplication()).getQueue();
        switch(method) {
            case GET:
                requestGet();
                break;
            case POST:
                requestPost();
                break;
        }
    }

    public enum Method {
        GET, POST
    }

    private void requestGet(){
        queue.add(new StringRequest(Request.Method.GET, baseUrl + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    onSuccess(response);
                }
                finally {
                    if (tag != null){
                        if (response != null){
                            Log.e("response",tag + "=>"+ "response=" + response);
                        }
                        else {
                            Log.e("response",tag + "=>"+ "response=" + "no data in response");
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onFail();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (tag != null && onPost() != null){
                    Log.e("response",tag + "=>"+ "url=" + url);
                    HashMap<String,String> map = onPost();
                    Iterator iterator = map.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String key = (String)entry.getKey();
                        String val = (String)entry.getValue();
                        Log.e("response",tag + "=>"+ key + "=" + val);
                    }
                }
                if (onPost() != null){
                    return onPost();
                }
                return super.getParams();
            }

            @Override
            public void setRetryPolicy(RetryPolicy retryPolicy) {
                super.setRetryPolicy(new DefaultRetryPolicy(failedTime,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            }
        });
    }

    private void requestPost(){
        queue.add(new StringRequest(Request.Method.POST,baseUrl +url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    onSuccess(response);
                }
                finally {
                    if (tag != null){
                        if (response != null){
                            Log.e("response",tag + "=>"+ "response=" + response);
                        }
                        else {
                            Log.e("response",tag + "=>"+ "response=" + "no data in response");
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onFail();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (tag != null && onPost() != null){
                    Log.e("response",tag + "=>"+ "url=" + url);
                    HashMap<String,String> map = onPost();
                    Iterator iterator = map.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String key = (String)entry.getKey();
                        String val = (String)entry.getValue();
                        Log.e("response",tag + "=>"+ key + "=" + val);
                    }
                }
                if (onPost() != null){
                    return onPost();
                }
                return super.getParams();
            }

            @Override
            public void setRetryPolicy(RetryPolicy retryPolicy) {
                super.setRetryPolicy(new DefaultRetryPolicy(failedTime,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            }
        });
    }

    protected abstract void onSuccess(String response);

    protected void onFail(){
        Toast.makeText(activity,"网络连接失败", Toast.LENGTH_SHORT).show();
    }

    protected HashMap<String, String> onPost(){
        return null;
    }

    protected void setFailedTime(int failedTime) {
        this.failedTime = failedTime;
    }

    public static void setBaseUrl(String baseUrl) {
        RequestMaker.baseUrl = baseUrl;
    }
}