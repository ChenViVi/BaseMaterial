package com.chenyuwei.basematerial.demo.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.demo.R;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;
import com.chenyuwei.basematerial.demo.activity.CollapsingToolbarLayoutActivity;
import com.chenyuwei.basematerial.demo.adapter.RecycleViewArticleAdapter;
import com.chenyuwei.basematerial.demo.modle.Article;
import com.chenyuwei.basematerial.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by vivi on 2016/7/18.
 */
public class RecyclerViewFragment extends BaseFragment {

    private ArrayList<Article> data = new ArrayList<>();
    private RecycleViewArticleAdapter adapter;

    private RecyclerView rvArticle;

    @Override
    protected int onBindView() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    protected void onCreateView() {
        rvArticle = (RecyclerView) findViewById(R.id.rvArticle);
        rvArticle.setLayoutManager(new LinearLayoutManager(activity));
        for (int i = 0; i < 20; i++){
            data.add(new Article("title"+String.valueOf(i)));
        }
        adapter = new RecycleViewArticleAdapter(data);
        rvArticle.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<Article>() {
            @Override
            public void onItemClick(int position, Article article) {
                startActivity(CollapsingToolbarLayoutActivity.class);
                toast(article.getTitle());
            }
        });
    }
}
