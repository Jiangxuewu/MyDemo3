package com.bb_sz.auto;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.bb_sz.auto.cmd.CMD;
import com.bb_sz.auto.manager.Contants;
import com.bb_sz.auto.manager.RunManager;
import com.bb_sz.auto.manager.SP;
import com.bb_sz.auto.server.RunService;
import com.bb_sz.lib.log.L;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2017/4/13.
 */

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initBugly();
        init();
        start();
        check();
    }

    private void initBugly() {
        String channel;
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            channel = appInfo.metaData.getString("channel");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            channel = "sky";
        }

        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppChannel(channel);//设置渠道
        strategy.setAppVersion(BuildConfig.VERSION_NAME); //App的版本
        strategy.setAppReportDelay(5000);  //设置SDK处理延时，毫秒
        Bugly.init(getApplicationContext(), Contants.BUGLY_ID, false, strategy);
    }

    private void check() {
        SP.getInstance().initInstall();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (1 == 1) {
                    try {
                        Thread.sleep(1000 * 60 * 3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        boolean runFlag = SP.getInstance().getBooleanValue(Contants.KEY_RUN_FLAG);
                        long time = SP.getInstance().getInstallTime();
                        long blank = System.currentTimeMillis() - time;
                        L.d(TAG, "check.....blank = " + blank + ", run flag is " + runFlag);
                        if (runFlag && time > 0 && blank > 1000 * 60 * 10) {
                            SP.getInstance().initInstall();
                            RunManager.getInstance().reset();
                            CMD.doSuExec("reboot");
                        }
                    }
                }
            }
        }).start();
    }

    private void start() {
        startService(new Intent(this, RunService.class));
        if (RunManager.getInstance().getSelPhoneType() == RunManager.P780) {
            RunService.start(this);
        }
    }

    private void init() {
        SP.getInstance().init(this.getApplicationContext());
    }
}
