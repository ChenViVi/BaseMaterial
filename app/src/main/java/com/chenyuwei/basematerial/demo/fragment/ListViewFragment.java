package com.chenyuwei.basematerial.demo.fragment;

import com.chenyuwei.basematerial.demo.adapter.ListViewArticleAdapter;
import com.chenyuwei.basematerial.demo.modle.Article;
import com.chenyuwei.basematerial.fragment.BaseListViewFragment;

import java.util.ArrayList;

/**
 * Created by vivi on 2016/7/18.
 */
public class ListViewFragment extends BaseListViewFragment<Article,ListViewArticleAdapter> {

    ArrayList<Article> items = new ArrayList<>();

    @Override
    protected void onCreateView() {
        super.onCreateView();
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
