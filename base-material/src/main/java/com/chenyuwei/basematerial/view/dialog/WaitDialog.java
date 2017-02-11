package com.chenyuwei.basematerial.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.chenyuwei.basematerial.R;

/**
 * Created by 93294 on 2017/1/3 0003.
 */

public class WaitDialog extends ProgressDialog {

    public WaitDialog(Context context){
        super(context);
        setTitle(R.string.tvLoading);
    }

    public WaitDialog(Context context,String title){
        this(context);
        setTitle(title);
    }

    public WaitDialog(Context context,int title){
        this(context);
        setTitle(title);
    }
}
