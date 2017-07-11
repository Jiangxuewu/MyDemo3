package com.bb_sz.auto.system;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017/7/11.
 */

public class RunningAppInfo {
    private String appLabel;
    private Drawable appIcon;
    private String pkgName;
    private int pid;
    private String processName;

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
