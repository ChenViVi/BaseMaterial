package com.chenyuwei.basematerial.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by vivi on 2016/9/1.
 */
public abstract class BaseListViewAdapter<Item, Holder extends BaseListViewAdapter.ViewHolder> extends BaseAdapter {

    protected List<Item> data;
    private OnItemClickListener onItemClickListener;

    public BaseListViewAdapter(List<Item> data){
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener<Item> listener) {
        onItemClickListener = listener;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Item getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Holder viewHolder;
        if (view == null){
            viewHolder = onCreateItem(viewGroup);
            view = viewHolder.itemView;
            view.setTag(viewHolder);
            if(onItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(i, getItem(i));
                    }
                });
            }
        }
        else {
            viewHolder = (Holder) view.getTag();
        }
        onBindViewHolder(viewHolder,i,getItem(i));
        return viewHolder.itemView;
    }
    protected abstract Holder onCreateItem(ViewGroup viewGroup);

    public abstract void onBindViewHolder(Holder viewHolder, int position, Item item);

    public class ViewHolder{
        View itemView;
        public ViewHolder(View itemView){
            this.itemView = itemView;
        }
        protected View findViewById(int id){
            return itemView.findViewById(id);
        }
    }

    public interface OnItemClickListener<Item> {
        void onItemClick(int position, Item item);
    }
}
