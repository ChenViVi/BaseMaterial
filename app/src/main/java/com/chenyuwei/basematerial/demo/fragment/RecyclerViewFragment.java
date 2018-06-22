package com.chenyuwei.basematerial.demo.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.demo.adapter.RecyclerViewArticleAdapter;
import com.chenyuwei.basematerial.demo.modle.Article;
import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;


import java.util.ArrayList;

/**
 * Created by vivi on 2016/7/18.
 */
public class RecyclerViewFragment extends BaseRecyclerViewFragment<Article,RecyclerViewArticleAdapter> {

    ArrayList<Article> items = new ArrayList<>();

    @Override
    protected void onCreateView() {
        super.onCreateView();
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
    public void onRefresh(RefreshLayout refreshlayout) {
        super.onRefresh(refreshlayout);
        clearItems();
        addItems(items);
        stopRefresh();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        super.onLoadMore(refreshlayout);
        addItems(items);
        stopLoadMore();
    }
}
