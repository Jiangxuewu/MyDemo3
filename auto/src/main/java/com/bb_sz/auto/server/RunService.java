package com.bb_sz.auto.server;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import com.bb_sz.auto.App;
import com.bb_sz.auto.RunActivity;
import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.lib.log.L;

import java.util.List;

public class RunService extends Service {
    private static final String TAG = RunService.class.getSimpleName();

    public RunService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.i(TAG, "onStartCommand");
        checkProcess();
        return START_STICKY;
    }

    private void checkProcess() {
        checkLauncherAct();
    }

    private void checkLauncherAct() {
        final String autoPkg = getPackageName();
        final String autoLauncher = RunActivity.class.getName();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isForeground(RunService.this, autoLauncher)) {
                    L.i(TAG, "is not exists");
                    CMD.doSuExec(CMD.getLaunchCMD(autoPkg, autoLauncher));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        if (!isForeground(this, autoLauncher)) {
//            L.i(TAG, "is not exists");
//            CMD.doSuExec(CMD.getLaunchCMD(autoPkg, autoLauncher));
//        } else
//            L.i(TAG, "is exists");
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     */
    private boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void start(Context context) {
        context.startService(new Intent(context, RunService.class));
    }
}
