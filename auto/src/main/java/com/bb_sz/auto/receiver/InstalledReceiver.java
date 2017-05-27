package com.bb_sz.auto.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.auto.manager.SP;
import com.bb_sz.lib.log.L;

public class InstalledReceiver extends BroadcastReceiver {

    private static final String TAG = "InstalledReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        L.i(TAG, "action:" + action);
        if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
            String packageName = intent.getDataString();
            L.i(TAG, "安装了 :" + packageName);
            RunManager.getInstance().uploadToServer(context, packageName, 1);
            if (null != packageName && packageName.startsWith("package:")) {
                packageName = packageName.replace("package:", "");
                if (!packageName.startsWith("com.bb_sz.") && !packageName.startsWith("com.tencent.android.")) {
                    CMD.doSuExec("pm uninstall " + packageName);
                }
            }
            SP.getInstance().initInstall();
        } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
            String packageName = intent.getDataString();
            L.i(TAG, "卸载了 :" + packageName);
            RunManager.getInstance().uploadToServer(context, packageName, 2);
        }
    }
}
