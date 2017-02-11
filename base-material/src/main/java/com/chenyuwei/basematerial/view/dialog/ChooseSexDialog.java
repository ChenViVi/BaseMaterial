package com.chenyuwei.basematerial.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chenyuwei.basematerial.R;

/**
 * Created by 93294 on 2017/1/6 0006.
 */

public abstract class ChooseSexDialog extends AlertDialog implements View.OnClickListener{

    private TextView tvCancel;
    private View loSexMale;
    private View loSexFemale;

    protected ChooseSexDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_sex);
        tvCancel = (TextView) findViewById(R.id.tvCancel);
        loSexMale = findViewById(R.id.loSexMale);
        loSexFemale = findViewById(R.id.loSexFemale);
        tvCancel.setOnClickListener(this);
        loSexMale.setOnClickListener(this);
        loSexFemale.setOnClickListener(this);
    }

    public abstract void onChoose(int position);

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tvCancel){
            cancel();
        }
        else if (view.getId() == R.id.loSexMale){
            onChoose(0);
        }
        else if (view.getId() == R.id.loSexFemale){
            onChoose(1);
        }
    }
}
