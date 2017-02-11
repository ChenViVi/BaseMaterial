package com.chenyuwei.basematerial.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.chenyuwei.basematerial.R;
import com.chenyuwei.basematerial.adapter.TabViewPagerAdapter;

/**
 * Created by vivi on 2016/9/3.
 */
public class BaseTabTopActivity extends BaseActivity {

    protected Toolbar toolbar;
    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    protected TabViewPagerAdapter adapterTab;

    @Override
    protected int onBindView() {
        return R.layout.base_activity_tab_top_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        adapterTab = new TabViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterTab);
        tabLayout.setupWithViewPager(viewPager);
    }

    protected void setCurrentItem(int position){
        viewPager.setCurrentItem(position);
    }

    protected void addFragment(Fragment fragment, String title){
        adapterTab.addFragment(fragment,title);
        adapterTab.notifyDataSetChanged();
    }

    protected void addFragment(Fragment fragment, int title){
        adapterTab.addFragment(fragment,getResources().getString(title));
        adapterTab.notifyDataSetChanged();
    }
}
