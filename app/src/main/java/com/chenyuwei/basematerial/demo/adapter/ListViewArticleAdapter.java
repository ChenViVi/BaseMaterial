package com.chenyuwei.basematerial.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyuwei.basematerial.demo.R;
import com.chenyuwei.basematerial.adapter.BaseListViewAdapter;
import com.chenyuwei.basematerial.demo.modle.Article;

import java.util.List;

/**
 * Created by vivi on 2016/9/1.
 */
public class ListViewArticleAdapter extends BaseListViewAdapter<Article,ListViewArticleAdapter.ViewHolder> {

    public ListViewArticleAdapter(List<Article> data) {
        super(data);
    }

    @Override
    protected ViewHolder onCreateItem(ViewGroup viewGroup) {
        return  new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, Article article) {
        viewHolder.tvTitle.setText(article.getTitle());
    }

    class ViewHolder extends BaseListViewAdapter.ViewHolder{
        TextView tvTitle;
        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) findViewById(R.id.tvTitle);
        }
    }
}
