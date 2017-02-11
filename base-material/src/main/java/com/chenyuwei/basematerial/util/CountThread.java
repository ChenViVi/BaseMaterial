package com.chenyuwei.basematerial.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Created by vivi on 2016/11/9.
 */

public abstract class CountThread implements Runnable{

    protected Activity activity;
    private static CountHandler handler;
    private boolean isFinish = false;

    public CountThread(Activity activity, long count){
        this.activity = activity;
        activity.isFinishing();
        handler = new CountHandler(count) {
            @Override
            protected void onCount(long count) {
                CountThread.this.onCount(count);
            }

            @Override
            protected void onFinish() {
                CountThread.this.onFinish();
            }
        };
        Context context;
    }

    public CountThread(Activity activity, long count,long speed){
        this.activity = activity;
        activity.isFinishing();
        handler = new CountHandler(count,speed) {
            @Override
            protected void onCount(long count) {
                CountThread.this.onCount(count);
            }

            @Override
            protected void onFinish() {
                CountThread.this.onFinish();
            }
        };
        Context context;
    }

    public void start(){
        new Thread(this).start();
    }

    public void stop(){
        isFinish = true;
    }

    @Override
    public void run() {
        while (!activity.isFinishing() && !isFinish) {
            try {
                Thread.sleep(1000);
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void onCount(long count);

    protected void onFinish(){}

    abstract class CountHandler extends Handler {

        private long count;
        private long speed = 1;

        CountHandler(long count){
            this.count = count;
        }

        CountHandler(long count,long speed){
            this.count = count;
            this.speed = speed;
        }

        protected abstract void onCount(long count);

        protected abstract void onFinish();

        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    count = count - speed;
                    if (count >= 0){
                        onCount(count);
                    }else {
                        CountThread.this.isFinish = true;
                        onFinish();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
