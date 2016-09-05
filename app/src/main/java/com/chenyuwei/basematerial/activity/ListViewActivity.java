package com.chenyuwei.basematerial.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chenyuwei.basematerial.activity.base.BaseListViewActivity;
import com.chenyuwei.basematerial.adapter.lv.ArticleAdapter;
import com.chenyuwei.basematerial.adapter.lv.base.BaseListViewAdapter;
import com.chenyuwei.basematerial.fragment.base.BaseListViewFragment;
import com.chenyuwei.basematerial.modle.Article;

import java.util.ArrayList;

/**
 * Created by vivi on 2016/9/3.
 */
public class ListViewActivity extends BaseListViewActivity<Article,ArticleAdapter>{

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
    protected ArticleAdapter setAdapter() {
        return new ArticleAdapter(data);
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
