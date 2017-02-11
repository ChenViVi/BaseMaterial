package com.chenyuwei.basematerial.demo.activity;

import android.os.Bundle;

import com.chenyuwei.basematerial.demo.fragment.HomeFragment;
import com.chenyuwei.basematerial.demo.fragment.RecyclerViewFragment;
import com.chenyuwei.basematerial.activity.BaseTabTopActivity;

public class TabTopActivity extends BaseTabTopActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new HomeFragment(),"HomeFragment");
        addFragment(new RecyclerViewFragment(),"RecyclerViewFragment");
    }
}