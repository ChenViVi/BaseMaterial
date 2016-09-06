package com.chenyuwei.basematerial.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chenyuwei.basematerial.R;
import com.chenyuwei.basematerial.activity.base.BaseTabBottomActivity;
import com.chenyuwei.basematerial.fragment.HomeFragment;
import com.chenyuwei.basematerial.fragment.ListViewFragment;
import com.chenyuwei.basematerial.fragment.RecyclerViewFragment;

/**
 * Created by vivi on 2016/9/6.
 */
public class TabBottomActivity extends BaseTabBottomActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(new HomeFragment(),"HomeFragment",R.mipmap.ic_launcher);
        addFragment(new RecyclerViewFragment(),"RecyclerViewFragment",R.mipmap.ic_launcher);
        addFragment(new ListViewFragment(),"ListViewFragment",R.mipmap.ic_launcher);
        initialise();
    }
}
