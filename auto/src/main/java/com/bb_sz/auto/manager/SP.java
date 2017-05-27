package com.bb_sz.auto.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/22.
 */

public class SP {
    private static final String TAG = SP.class.getSimpleName();

    private static final String FILE_NAME = "auto_sp";
    private static SP mInstance;
    private SharedPreferences sp;

    public static SP getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new SP();
            }
            return mInstance;
        }
    }

    public SP() {
    }

    public void init(Context context) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public boolean isFirstApp() {
        boolean res = sp.getBoolean(Contants.APP_FIRST_RUN, true);
        if (res) {
            sp.edit().putBoolean(Contants.APP_FIRST_RUN, false).apply();
        }
        return res;
    }

    public int getRunTimes() {
        return sp.getInt(Contants.KEY_RUN_TIMES, 0);
    }

    public void RunTimesAdd() {
        int times = getRunTimes() + 1;
        sp.edit().putInt(Contants.KEY_RUN_TIMES, times).apply();
    }

    public boolean getBooleanValue(String key) {
        return sp.getBoolean(key, false);
    }

    public void setStringValue(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    public String getStringValue(String key) {
        return sp.getString(key, "");
    }

    public void setIntValue(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public void setLongValue(String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public int getIntValue(String key) {
        return sp.getInt(key, 0);
    }
    public int getIntValue(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public long getLongValue(String key) {
        return sp.getLong(key, 0);
    }

    public void setBooleanValue(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public void start() {
        sp.edit().putBoolean(Contants.KEY_RUN_FLAG, true).apply();
    }
    public void stop() {
        sp.edit().putBoolean(Contants.KEY_RUN_FLAG, false).apply();
    }

    public void initInstall() {
        sp.edit().putLong(Contants.KEY_INSTALL_TIME, System.currentTimeMillis()).apply();
    }

    public long getInstallTime() {
        return sp.getLong(Contants.KEY_INSTALL_TIME, 0);
    }
}
