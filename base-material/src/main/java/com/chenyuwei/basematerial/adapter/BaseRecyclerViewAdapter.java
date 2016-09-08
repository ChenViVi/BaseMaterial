package com.chenyuwei.basematerial.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by vivi on 2016/8/10.
 */
public abstract class BaseRecyclerViewAdapter<Item, Holder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items;
    private OnItemClickListener onItemClickListener;

    public BaseRecyclerViewAdapter(List<Item> items){
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener<Item> listener) {
        onItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        return onCreateItem(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final Item item = this.items.get(position);
        onBindViewHolder((Holder)viewHolder, position, item);
        if(onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position, item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public abstract RecyclerView.ViewHolder onCreateItem(ViewGroup parent, final int viewType);

    public abstract void onBindViewHolder(Holder viewHolder, int position, Item item);

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
        protected View findViewById(int id){
            return itemView.findViewById(id);
        }
    }

    public interface OnItemClickListener<Item> {
        void onItemClick(int position, Item item);
    }
}