package com.chenyuwei.basematerial.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vivi on 2016/9/4.
 */
public abstract class DownlandDialog extends ProgressDialog {

    private int progress = 0;
    private boolean cancelUpdate = false;
    private static final int DOWNLOAD = 1;
    private static final int DOWNLOAD_FINISH = 2;
    private File file;


    public DownlandDialog(Context context, String downlandUrl, File file) {
        super(context);
        this.file = file;
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        setMessage("请稍等...");
        setTitle("下载中");
        setProgress(0);
        setMax(100);
        setCanceledOnTouchOutside(false);
        if (file.exists()){
            onExists(file);
        }
        else {
            show();
            new DownloadThread(downlandUrl,file).start();
        }
    }

    public abstract void onFinish(File file);

    protected  void onFail(File file){
        Toast.makeText(getContext(), "下载失败", Toast.LENGTH_SHORT).show();
    }

    protected  void onCancel(File file){
        Toast.makeText(getContext(), "下载取消", Toast.LENGTH_SHORT).show();
    }

    protected  void onExists(File file){
        Toast.makeText(getContext(), "文件已存在", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOnCancelListener(OnCancelListener listener) {
        super.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                DownlandDialog.this.dismiss();
                cancelUpdate = true;
                if (file.exists()){
                    file.delete();
                }
                DownlandDialog.this.onCancel(file);
            }
        });
    }

    private class DownloadThread extends Thread {

        private String downlandUrl;
        private File file;

        public DownloadThread(String downlandUrl,File file){
            this.downlandUrl = downlandUrl;
            this.file = file;
        }

        @Override
        public void run() {
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    URL url = new URL(downlandUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    File fileDir = new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf("/")));
                    if (!fileDir.exists()) {
                        fileDir.mkdir();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    int count = 0;
                    byte buf[] = new byte[1024];
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);
                    fos.close();
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                dismiss();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private  Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD:
                    setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    dismiss();
                    cancelUpdate = true;
                    if (file.exists()){
                        onFinish(file);
                    }
                    else {
                        onFail(file);
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
