package com.chenyuwei.basematerial.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
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

    public static boolean isMobile(String phone) {
       /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phone)) return false;
        else return phone.matches(telRegex);
    }

    public static String saveImage(Context context,Bitmap bitmap){
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        String time = String.valueOf(System.currentTimeMillis());
        try {
            FileOutputStream outputStream = context.openFileOutput(time+".png", Activity.MODE_WORLD_READABLE);
            outputStream.write(bStream.toByteArray());
            Log.e("output","saveImage() success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("output","saveImage() error FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("output","saveImage() error IOException");
        }
        return context.getFilesDir() + "/" + time+".jpg";
    }
}
