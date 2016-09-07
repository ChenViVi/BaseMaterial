package com.chenyuwei.basematerial.demo.activity;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.chenyuwei.basematerial.demo.R;
import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.basematerial.fragment.BaseDrawerFragment;

public class DrawLayoutActivity extends BaseActivity {

    private Toolbar toolbar;
    private BaseDrawerFragment drawerFragment;

    @Override
    protected int onBindView() {
        return R.layout.activity_draw_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerFragment = (BaseDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        setSupportActionBar(toolbar);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }
}
