package com.chenyuwei.basematerial.demo.fragment;


import android.view.View;
import android.widget.Button;

import com.chenyuwei.basematerial.demo.R;
import com.chenyuwei.basematerial.demo.activity.DrawLayoutActivity;
import com.chenyuwei.basematerial.demo.activity.DrawLayoutTabLayoutActivity;
import com.chenyuwei.basematerial.demo.activity.ListViewActivity;
import com.chenyuwei.basematerial.demo.activity.RecyclerViewActivity;
import com.chenyuwei.basematerial.demo.activity.TabBottomActivity;
import com.chenyuwei.basematerial.demo.activity.TabTopActivity;
import com.chenyuwei.basematerial.fragment.BaseFragment;


/**
 * Created by vivi on 2016/7/18.
 */
public class HomeFragment extends BaseFragment {

    private Button btnTabLayoutActivity;
    private Button btnDrawLayoutActivity;
    private Button btnBackToolBarActivity;
    private Button btnDrawLayoutTabLayoutActivity;
    private Button btnCollapsingToolbarLayoutActivity;
    private Button btnListViewActivity;
    private Button btnRecycleViewActivity;
    private Button btnTabBottomActivity;

    @Override
    protected int onBindView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        btnTabLayoutActivity = (Button)findViewById(R.id.btnTabLayoutActivity);
        btnDrawLayoutActivity = (Button)findViewById(R.id.btnDrawLayoutActivity);
        btnDrawLayoutTabLayoutActivity = (Button)findViewById(R.id.btnDrawLayoutTabLayoutActivity);
        btnCollapsingToolbarLayoutActivity = (Button)findViewById(R.id.btnCollapsingToolbarLayoutActivity);
        btnListViewActivity = (Button)findViewById(R.id.btnListViewActivity);
        btnRecycleViewActivity = (Button)findViewById(R.id.btnRecycleViewActivity);
        btnTabBottomActivity = (Button)findViewById(R.id.btnTabBottomActivity);
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
        }
    }
}
