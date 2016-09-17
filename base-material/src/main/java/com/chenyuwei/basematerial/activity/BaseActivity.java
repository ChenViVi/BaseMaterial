package com.chenyuwei.basematerial.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chenyuwei.basematerial.BaseApplication;

/**
 * Created by vivi on 2016/8/31.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected BaseActivity activity;
    protected View rootView;
    protected RequestQueue appQueue;
    protected RequestQueue queue;
    protected SharedPreferences preferences;

    protected abstract int onBindView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        rootView = View.inflate(activity,onBindView(), null);
        setContentView(rootView);
        appQueue = Volley.newRequestQueue(activity);
        queue = ((BaseApplication)activity.getApplication()).getQueue();
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    protected void toast(String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected void startActivity(Class<?> cls){
        startActivity(new Intent(activity, cls));
    }

    public View findViewById(int id){
        View view = rootView.findViewById(id);
        if (!(rootView.findViewById(id) instanceof AdapterView)){
            view.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
