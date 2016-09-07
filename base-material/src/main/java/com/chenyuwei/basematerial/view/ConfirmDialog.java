package com.chenyuwei.basematerial.view;

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
    private TextView tvNotice;
    private Button btnConfirm;
    private Button btnCancel;

    public ConfirmDialog(Context context,String message) {
        super(context);
        this.message = message;
        setCancelable(false);
    }

    public abstract void onConfirm();

    public abstract void onCancel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_confirm);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        tvNotice = (TextView) findViewById(R.id.tvNotice);
        tvNotice.setText(message);
        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnCancel) {
            dismiss();
        } else if (i == R.id.btnConfirm) {
            onConfirm();
            dismiss();
        }
    }
}