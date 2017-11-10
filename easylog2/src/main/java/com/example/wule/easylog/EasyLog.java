package com.example.wule.easylog;

import android.app.Activity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wule_ on 2017/11/10.
 */

public class EasyLog {
    private static String TAG = "= EasyLog =";
    private static boolean showIs = true;
    private static boolean timeIs = false;
    private static boolean lineIs = false;
    private static Activity sClazz = null;

    public static void setAll(boolean b) {
        timeIs = b;
        lineIs = b;
    }

    public static void setClazz(Activity clazz) {
        sClazz = clazz;
    }

    public static void setIsShow(boolean isShow) {
        showIs = isShow;
    }

    public static void setIsLine(boolean isLine) {
        lineIs = isLine;
    }

    public static void initTag() {
        TAG = "= EasyLog =";
    }

    public static void setTag(String tag) {
        TAG = tag;
    }

    public static void setIsTime(boolean isTime) {
        timeIs = isTime;
    }

    public static void i(String msg) {
        show(Log.INFO, msg);
    }

    public static void w(String msg) {
        show(Log.WARN, msg);
    }

    public static void d(String msg) {
        show(Log.DEBUG, msg);
    }

    public static void v(String msg) {
        show(Log.VERBOSE, msg);
    }

    public static void e(String msg) {
        show(Log.ERROR, msg);
    }

    public static void show(int type, String msg) {
        StringBuffer temp = new StringBuffer();
        if (!showIs) {
            return;
        }
        if (timeIs) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            temp.append(simpleDateFormat.format(date));
            temp.append(" == ");
        }
        if (lineIs) {
            if (sClazz instanceof Activity) {
                StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
                temp.append("(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")" + " == ");
            } else
                temp.append("(the activity is null)" + " == ");
        }
        temp.append(msg);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < temp.length(); i++) {
            sb.append("*");
        }
        switch (type) {
            case Log.VERBOSE:
                Log.v(TAG, sb.toString());
                Log.v(TAG, temp.toString());
                Log.v(TAG, sb.toString());
                break;
            case Log.DEBUG:
                Log.d(TAG, sb.toString());
                Log.d(TAG, temp.toString());
                Log.d(TAG, sb.toString());
                break;
            case Log.INFO:
                Log.i(TAG, sb.toString());
                Log.i(TAG, temp.toString());
                Log.i(TAG, sb.toString());
                break;
            case Log.ERROR:
                Log.e(TAG, sb.toString());
                Log.e(TAG, temp.toString());
                Log.e(TAG, sb.toString());
                break;
            case Log.WARN:
                Log.w(TAG, sb.toString());
                Log.w(TAG, temp.toString());
                Log.w(TAG, sb.toString());
                break;
            default:
                Log.v(TAG, temp.toString());
                break;
        }
    }

    public static StackTraceElement getTargetStackTraceElement() {
        StackTraceElement targetStackTrace = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String[] split = sClazz.getClass().getName().split("\\.");
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.toString().contains(split[split.length - 1])) {
                targetStackTrace = stackTraceElement;
                break;
            }
        }
        return targetStackTrace;
    }
}