package com.chenyuwei.basematerial.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vivi on 2016/8/5.
 */
public class Tool {

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static int getScreenHeight(Context context) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }
        return screenHeight;
    }

    public static int getScreenWidth(Context context) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }
        return screenWidth;
    }

    public static int px2dp(Context context,float value){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(value / scale + 0.5f);
    }

    public static int dp2px(Context context,float value){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(value * scale + 0.5f);
    }

    public static int px2sp(Context context,float value){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(value / fontScale + 0.5f);
    }

    public static int sp2px(Context context,float value){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(value * fontScale + 0.5f);
    }

    public static String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuilder strBuf = new StringBuilder();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e) {
            return "";
        }
        catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
