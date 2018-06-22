package com.chenyuwei.basematerial.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by super南仔 on 07/28/16.
 * blog: http://supercwn.github.io/
 * GitHub: https://github.com/supercwn
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    /**
     * Base config
     */
    public List<T> mData;
    protected Activity activity;
    private LayoutInflater mInflater;

    /**
     * Listener
     */
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnRecyclerViewItemChildClickListener mChildClickListener;
    private OnRecyclerViewItemChildLongClickListener mChildLongClickListener;

    public BaseAdapter(Activity activity, List<T> data) {
        mData = null == data ? new ArrayList<T>() : data;
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    protected void toast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int id) {
        Toast.makeText(activity, activity.getResources().getString(id), Toast.LENGTH_SHORT).show();
    }

    protected void startActivity(Class<?> cls) {
        activity.startActivity(new Intent(activity, cls));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = new BaseViewHolder(mInflater.inflate(getItemViewLayoutId(viewType),
                parent, false), activity);
        initItemClickListener(baseViewHolder);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, getItem(position), position);
    }

    /**
     * init the baseViewHolder to register mOnItemClickListener and mOnItemLongClickListener
     * @param holder
     */
    protected final void initItemClickListener(final BaseViewHolder holder) {
        if (null != mOnItemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(view, mData.get(position), position);
                }
            });
        }

        if (null != mOnItemLongClickListener) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int position = holder.getAdapterPosition();
                    mOnItemLongClickListener.onItemLongClick(v, mData.get(position), position);
                    return true;
                }
            });
        }
    }

    /**
     * Base api
     */
    protected abstract void convert(BaseViewHolder holder, T item, int position);

    protected abstract int getItemViewLayoutId(int viewType);

    protected T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**
     * Listener api
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * Register a callback to be invoked when childView in this AdapterView has
     * been clicked and held
     * {@link OnRecyclerViewItemChildClickListener}
     *
     * @param childClickListener The callback that will run
     */
    public void setOnItemChildClickListener(OnRecyclerViewItemChildClickListener childClickListener) {
        this.mChildClickListener = childClickListener;
    }

    public class OnItemChildClickListener implements View.OnClickListener {
        public RecyclerView.ViewHolder mViewHolder;

        @Override
        public void onClick(View v) {
            if (mChildClickListener != null)
                mChildClickListener.onItemChildClick(BaseAdapter.this, v, mViewHolder.getLayoutPosition());
        }
    }

    /**
     * Register a callback to be invoked when childView in this AdapterView has
     * been longClicked and held
     * {@link OnRecyclerViewItemChildLongClickListener}
     *
     * @param childLongClickListener The callback that will run
     */
    public void setOnItemChildLongClickListener(OnRecyclerViewItemChildLongClickListener childLongClickListener) {
        this.mChildLongClickListener = childLongClickListener;
    }

    public class OnItemChildLongClickListener implements View.OnLongClickListener {
        public RecyclerView.ViewHolder mViewHolder;

        @Override
        public boolean onLongClick(View v) {
            if (mChildLongClickListener != null) {
                return mChildLongClickListener.onItemChildLongClick(BaseAdapter.this, v, mViewHolder.getLayoutPosition());
            }
            return false;
        }
    }

    /**
     * Some interface
     */
    public interface OnItemClickListener<T> {
        void onItemClick(View view, T item, int position);
    }
    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T item, int position);
    }

    public interface OnRecyclerViewItemChildClickListener {
        void onItemChildClick(BaseAdapter adapter, View view, int position);
    }

    public interface OnRecyclerViewItemChildLongClickListener {
        boolean onItemChildLongClick(BaseAdapter adapter, View view, int position);
    }
}