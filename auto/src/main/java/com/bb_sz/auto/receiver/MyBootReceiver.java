package com.bb_sz.auto.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bb_sz.auto.RunActivity;
import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.lib.log.L;

/**
 * Created by Administrator on 2017/4/13.
 */

public class MyBootReceiver extends BroadcastReceiver {
    public MyBootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        L.i("sky_MyBootReceiver", "MyBootReceiver action:" + intent.getAction());
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)
                || intent.getAction().equals(Intent.ACTION_SCREEN_ON)
                || intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Intent intent1 = new Intent();
            intent1.setClass(context, RunActivity.class);
            intent1.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        } else if (intent.getAction().equals(Intent.ACTION_SHUTDOWN)) {
            RunManager.getInstance().reset();
        }
    }
}