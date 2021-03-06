package com.bb_sz.auto.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bb_sz.auto.manager.Contants;
import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.auto.manager.SP;
import com.bb_sz.auto.system.AppHelper;
import com.bb_sz.lib.log.L;

public class BatterInfoReceiver extends BroadcastReceiver {
    private static final String TAG = "sky_BatterInfoReceiver";
    private boolean debug = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (debug) L.i(TAG, "BatterInfoReceiver action:" + action);
        if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
            BatteryInfo batteryinfos = new BatteryInfo();
            batteryinfos.m_iHealth = intent.getIntExtra("health", 0);
            batteryinfos.m_iLevel = intent.getIntExtra("level", 0);
            batteryinfos.m_iPlugged = intent.getIntExtra("plugged", 0);
            batteryinfos.m_bPresent = intent.getExtras().getBoolean("present");
            batteryinfos.m_iScale = intent.getIntExtra("scale", 0);
            batteryinfos.m_iStatus = intent.getIntExtra("status", 0);
            batteryinfos.m_szTechnology = intent.getExtras().getString("technology");
            batteryinfos.m_iTemperature = intent.getIntExtra("temperature", 0);
            batteryinfos.m_iVoltage = intent.getIntExtra("voltage", 0);
            if (debug)
                L.i(TAG, "health = " + batteryinfos.m_iHealth + ", level = " + batteryinfos.iGetLevel() + ", m_iTemperature = " + ((float) batteryinfos.m_iTemperature * 0.1));
            if (batteryinfos.m_iHealth != 2 || batteryinfos.iGetLevel() < 5 || ((float) batteryinfos.m_iTemperature * 0.1) > 47) {
                stopMainRun(context);
                closeAllOtherApp(context);
//                ScreenHelper.getInstance().lockScreen(context);
            } else if (batteryinfos.m_iHealth == 2 && batteryinfos.iGetLevel() > 15 && ((float) batteryinfos.m_iTemperature * 0.1) < 35){
//                ScreenHelper.getInstance().unlockScreen(context);
                startMainRun(context);
            }
        } else if (Intent.ACTION_BATTERY_LOW.equals(action)) {
            stopMainRun(context);
        } else if (Intent.ACTION_BATTERY_OKAY.equals(action)) {
//            startMainRun(context);
        }
    }

    private void closeAllOtherApp(Context context) {
        AppHelper.getInstance().closeAllOtherApp(context);
    }

    private void startMainRun(Context context) {
        if (debug) L.i(TAG, "startMainRun");
        if (!isRunning(context)) {
            if (debug) L.i(TAG, "startMainRun, not is running, start run.");
            RunManager.getInstance().start();
            RunManager.getInstance().start(context);
        } else {
            if (debug) L.i(TAG, "startMainRun, is running, return");
        }
    }

    private boolean isRunning(Context context) {
        if (debug) L.i(TAG, "isRunning");
        return SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
    }

    private void stopMainRun(Context context) {
        RunManager.getInstance().stop();
    }
}
