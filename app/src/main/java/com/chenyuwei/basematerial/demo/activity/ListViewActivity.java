package com.chenyuwei.basematerial.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chenyuwei.basematerial.activity.BaseListViewActivity;
import com.chenyuwei.basematerial.demo.adapter.ListViewArticleAdapter;
import com.chenyuwei.basematerial.demo.modle.Article;

import java.util.ArrayList;

/**
 * Created by vivi on 2016/9/3.
 */
public class ListViewActivity extends BaseListViewActivity<Article,ListViewArticleAdapter> {

    ArrayList<Article> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 20; i++){
            items.add(new Article("title"+String.valueOf(i)));
        }
        addItems(items);
        setPullRefreshEnable(true);
        setPullLoadEnable(true);
    }

    @Override
    protected ListViewArticleAdapter setAdapter() {
        return new ListViewArticleAdapter(data);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        clearItems();
        addItems(items);
        stopRefresh();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        addItems(items);
        stopLoadMore();
    }
}