package com.chenyuwei.basematerial.activity;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;

import com.chenyuwei.basematerial.R;
import com.chenyuwei.basematerial.activity.base.BaseTabTopActivity;
import com.chenyuwei.basematerial.fragment.HomeFragment;
import com.chenyuwei.basematerial.fragment.ListViewFragment;
import com.chenyuwei.basematerial.fragment.RecyclerViewFragment;
import com.chenyuwei.basematerial.fragment.base.BaseDrawerFragment;

public class DrawLayoutTabLayoutActivity extends BaseTabTopActivity {


    private BaseDrawerFragment drawerFragment;

    @Override
    protected int onBindView() {
        return R.layout.activity_draw_layout_tab_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawerFragment = (BaseDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        addFragment(new HomeFragment(),"HomeFragment");
        addFragment(new RecyclerViewFragment(),"RecyclerViewFragment");
        addFragment(new ListViewFragment(),"ListViewFragment");
    }
}