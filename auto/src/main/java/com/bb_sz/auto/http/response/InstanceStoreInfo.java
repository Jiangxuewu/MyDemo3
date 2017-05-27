package com.bb_sz.auto.http.response;

/**
 * Created by Administrator on 2017/4/11.
 */

public class InstanceStoreInfo {


    /**
     * _id : 1
     * app_id : 1
     * build_id : 1
     * net_work_id : 1
     * screen_id : 1
     * sim_id : 1
     * wifi_id : 0
     */

    private int _id;
    private int app_id;
    private int build_id;
    private int net_work_id;
    private int screen_id;
    private int sim_id;
    private int wifi_id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getBuild_id() {
        return build_id;
    }

    public void setBuild_id(int build_id) {
        this.build_id = build_id;
    }

    public int getNet_work_id() {
        return net_work_id;
    }

    public void setNet_work_id(int net_work_id) {
        this.net_work_id = net_work_id;
    }

    public int getScreen_id() {
        return screen_id;
    }

    public void setScreen_id(int screen_id) {
        this.screen_id = screen_id;
    }

    public int getSim_id() {
        return sim_id;
    }

    public void setSim_id(int sim_id) {
        this.sim_id = sim_id;
    }

    public int getWifi_id() {
        return wifi_id;
    }

    public void setWifi_id(int wifi_id) {
        this.wifi_id = wifi_id;
    }
}
