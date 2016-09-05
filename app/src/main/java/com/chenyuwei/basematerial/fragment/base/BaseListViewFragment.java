package com.chenyuwei.basematerial.fragment.base;

import com.chenyuwei.basematerial.R;
import com.chenyuwei.basematerial.adapter.lv.base.BaseListViewAdapter;
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

    @Override
    protected int onBindView() {
        return R.layout.base_fragment_list_view;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        listView = (XListView) findViewById(R.id.listView);
        adapter = setAdapter();
        listView.setAdapter(adapter);
        setPullLoadEnable(false);
        setPullRefreshEnable(false);
        listView.setXListViewListener(this);
        clearItems();
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
