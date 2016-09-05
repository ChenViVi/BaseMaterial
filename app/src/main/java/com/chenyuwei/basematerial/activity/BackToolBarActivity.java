package com.chenyuwei.basematerial.activity;

import android.os.Bundle;

import com.chenyuwei.basematerial.R;
import com.chenyuwei.basematerial.activity.base.BaseToolBarActivity;


public class BackToolBarActivity extends BaseToolBarActivity {

    @Override
    protected int onBindView() {
        return R.layout.activity_back_tool_bar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnableBack(true);
        setTitle("2333");
    }
}
