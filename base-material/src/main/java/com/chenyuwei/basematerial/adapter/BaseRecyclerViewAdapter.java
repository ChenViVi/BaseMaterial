package com.chenyuwei.basematerial.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by vivi on 2016/8/10.
 */
public abstract class BaseRecyclerViewAdapter<Item, Holder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Activity activity;
    protected SharedPreferences preferences;
    protected List<Item> items;
    private OnItemClickListener onItemClickListener;

    public BaseRecyclerViewAdapter(Activity activity,List<Item> items){
        this.items = items;
        this.activity = activity;
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
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

    protected void toast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int id) {
        Toast.makeText(activity, activity.getResources().getString(id), Toast.LENGTH_SHORT).show();
    }

    protected void debug() {
        Toast.makeText(activity, "debug", Toast.LENGTH_SHORT).show();
    }
}
