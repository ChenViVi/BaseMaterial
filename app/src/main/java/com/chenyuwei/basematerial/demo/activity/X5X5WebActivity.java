package com.chenyuwei.basematerial.demo.activity;

import android.os.Bundle;

import com.chenyuwei.basematerial.activity.BaseX5WebActivity;

public class X5X5WebActivity extends BaseX5WebActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl("http://wap.baidu.com");
    }
}
