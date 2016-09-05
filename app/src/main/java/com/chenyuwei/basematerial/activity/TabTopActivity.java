package com.chenyuwei.basematerial.activity;

import android.os.Bundle;
import com.chenyuwei.basematerial.activity.base.BaseTabTopActivity;
import com.chenyuwei.basematerial.fragment.HomeFragment;
import com.chenyuwei.basematerial.fragment.ListViewFragment;
import com.chenyuwei.basematerial.fragment.RecyclerViewFragment;

public class TabTopActivity extends BaseTabTopActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new HomeFragment(),"HomeFragment");
        addFragment(new RecyclerViewFragment(),"RecyclerViewFragment");
        addFragment(new ListViewFragment(),"ListViewFragment");
    }
}