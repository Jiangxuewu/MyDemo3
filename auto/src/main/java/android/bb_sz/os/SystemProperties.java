package android.bb_sz.os;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by Administrator on 2017/4/12.
 */

public class SystemProperties {
    private static Class<?> mCls = null;
    private static Method mNativeGetInt = null;
    private static Method mNativeGetLong = null;
    private static Method mNativeGetString_1 = null;
    private static Method mNativeGetString_2 = null;
    private static final String propFile = "/data/local/tmp/test.prop";
    private static final String resetPropFile = "/data/local/tmp/reset.prop";

    private static boolean debug = false;

    public static boolean isNeedChange() {
        return true;
    }

    private static void init() {
        try {
            if (mCls == null) {
                mCls = Class.forName("android.os.SystemProperties");
            }
        } catch (Exception ignored) {
            mCls = null;
        }

        if (null != mCls) {
            try {
                mNativeGetString_1 = mCls.getMethod("_get", String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if (null != mCls) {
            try {
                mNativeGetString_2 = mCls.getMethod("_get", String.class, String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if (null != mCls) {
            try {
                mNativeGetInt = mCls.getMethod("_getInt", String.class, int.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if (null != mCls) {
            try {
                mNativeGetLong = mCls.getMethod("_getLong", String.class, long.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static float getFloat(String key, int value) {
        if (debug) Log.v("sky", "getFloat key = " + key + ", value = " + value);
        return getFloatValue(key, value);
    }

    public static String native_get(String key) {
        if (debug) Log.v("sky", "native_get key = " + key);
        return getStringValue(key);
    }

    public static String native_get(String key, String value) {
        if (debug) Log.v("sky", "native_get key = " + key + ", value = " + value);
        return getStringValue(key, value);
    }

    public static int native_get_int(String key, int value) {
        if (debug) Log.v("sky", "native_get_int key = " + key + ", value = " + value);
        return getIntValue(key, value);
    }

    public static long native_get_long(String key, long value) {
        if (debug) Log.v("sky", "native_get_long key = " + key + ", value = " + value);
        return getLongValue(key, value);
    }

    public static String getStringValue(final String key) {
        init();
        String result = null;
        String newKey = changed(key);
        if (new File(propFile).exists() && !TextUtils.isEmpty(newKey) && newKey.startsWith("refresh")) {
            result = getMockProp().getProperty(newKey, "");
        }

        if (null == result && null != mNativeGetString_1) {
            try {
                result = (String) mNativeGetString_1.invoke(null, key);
            } catch (Exception e) {
                e.printStackTrace();
                result = "";
                if (debug)
                    Log.v("sky2", "getStringValue0 err key = " + key + ", value = " + result);
            }
        }

        if (debug) Log.v("sky2", "getStringValue0 key = " + key + ", value = " + result);
        return result;
    }

    public static String getStringValue(final String key, final String value) {
        init();
        String result = value;
        String newKey = changed(key);
        if (new File(propFile).exists() && !TextUtils.isEmpty(newKey) && newKey.startsWith("refresh")) {
            result = getMockProp().getProperty(newKey, "" + result);
        }

        if (null != result && result.equals(value) && null != mNativeGetString_2) {
            try {
                result = (String) mNativeGetString_2.invoke(null, key, value);
            } catch (Exception e) {
                e.printStackTrace();
                result = value;
                if (debug)
                    Log.v("sky2", "getStringValue err key = " + key + ", value = " + result + ", msg = ");
            }
        }

        if (debug) Log.v("sky2", "getStringValue key = " + key + ", value = " + result);
        return result;
    }

    public static long getLongValue(final String key, final long value) {
        init();
        long result = value;
        String newKey = changed(key);
        if (new File(propFile).exists() && !TextUtils.isEmpty(newKey) && newKey.startsWith("refresh")) {
            result = Long.parseLong(getMockProp().getProperty(newKey, "" + result));
        }

        if (result == value && null != mNativeGetLong) {
            try {
                result = (long) mNativeGetLong.invoke(null, key, value);
            } catch (Exception e) {
                e.printStackTrace();
                result = value;
                if (debug) Log.v("sky2", "getLongValue err key = " + key + ", value = " + result);
            }
        }
        if (debug) Log.v("sky2", "getLongValue key = " + key + ", value = " + result);
        return result;
    }

    public static int getIntValue(final String key, final int value) {
        init();
        int result = value;
        String newKey = changed(key);
        if (new File(propFile).exists() && !TextUtils.isEmpty(newKey) && newKey.startsWith("refresh")) {
            try {
                result = Integer.parseInt(getMockProp().getProperty(newKey, "" + result));
            } catch (Exception ignored) {
                result = value;
                if (debug) Log.v("sky2", "getIntValue err 1 key = " + key + ", value = " + result);
            }
        }

        if (result == value && null != mNativeGetInt) {
            try {
                Object object = mNativeGetInt.invoke(null, key, value);
                if (null != object && object instanceof Integer)
                    result = (int) object;
            } catch (Exception e) {
                e.printStackTrace();
                result = value;
                if (debug)
                    Log.v("sky2", "getIntValue err 2 key = " + key + ", value = " + result);
            }
        }
        if (debug) Log.v("sky2", "getIntValue key = " + key + ", value = " + result);
        return result;
    }

    public static float getFloatValue(final String key, final float value) {
        init();
        float result = value;
        String newKey = changed(key);
        if (new File(propFile).exists() && !TextUtils.isEmpty(newKey) && newKey.startsWith("refresh")) {
            result = Float.parseFloat(getMockProp().getProperty(newKey, "" + result));
        }
        if (debug) Log.v("sky2", "getFloatValue key = " + key + ", value = " + result);
        return result;
    }


    private static String changed(String key) {
        if (key.equals("ro.build.id"))
            return "refresh.id";
        else if (key.equals("ro.product.model"))
            return "refresh.model";
        else if (key.equals("ro.serialno"))
            return "refresh.serial";
        else if (key.equals("ro.build.version.release"))
            return "refresh.version";
        else if (key.equals("ro.build.version.sdk"))
            return "refresh.api";
        else if (key.equals("ro.product.manufacturer"))
            return "refresh.manufacturer";
        else if (key.equals("ro.product.brand"))
            return "refresh.brand";
        else if ((key.equals("ro.build.product")) || ("ro.product.name".equals(key)))
            return "refresh.product";
        else if (key.equals("ro.product.device"))
            return "refresh.device";
        else if (key.equals("ro.product.board"))
            return "refresh.board";
        else if (key.equals("ro.product.cpu.abi"))
            return "refresh.cpuabi";
        else if (key.equals("ro.product.cpu.abi2"))
            return "refresh.cpuabi2";
        else if (key.equals("ro.hardware"))
            return "refresh.hardware";
        else if (key.equals("qemu.sf.lcd_density") || key.equals("ro.sf.lcd_density"))
            return "refresh.densityDpi";
        else
            return key;
    }

    public static Properties getMockProp() {
        long time = SystemClock.elapsedRealtime();
        Log.v("SKYTime", "elapsedRealtime = " + time);
        if (time <= 1000 * 60) {
            return getMockProp(resetPropFile);
        }
        return getMockProp(propFile);
    }

    public static Properties getMockProp(String file) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            prop.load(in);
        } catch (Exception ignored) {
        } finally {
            if (null != in)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return prop;
    }
}
