package com.chenyuwei.basematerial.activity.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chenyuwei.basematerial.R;

/**
 * Created by vivi on 2016/8/31.
 */
public abstract class BaseToolBarActivity extends BaseActivity {

    protected Toolbar toolbar ;

    protected abstract int onBindView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolBarUtil mBackToolBarHelper = new ToolBarUtil(this,onBindView()) ;
        toolbar = mBackToolBarHelper.getToolBar() ;
        setContentView(mBackToolBarHelper.getContentView());
        setSupportActionBar(toolbar);
        setEnableBack(false);
    }

    public void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void setEnableBack(boolean enableBack){
        getSupportActionBar().setDisplayHomeAsUpEnabled(enableBack);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }

    class ToolBarUtil {
        Context mContext;
        FrameLayout mContentView;
        View mUserView;
        Toolbar mToolBar;
        LayoutInflater mInflater;
        int[] ATTRS = {
                R.attr.windowActionBarOverlay,
                R.attr.actionBarSize
        };

        public ToolBarUtil(Context context, int layoutId) {
            this.mContext = context;
            mInflater = LayoutInflater.from(mContext);
            mContentView = new FrameLayout(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mContentView.setLayoutParams(params);
            mUserView = mInflater.inflate(layoutId, null);
            FrameLayout.LayoutParams userViewParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
            boolean overly = typedArray.getBoolean(0, false);
            int toolBarSize = (int) typedArray.getDimension(1,(int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
            typedArray.recycle();
            userViewParams.topMargin = overly ? 0 : toolBarSize;
            mContentView.addView(mUserView, userViewParams);
            View toolbar = mInflater.inflate(R.layout.base_activity_back_toolbar, mContentView);
            mToolBar = (Toolbar) toolbar.findViewById(R.id.toolbar);
        }

        public FrameLayout getContentView() {
            return mContentView;
        }

        public Toolbar getToolBar() {
            return mToolBar;
        }
    }
}




