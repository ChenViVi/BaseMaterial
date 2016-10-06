package com.chenyuwei.basematerial.demo.fragment;


import android.view.View;

import com.chenyuwei.basematerial.demo.R;
import com.chenyuwei.basematerial.demo.activity.DrawLayoutActivity;
import com.chenyuwei.basematerial.demo.activity.DrawLayoutTabLayoutActivity;
import com.chenyuwei.basematerial.demo.activity.ListViewActivity;
import com.chenyuwei.basematerial.demo.activity.RecyclerViewActivity;
import com.chenyuwei.basematerial.demo.activity.TabBottomActivity;
import com.chenyuwei.basematerial.demo.activity.TabTopActivity;
import com.chenyuwei.basematerial.demo.activity.X5X5WebActivity;
import com.chenyuwei.basematerial.fragment.BaseFragment;


/**
 * Created by vivi on 2016/7/18.
 */
public class HomeFragment extends BaseFragment {

    @Override
    protected int onBindView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        findViewById(R.id.btnTabLayoutActivity);
        findViewById(R.id.btnDrawLayoutActivity);
        findViewById(R.id.btnDrawLayoutTabLayoutActivity);
        findViewById(R.id.btnListViewActivity);
        findViewById(R.id.btnRecycleViewActivity);
        findViewById(R.id.btnTabBottomActivity);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btnTabLayoutActivity:
                startActivity(TabTopActivity.class);
                break;
            case R.id.btnDrawLayoutActivity:
                startActivity(DrawLayoutActivity.class);
                break;
            case R.id.btnDrawLayoutTabLayoutActivity:
                startActivity(DrawLayoutTabLayoutActivity.class);
                break;
            case R.id.btnListViewActivity:
                startActivity(ListViewActivity.class);
                break;
            case R.id.btnRecycleViewActivity:
                startActivity(RecyclerViewActivity.class);
                break;
            case R.id.btnTabBottomActivity:
                startActivity(TabBottomActivity.class);
                break;
            case R.id.btnX5WebActivity:
                startActivity(X5X5WebActivity.class);
                break;
        }
    }
}
