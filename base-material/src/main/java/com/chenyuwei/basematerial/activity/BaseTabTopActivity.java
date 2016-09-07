package com.chenyuwei.basematerial.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.chenyuwei.basematerial.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivi on 2016/9/3.
 */
public class BaseTabTopActivity extends BaseActivity {

    protected Toolbar toolbar;
    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    protected TabAdapter adapterTab;

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
        adapterTab = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterTab);
        tabLayout.setupWithViewPager(viewPager);
    }

    protected void addFragment(Fragment fragment, String title){
        adapterTab.addFragment(fragment,title);
        adapterTab.notifyDataSetChanged();
    }

    class TabAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public TabAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
