package com.chenyuwei.basematerial.demo.activity;

import android.os.Bundle;

import com.chenyuwei.basematerial.activity.BaseTabTopActivity;
import com.chenyuwei.basematerial.demo.fragment.HomeFragment;
import com.chenyuwei.basematerial.demo.fragment.ListViewFragment;
import com.chenyuwei.basematerial.demo.fragment.RecyclerViewFragment;

public class TabTopActivity extends BaseTabTopActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new HomeFragment(),"HomeFragment");
        addFragment(new RecyclerViewFragment(),"RecyclerViewFragment");
        addFragment(new ListViewFragment(),"ListViewFragment");
    }
}