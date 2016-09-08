package com.chenyuwei.basematerial.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyuwei.basematerial.demo.R;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;
import com.chenyuwei.basematerial.demo.modle.Article;

import java.util.List;

/**
 * Created by vivi on 2016/8/10.
 */
public class RecyclerViewArticleAdapter extends BaseRecyclerViewAdapter<Article,RecyclerViewArticleAdapter.ViewHolder> {

    public RecyclerViewArticleAdapter(List<Article> data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItem(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, Article article) {
        viewHolder.tvTitle.setText(article.getTitle());
    }

    class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder {
        TextView tvTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) findViewById(R.id.tvTitle);
        }
    }
}