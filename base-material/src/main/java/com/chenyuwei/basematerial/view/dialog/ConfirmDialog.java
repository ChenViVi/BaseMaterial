package com.chenyuwei.basematerial.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chenyuwei.basematerial.R;

/**
 * Created by vivi on 2016/7/27.
 */
public abstract class ConfirmDialog extends AlertDialog implements View.OnClickListener{

    private String message;
    private String confirm;
    private String cancel;
    private TextView tvNotice;
    private Button btnConfirm;
    private Button btnCancel;

    public ConfirmDialog(Context context,String message) {
        super(context);
        this.message = message;
        setCancelable(false);
    }

    public ConfirmDialog(Context context,int message){
        this(context,context.getResources().getString(message));
    }

    public ConfirmDialog(Context context,int message,int confirm,int cancel){
        this(context,context.getResources().getString(message));
        this.confirm = context.getResources().getString(confirm);
        this.cancel = context.getResources().getString(cancel);
    }

    public abstract void onConfirm();

    public abstract void onCancel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        tvNotice = (TextView) findViewById(R.id.tvNotice);
        tvNotice.setText(message);
        if (confirm !=  null){
            btnConfirm.setText(confirm);
        }
        if (cancel != null){
            btnCancel.setText(cancel);
        }
        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnCancel) {
            onCancel();
            dismiss();
        } else if (i == R.id.btnConfirm) {
            onConfirm();
            dismiss();
        }
    }
}