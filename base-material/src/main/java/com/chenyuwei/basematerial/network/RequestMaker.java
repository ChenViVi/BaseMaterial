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
import com.chenyuwei.basematerial.BaseApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vivi on 2016/8/31.
 */
public abstract class RequestMaker {

    private static String baseUrl = "";
    protected Activity activity;
    protected String tag;
    private int failedTime = 5000;
    private RequestQueue queue;
    private HashMap<String,String> map = new HashMap<>();

    public RequestMaker(Activity activity, Method method,String url) {
        this.activity = activity;
        queue = ((BaseApplication) activity.getApplication()).getQueue();
        setParams(map);
        switch(method) {
            case GET:
                requestGet(url);
                break;
            case POST:
                requestPost(url);
                break;
        }
    }

    public RequestMaker(Activity activity,Method method,String url,String tag) {
        this.activity = activity;
        this.tag = tag;
        queue = ((BaseApplication) activity.getApplication()).getQueue();
        setParams(map);
        switch(method) {
            case GET:
                requestGet(url);
                break;
            case POST:
                requestPost(url);
                break;
        }
    }

    public enum Method {
        GET, POST
    }

    private void requestGet(String url){
        final StringBuilder builderUrl = new StringBuilder();
        builderUrl.append(url);
        if (map.size() != 0){
            builderUrl.append("?");
            for (Object object : map.entrySet()) {
                Map.Entry entry = (Map.Entry) object;
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                builderUrl.append(key);
                builderUrl.append("=");
                builderUrl.append(val);
                builderUrl.append("&");
                if (tag != null){
                    Log.e("response", tag + "=>" + "param: " + key + "=" + val);
                }
            }
            builderUrl.deleteCharAt(builderUrl.length()-1);
        }
        queue.add(new StringRequest(Request.Method.GET, baseUrl + builderUrl.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                int state = 0;
                String data = null;
                try {
                    JSONObject response= new JSONObject(s);
                    state = response.getInt("state");
                    if (state == 0){
                        onError(response.getInt("code"),response.getString("error_msg"));
                    }
                    else if (state == 1){
                        data =  response.getString("data");
                        onSuccess(data);
                    }
                    else {
                        Toast.makeText(activity, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    if (tag != null){
                        Log.e("response",tag + "=>"+ "url=" + baseUrl + builderUrl.toString());
                        Log.e("response",tag + "=>"+ "response=" + s);
                        Log.e("response",tag + "=>"+ "state=" + state);
                        if (data != null){
                            Log.e("response",tag + "=>"+ "data=" + data);
                        }
                        else {
                            Log.e("response",tag + "=>"+ "data=" + "no data in response");
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
            public void setRetryPolicy(RetryPolicy retryPolicy) {
                super.setRetryPolicy(new DefaultRetryPolicy(failedTime,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            }
        });
    }

    private void requestPost(final String url){
        queue.add(new StringRequest(Request.Method.POST,baseUrl +url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                int state = 0;
                String data = null;
                try {
                    JSONObject response= new JSONObject(s);
                    state = response.getInt("state");
                    if (state == 0){
                        onError(response.getInt("code"),response.getString("error_msg"));
                    }
                    else if (state == 1){
                        data =  response.getString("data");
                        onSuccess(data);
                    }
                    else {
                        Toast.makeText(activity, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    if (tag != null){
                        StringBuilder builderUrl = new StringBuilder();
                        builderUrl.append(url);
                        if (map.size() != 0){
                            builderUrl.append("?");
                            for (Object object : map.entrySet()) {
                                Map.Entry entry = (Map.Entry) object;
                                String key = (String) entry.getKey();
                                String val = (String) entry.getValue();
                                builderUrl.append(key);
                                builderUrl.append("=");
                                builderUrl.append(val);
                                builderUrl.append("&");
                                Log.e("response", tag + "=>" + "param: " + key + "=" + val);
                            }
                            builderUrl.deleteCharAt(builderUrl.length()-1);
                        }
                        Log.e("response",tag + "=>"+ "url=" + baseUrl + builderUrl.toString());
                        Log.e("response",tag + "=>"+ "response=" + s);
                        Log.e("response",tag + "=>"+ "state=" + state);
                        if (data != null){
                            Log.e("response",tag + "=>"+ "data=" + data);
                        }
                        else {
                            Log.e("response",tag + "=>"+ "data=" + "no data in response");
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
                return map;
            }

            @Override
            public void setRetryPolicy(RetryPolicy retryPolicy) {
                super.setRetryPolicy(new DefaultRetryPolicy(failedTime,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            }
        });
    }

    protected abstract void onSuccess(String response) throws JSONException;

    protected void onFail(){
        Toast.makeText(activity,"网络连接失败", Toast.LENGTH_SHORT).show();
    }

    protected void onError(int code,String message){
        Toast.makeText(activity,message, Toast.LENGTH_SHORT).show();
    }

    protected void setParams(HashMap<String,String> map){

    }

    protected void setFailedTime(int failedTime) {
        this.failedTime = failedTime;
    }

    public static void setBaseUrl(String baseUrl) {
        RequestMaker.baseUrl = baseUrl;
    }
}