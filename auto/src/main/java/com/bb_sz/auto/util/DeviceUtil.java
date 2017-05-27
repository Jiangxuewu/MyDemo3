package com.bb_sz.auto.util;

import com.bb_sz.auto.http.response.AppInfo;
import com.bb_sz.auto.http.response.BuildInfo;
import com.bb_sz.auto.http.response.NetWorkInfo;
import com.bb_sz.auto.http.response.ScreenInfo;
import com.bb_sz.auto.http.response.SimInfo;
import com.bb_sz.auto.http.response.WifiInfo;
import com.bb_sz.auto.info.InfoManager;

/**
 * Created by Administrator on 2017/4/11.
 */

public class DeviceUtil {

    public void createFile(AppInfo appInfo, BuildInfo buildInfo, NetWorkInfo netWorkInfo, ScreenInfo screenInfo, SimInfo simInfo, WifiInfo wifiInfo) {
        StringBuffer sb = new StringBuffer();
        if (null != appInfo)
            sb.append(appInfo.toString());
        if (null != buildInfo)
            sb.append(buildInfo.toString());
        if (null != netWorkInfo)
            sb.append(netWorkInfo.toString());
        if (null != screenInfo)
            sb.append(screenInfo.toString());
        if (null != simInfo)
            sb.append(simInfo.toString());
        if (null != wifiInfo)
            sb.append(wifiInfo.toString());
        FileUtil.writeContentToFile(InfoManager.FILE_PATH, sb.toString());
    }
}
