package com.chenyuwei.basematerial.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.chenyuwei.basematerial.BaseApplication;

/**
 * Created by vivi on 2016/8/31.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected BaseActivity activity;
    protected View rootView;
    protected SharedPreferences preferences;
    protected Toolbar toolbar;

    protected abstract int onBindView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        ((BaseApplication)getApplication()).addActivity(activity);
        rootView = View.inflate(activity, onBindView(), null);
        setContentView(rootView);
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    protected void setSupportActionBar(int id) {
        toolbar = (Toolbar) (findViewById(id));
        setSupportActionBar(toolbar);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (toolbar != null){
            toolbar.setTitle(title);
        }
    }

    @Override
    public void setTitle(int title) {
        super.setTitle(title);
        if (toolbar != null){
            toolbar.setTitle(title);
        }
    }

    protected void setDisplayHomeAsUpEnabled(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }

    protected void toast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int id) {
        Toast.makeText(activity, getResources().getString(id), Toast.LENGTH_SHORT).show();
    }

    protected void debug() {
        Toast.makeText(activity, "debug", Toast.LENGTH_SHORT).show();
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(activity, cls));
    }

    public  <T extends View> T  findViewById(int id) {
        T view = rootView.findViewById(id);
        if (!(rootView.findViewById(id) instanceof AdapterView)) {
            view.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((BaseApplication)getApplication()).removeActivity(activity);
    }

    public void exitAllActivities(){
        ((BaseApplication)getApplication()).exitAllActivities();
    }
}
