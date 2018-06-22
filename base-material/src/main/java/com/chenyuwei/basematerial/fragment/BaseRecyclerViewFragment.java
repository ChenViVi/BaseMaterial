package com.chenyuwei.basematerial.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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
public abstract class BaseRecyclerViewFragment<Item,Adapter extends BaseAdapter> extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    protected ArrayList<Item> data = new ArrayList<>();
    private Adapter adapter;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private Toolbar toolbar;
    private TextView tvTitle;

    @Override
    protected int onBindView() {
        return R.layout.base_fragment_recycle_view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = setAdapter();
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        enableToolBar(false);
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
                BaseRecyclerViewFragment.this.onItemClick(view,  position,i);
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {

    }

    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected void enableToolBar(boolean enable){
        if (enable){
            toolbar.setVisibility(View.VISIBLE);
        }
        else {
            toolbar.setVisibility(View.GONE);
        }
    }

    protected void setTitle(String title){
        enableToolBar(true);
        tvTitle.setText(title);
    }

    protected void setTitle(int titleId){
        setTitle(getResources().getString(titleId));
    }

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

    protected void clearItems(){
        if (data.size() > 0){
            data.clear();
        }
    }

    protected abstract Adapter setAdapter();

    protected void onItemClick(View view,int position, Item item){

    }
}