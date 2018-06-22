package com.chenyuwei.basematerial.demo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyuwei.basematerial.adapter.BaseAdapter;
import com.chenyuwei.basematerial.adapter.BaseViewHolder;
import com.chenyuwei.basematerial.demo.R;
import com.chenyuwei.basematerial.demo.modle.Article;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;

import java.util.List;

/**
 * Created by vivi on 2016/8/10.
 */
public class RecyclerViewArticleAdapter extends BaseAdapter<Article> {


    public RecyclerViewArticleAdapter(Activity activity, List<Article> data) {
        super(activity, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Article item, int position) {
        holder.setText(R.id.tvTitle, item.getTitle());
    }

    @Override
    protected int getItemViewLayoutId(int viewType) {
        return R.layout.item_article;
    }
}