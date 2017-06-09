package com.bb_sz.deviceinfo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        testCurPage();
        testPid();
    }

    private void testPid() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                        testPid(App.this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void testCurPage() {
        try {
            java.lang.Process proc = Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int pid = Process.myPid();
                        Thread.sleep(1000);
                        String cur = getCurrentActivityName1(App.this);
                        Log.i("CurAct", "cur = " + cur + ", pid = " + pid);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public String getCurrentActivityName1(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        return componentInfo.getClassName();
    }

    public void testPid(Context context) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mRunningProcess = mActivityManager.getRunningAppProcesses();
        int i = 1;
        for (ActivityManager.RunningAppProcessInfo amProcess : mRunningProcess) {
            Log.i("Application", (i++) + "PID: " + amProcess.pid + "(processName=" + amProcess.processName + "UID=" + amProcess.uid + ")");
        }
    }
}
