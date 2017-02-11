package com.chenyuwei.basematerial.activity;

import android.os.Bundle;

import com.chenyuwei.basematerial.R;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by vivi on 2016/9/26.
 */

public class BaseX5WebActivity extends BaseActivity {

    protected WebView webView;

    @Override
    protected int onBindView() {
        return R.layout.base_activity_x5web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    protected void loadUrl(String url){
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null){
            webView.destroy();
        }
    }
}
