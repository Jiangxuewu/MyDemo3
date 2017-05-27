package android.bb_sz.os;

import android.content.ContentResolver;
import android.os.UserHandle;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Test {
    public int getIpAddress() {
        return SystemProperties.getIntValue("refresh.ip", 2030151872);
    }

    public String getExtraInfo() {
        return SystemProperties.getStringValue("refresh.net_extrainfo", "");
    }

    public static String getString(ContentResolver resolver, String name) {
        if ("android_id".equals(name)){
            return SystemProperties.getStringValue("refresh.android_id", "");
        } else if ("bd_setting_i".equals(name)){
            return SystemProperties.getStringValue("refresh.device_id", "");
        }
        return getStringForUser(resolver, name, 1);
    }

    public static String getStringForUser(ContentResolver resolver, String name,
                                          int userHandle) {
        return "";
    }
}
