package com.chenyuwei.basematerial.fragment;

import com.chenyuwei.basematerial.adapter.lv.ArticleAdapter;
import com.chenyuwei.basematerial.fragment.base.BaseListViewFragment;
import com.chenyuwei.basematerial.modle.Article;

import java.util.ArrayList;

/**
 * Created by vivi on 2016/7/18.
 */
public class ListViewFragment extends BaseListViewFragment<Article,ArticleAdapter> {

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
