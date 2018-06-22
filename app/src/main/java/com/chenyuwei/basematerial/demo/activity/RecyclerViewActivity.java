package com.chenyuwei.basematerial.demo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.demo.adapter.RecyclerViewArticleAdapter;
import com.chenyuwei.basematerial.demo.modle.Article;
import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;

/**
 * Created by vivi on 2016/9/3.
 */
public class RecyclerViewActivity extends BaseRecyclerViewActivity<Article,RecyclerViewArticleAdapter> {

    ArrayList<Article> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 40; i++){
            items.add(new Article("title"+String.valueOf(i)));
        }
        addItems(items);
        setPullRefreshEnable(true);
        setPullLoadEnable(true);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new GridLayoutManager(activity,3);
    }

    @Override
    protected RecyclerViewArticleAdapter setAdapter() {
        return new RecyclerViewArticleAdapter(activity,data);
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshlayout) {
        clearItems();
        addItems(items);
        stopRefresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        addItems(items);
        stopLoadMore();
    }
}
