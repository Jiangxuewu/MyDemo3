package com.bb_sz.lib.device;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;

/**
 * Created by Administrator on 2016/12/16.
 */

public class DeviceConfig {

    private static final String LOG_TAG = "SkyDevice";
    private static DeviceConfig mInstance;
    private Context context;

    public static DeviceConfig getInstance() {
        synchronized (LOG_TAG) {
            if (null == mInstance) {
                mInstance = new DeviceConfig();
            }
            return mInstance;
        }
    }

    public void init(Context context) {
        this.context = context;
    }

    public boolean checkPermission(String paramString) {
        return context.getPackageManager().checkPermission(paramString, context.getPackageName()) == 0;
    }

    public String getMac() {
        try {
            WifiManager localWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (checkPermission("android.permission.ACCESS_WIFI_STATE")) {
                return localWifiManager.getConnectionInfo().getMacAddress();
            } else {
                Log.w(LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            }

        } catch (Exception localException) {
            Log.w(LOG_TAG, "Could not get mac address." + localException.toString());
        }
        return "";
    }

    public String getSSID() {
        try {
            WifiManager localWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (checkPermission("android.permission.ACCESS_WIFI_STATE")) {
                return localWifiManager.getConnectionInfo().getSSID();
            } else {
                Log.w(LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            }

        } catch (Exception localException) {
            Log.w(LOG_TAG, "Could not get mac address." + localException.toString());
        }
        return "";
    }

    public String getDeviceId() {
        TelephonyManager localTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (localTelephonyManager == null) {
            Log.w(LOG_TAG, "No IMEI.");
            return "";
        }
        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                String str1 = localTelephonyManager.getDeviceId();
                if (TextUtils.isEmpty(str1)) {
                    Log.w(LOG_TAG, "No IMEI.");
                    str1 = getMac();
                    if (TextUtils.isEmpty(str1)) {
                        Log.w(LOG_TAG, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
                        str1 = Settings.Secure.getString(context.getContentResolver(), "android_id");
                        Log.i(LOG_TAG, "getDeviceId: Secure.ANDROID_ID: " + str1);
                    }
                }
                return str1;
            }
        } catch (Exception localException) {
            android.util.Log.w(LOG_TAG, "No IMEI.", localException);
        }
        return "";
    }

    public String getCPU() {
        String str1 = null;
        try {
            FileReader localFileReader = new FileReader("/proc/cpuinfo");
            try {
                BufferedReader localBufferedReader = new BufferedReader(localFileReader, 1024);
                str1 = localBufferedReader.readLine();
                localBufferedReader.close();
                localFileReader.close();
                if (str1 != null)
                    str1 = str1.substring(1 + str1.indexOf(':'));
                return str1.trim();
            } catch (IOException localIOException) {
                android.util.Log.e(LOG_TAG, "Could not read from file /proc/cpuinfo", localIOException);
                str1 = "";
            }
        } catch (FileNotFoundException localFileNotFoundException) {
            android.util.Log.e(LOG_TAG, "Could not open file /proc/cpuinfo", localFileNotFoundException);
            str1 = "";
        }
        return str1;
    }

    private int reflectMetrics(Object paramObject, String paramString) {
        try {
            Field localField = DisplayMetrics.class.getDeclaredField(paramString);
            localField.setAccessible(true);
            int i = localField.getInt(paramObject);
            return i;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return -1;
    }


    public String getResolution() {
        try {
            int i = -1;
            int j = -1;
            DisplayMetrics localDisplayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(localDisplayMetrics);
            if ((0x2000 & context.getApplicationInfo().flags) == 0) {
                j = reflectMetrics(localDisplayMetrics, "noncompatWidthPixels");
                i = reflectMetrics(localDisplayMetrics, "noncompatHeightPixels");
                if (i == -1 || j == -1) {
                    j = localDisplayMetrics.widthPixels;
                    i = localDisplayMetrics.heightPixels;
                }
                StringBuffer localStringBuffer = new StringBuffer();
                localStringBuffer.append(j);
                localStringBuffer.append("*");
                localStringBuffer.append(i);
                String str = localStringBuffer.toString();
                return str;
            }
        } catch (Exception localException) {
            android.util.Log.e(LOG_TAG, "read resolution fail", localException);
        }
        return "Unknown";
    }


    public String getAppVersionName() {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
        }
        return "Unknown";
    }

    public String getAppVersionCode() {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
        }
        return "Unknown";
    }


    public int getTimeZone() {
        try {
            Calendar localCalendar = Calendar.getInstance(getLocale());
            if (localCalendar != null) {
                return localCalendar.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Exception localException) {
            android.util.Log.i(LOG_TAG, "error in getTimeZone", localException);
        }
        return 8;
    }

    private Locale getLocale() {
        Locale localLocale = null;
        try {
            Configuration localConfiguration = new Configuration();
            Settings.System.getConfiguration(context.getContentResolver(), localConfiguration);
            if (localConfiguration != null)
                localLocale = localConfiguration.locale;
            if (localLocale == null)
                localLocale = Locale.getDefault();
        } catch (Exception localException) {
            Log.e(LOG_TAG, "fail to read user config locale");
        }
        return localLocale;
    }

    public String[] getLocaleInfo() {
        String[] arrayOfString = new String[2];
        try {
            Locale localLocale = getLocale();
            if (localLocale != null) {
                arrayOfString[0] = localLocale.getCountry();
                arrayOfString[1] = localLocale.getLanguage();
            }
            if (TextUtils.isEmpty(arrayOfString[0]))
                arrayOfString[0] = "Unknown";
            if (TextUtils.isEmpty(arrayOfString[1]))
                arrayOfString[1] = "Unknown";
            return arrayOfString;
        } catch (Exception localException) {
            android.util.Log.e(LOG_TAG, "error in getLocaleInfo", localException);
        }
        return arrayOfString;
    }

    public String[] getNetworkAccessMode() {
        String[] arrayOfString = {"Unknown", "Unknown"};
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
                arrayOfString[0] = "Unknown";
                return arrayOfString;
            }
            ConnectivityManager localConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (localConnectivityManager == null) {
                arrayOfString[0] = "Unknown";
                return arrayOfString;
            }
            if (localConnectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
                arrayOfString[0] = "Wi-Fi";
                return arrayOfString;
            }
            NetworkInfo localNetworkInfo = localConnectivityManager.getNetworkInfo(0);
            if (localNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                arrayOfString[0] = "2G/3G";
                arrayOfString[1] = localNetworkInfo.getSubtypeName();
                return arrayOfString;
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return arrayOfString;
    }

    public String getOperator() {
        try {
            return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperatorName();
        } catch (Exception localException) {
            android.util.Log.i(LOG_TAG, "read carrier fail", localException);
        }
        return "Unknown";
    }

    public String getDeviceIdMD5() {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(getDeviceId().getBytes());
            byte[] arrayOfByte = localMessageDigest.digest();
            StringBuffer localStringBuffer = new StringBuffer();
            for (int i = 0; ; i++) {
                if (i >= arrayOfByte.length)
                    return localStringBuffer.toString();
                localStringBuffer.append(Integer.toHexString(0xFF & arrayOfByte[i]));
            }
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            android.util.Log.i(LOG_TAG, "getMD5 error", localNoSuchAlgorithmException);
        }
        return "";
    }

    public String getIP() {
        String[] mode = getNetworkAccessMode();
        if (null == mode) return getLocalIpAddress();
        if ("Wi-Fi".equals(mode[0])) {
            return getWifiIP();
        }
        return getLocalIpAddress();
    }

    public String getWifiIP() {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    private String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }

    public String getIMSI() {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            Log.w(LOG_TAG, "No IMSI.");
            return "";
        }

        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                return tm.getSubscriberId();
            }
        } catch (Exception localException) {
            android.util.Log.w(LOG_TAG, "No IMSI.", localException);
        }
        return "";
    }

    public String getSimSerialNumber() {
        TelephonyManager localTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (localTelephonyManager == null) {
            Log.w(LOG_TAG, "No IMEI.");
            return "";
        }
        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                return localTelephonyManager.getSimSerialNumber();
            }
        } catch (Exception localException) {
            android.util.Log.w(LOG_TAG, "No IMEI.", localException);
        }
        return "";
    }

    public String getAndroidId() {
        String id;
        try {
            id = android.provider.Settings.Secure.getString(
                    context.getContentResolver(),
                    android.provider.Settings.Secure.ANDROID_ID);
        } catch (Exception ignored) {
            id = "";
        }
        return id;
    }
}
