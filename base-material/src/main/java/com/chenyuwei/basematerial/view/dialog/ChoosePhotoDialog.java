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

public abstract class ChoosePhotoDialog extends AlertDialog implements View.OnClickListener{

    private TextView tvCancel;
    private View loPhoto;
    private View loAlbum;

    protected ChoosePhotoDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_photo);
        tvCancel = (TextView) findViewById(R.id.tvCancel);
        loPhoto = findViewById(R.id.loPhoto);
        loAlbum = findViewById(R.id.loAlbum);
        tvCancel.setOnClickListener(this);
        loPhoto.setOnClickListener(this);
        loAlbum.setOnClickListener(this);
    }

    public abstract void onChoose(int position);

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tvCancel){
            cancel();
        }
        else if (view.getId() == R.id.loPhoto){
            onChoose(0);
        }
        else if (view.getId() == R.id.loAlbum){
            onChoose(1);
        }
    }
}
