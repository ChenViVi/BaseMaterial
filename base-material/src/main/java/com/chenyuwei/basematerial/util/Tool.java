package com.chenyuwei.basematerial.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    /**
     * 参考：http://www.jianshu.com/p/e8477fdccbe9
     * */
    public static boolean isMobile(String phone) {
        return !(TextUtils.isEmpty(phone)|| phone.length() != 11) && phone.matches("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$");
    }

    public static String saveImage(Activity activity,Bitmap bitmap){
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        String time = String.valueOf(System.currentTimeMillis());
        try {
            FileOutputStream outputStream = activity.openFileOutput(time+".png", Activity.MODE_WORLD_READABLE);
            outputStream.write(bStream.toByteArray());
            Log.e("output","saveImage() success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("output","saveImage() error FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("output","saveImage() error IOException");
        }
        return activity.getFilesDir() + "/" + time+".png";
    }

    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
    
    public static Bitmap resize(Bitmap bitmap, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }
}
