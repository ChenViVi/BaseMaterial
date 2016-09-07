package com.chenyuwei.basematerial.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chenyuwei.basematerial.R;
import com.chenyuwei.basematerial.adapter.BaseListViewAdapter;
import com.chenyuwei.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivi on 2016/9/3.
 */
public abstract class BaseListViewFragment<Item,Adapter extends BaseListViewAdapter> extends BaseFragment implements XListView.IXListViewListener{

    protected ArrayList<Item> data = new ArrayList<>();
    private Adapter adapter;
    private XListView listView;
    private Toolbar toolbar;
    private TextView tvTitle;

    @Override
    protected int onBindView() {
        return R.layout.base_fragment_list_view;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        listView = (XListView) findViewById(R.id.listView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        enableToolBar(false);
        adapter = setAdapter();
        listView.setAdapter(adapter);
        setPullLoadEnable(false);
        setPullRefreshEnable(false);
        listView.setXListViewListener(this);
        clearItems();
    }

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

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    protected void setPullLoadEnable(boolean enable){
        listView.setPullLoadEnable(enable);
    }

    protected void setPullRefreshEnable(boolean enable){
        listView.setPullRefreshEnable(enable);
    }

    protected void stopRefresh(){
        listView.stopRefresh();
    }

    protected void stopLoadMore(){
        listView.stopLoadMore();
    }

    protected void addItems(List<Item> items){
        data.addAll(items);
        adapter.notifyDataSetChanged();
    }

    protected void clearItems(){
        if (data.size() > 0){
            data.clear();
            adapter.notifyDataSetChanged();
        }
    }

    protected abstract Adapter setAdapter();

    protected void setOnItemClickListener(BaseListViewAdapter.OnItemClickListener<Item> listener){
        adapter.setOnItemClickListener(listener);
    }
}
