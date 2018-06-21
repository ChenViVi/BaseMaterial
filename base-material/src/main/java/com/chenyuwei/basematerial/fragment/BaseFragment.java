package com.chenyuwei.basematerial.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by vivi on 2016/7/18.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected AppCompatActivity activity;
    protected View rootView;
    protected SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(onBindView(),null);
        onCreateView();
        return rootView;
    }

    protected void onCreateView(){}

    protected  <T extends View> T  findViewById(int id){
        T view = rootView.findViewById(id);
        if (!(rootView.findViewById(id) instanceof AdapterView)){
            view.setOnClickListener(this);
        }
        return view;
    }

    protected void toast(String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int id){
        Toast.makeText(activity, getResources().getString(id), Toast.LENGTH_SHORT).show();
    }

    protected void debug() {
        Toast.makeText(activity, "debug", Toast.LENGTH_SHORT).show();
    }

    protected void startActivity(Class<?> cls){
        startActivity(new Intent(activity, cls));
    }

    protected abstract int onBindView();

    @Override
    public void onClick(View view) {

    }
}
