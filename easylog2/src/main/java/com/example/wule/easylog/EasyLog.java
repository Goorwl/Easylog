package com.example.wule.easylog;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by wule_ on 2017/11/10.
 */

public class EasyLog {
    private static String TAG = "= EasyLog =";
    private static HashSet<Object> used = new HashSet<>();
    private static boolean showIs = true;
    private static boolean timeIs = false;
    private static boolean lineIs = false;
    private static Object sClazz = null;

    public static void setAll(boolean b) {
        timeIs = b;
        lineIs = b;
    }

    public static void setClazz(Object clazz) {
        sClazz = clazz;
        used.add(clazz);
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

    public static void auto(Object a) {
        timeIs = true;
        lineIs = true;
        sClazz = a;
        used.add(a);
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
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement(sClazz);
            if (targetStackTraceElement != null) {
                temp.append("(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")" + " == ");
            } else {
                for (Object a : used) {
                    StackTraceElement targetStackTraceElement1 = getTargetStackTraceElement(a);
                    if (targetStackTraceElement1 != null) {
                        temp.append("(" + targetStackTraceElement1.getFileName() + ":"
                                + targetStackTraceElement1.getLineNumber() + ")" + " == ");
                    }
                }
            }
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

    public static StackTraceElement getTargetStackTraceElement(Object clazz) {
        StackTraceElement targetStackTrace = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String[] split = clazz.getClass().getName().split("\\.");
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.toString().contains(split[split.length - 1])) {
                targetStackTrace = stackTraceElement;
                break;
            }
        }
        return targetStackTrace;
    }
}