package com.chenyuwei.basematerial.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chenyuwei.basematerial.R;

import com.chenyuwei.basematerial.adapter.BaseAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivi on 2016/9/3.
 */

public abstract class BaseRecyclerViewActivity<Item, Adapter extends BaseAdapter> extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    protected ArrayList<Item> data = new ArrayList<>();
    private Adapter adapter;
    private RefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected int onBindView() {
        return R.layout.base_activity_recycle_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = setAdapter();
        recyclerView.setLayoutManager(setLayoutManager());
        recyclerView.setAdapter(adapter);
        setPullLoadEnable(false);
        setPullRefreshEnable(false);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                Item i = (Item) item;
                BaseRecyclerViewActivity.this.onItemClick(view,  position,i);
            }
        });
    }

    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected void setPullLoadEnable(boolean enable) {
        refreshLayout.setEnableLoadMore(enable);
    }

    protected void setPullRefreshEnable(boolean enable) {
        refreshLayout.setEnableRefresh(enable);
    }

    protected void stopRefresh() {
        refreshLayout.finishRefresh();
    }

    protected void stopRefresh(int delay) {
        refreshLayout.finishRefresh(delay);
    }

    protected void stopRefresh(int delay, boolean isFinish) {
        refreshLayout.finishRefresh(delay, isFinish);
    }

    protected void stopRefresh(boolean isFinish) {
        refreshLayout.finishRefresh(isFinish);
    }

    protected void stopLoadMore() {
        refreshLayout.finishLoadMore();
    }

    protected void stopLoadMore(int delay) {
        refreshLayout.finishLoadMore(delay);
    }

    protected void stopLoadMore(int delay, boolean isFinish, boolean noMoreData) {
        refreshLayout.finishLoadMore(delay, isFinish, noMoreData);
    }

    protected void stopLoadMore(boolean isFinish) {
        refreshLayout.finishLoadMore(isFinish);
    }

    protected void notifyDataSetChanged(){
        adapter.notifyDataSetChanged();
    }

    protected void addItems(List<Item> items) {
        data.addAll(items);
    }

    protected void clearItems() {
        if (data.size() > 0) {
            data.clear();
        }
    }

    protected abstract Adapter setAdapter();

    protected void onItemClick(View view, int position, Item item){

    }
}
