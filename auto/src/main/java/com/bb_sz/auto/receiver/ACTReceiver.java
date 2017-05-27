package com.bb_sz.auto.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bb_sz.lib.log.L;

public class ACTReceiver extends BroadcastReceiver {

    private static final String TAG = "ACTReceiver";

    public static String act;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
//        L.d(TAG, "action:" + action);
        if ("SKY_ACTIVITY_ACTION".equals(action)) {
            act = intent.getStringExtra("act");
//            L.d(TAG, "act:" + act);
        }
    }
}
