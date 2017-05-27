package com.bb_sz.auto.manager;

/**
 * Created by Administrator on 2017/4/13.
 */

public class IDManager {
    private static final String TAG = IDManager.class.getSimpleName();
    private static IDManager mInstance;

    public static IDManager getInstance() {
        synchronized (TAG) {
            if (null == mInstance) {
                mInstance = new IDManager();
            }
            return mInstance;
        }
    }

    private IDManager() {

    }

    public int getID() {
        return SP.getInstance().getIntValue(Contants.KEY_RUN_ID, 1) + Setting.COUNT;
    }

    public int _getID() {
        return SP.getInstance().getIntValue(Contants.KEY_RUN_ID, 1);
    }

    public void setID(int id) {
        SP.getInstance().setIntValue(Contants.KEY_RUN_ID, id);
    }

    public void setID() {
        SP.getInstance().setIntValue(Contants.KEY_RUN_ID, _getID() + 1);
    }
}
