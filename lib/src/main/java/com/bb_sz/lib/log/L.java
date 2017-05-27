package com.bb_sz.lib.log;


/**
 * Created by Administrator on 2017/3/13.
 */

public class L {
    public static final boolean debug = true;
//    public static final boolean debug = true;

    public static void v(String tag, String msg) {
//        android.util.Log.d(tag, msg);
        FLog.d(tag, msg);
    }
    public static void d(String tag, String msg) {
//        android.util.Log.d(tag, msg);
        FLog.d(tag, msg);
    }

    public static void i(String tag, String msg) {
//        android.util.Log.i(tag, msg);
        FLog.i(tag, msg);
    }

    public static void w(String tag, String msg) {
//        android.util.Log.w(tag, msg);
        FLog.w(tag, msg);
    }

    public static void e(String tag, String msg) {
//        android.util.Log.e(tag, msg);
        FLog.e(tag, msg);
    }

    public static void u(String tag, String s) {
    }
}
