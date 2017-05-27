package com.bb_sz.auto.http.response;

/**
 * Created by Administrator on 2017/4/11.
 */

public class NetWorkInfo {


    /**
     * _id : 1
     * net_extrainfo : cmnet
     * net_subtype : 2
     * net_subtype_name : EDGE
     * net_type : 0
     * net_type_name : MOBILE
     */

    private String _id;
    private String net_extrainfo;
    private int net_subtype;
    private String net_subtype_name;
    private int net_type;
    private String net_type_name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNet_extrainfo() {
        return net_extrainfo;
    }

    public void setNet_extrainfo(String net_extrainfo) {
        this.net_extrainfo = net_extrainfo;
    }

    public int getNet_subtype() {
        return net_subtype;
    }

    public void setNet_subtype(int net_subtype) {
        this.net_subtype = net_subtype;
    }

    public String getNet_subtype_name() {
        return net_subtype_name;
    }

    public void setNet_subtype_name(String net_subtype_name) {
        this.net_subtype_name = net_subtype_name;
    }

    public int getNet_type() {
        return net_type;
    }

    public void setNet_type(int net_type) {
        this.net_type = net_type;
    }

    public String getNet_type_name() {
        return net_type_name;
    }

    public void setNet_type_name(String net_type_name) {
        this.net_type_name = net_type_name;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("refresh.net_extrainfo=").append(net_extrainfo).append("\n");
        sb.append("refresh.net_subtype=").append(net_subtype).append("\n");
        sb.append("refresh.net_subtype_name=").append(net_subtype_name).append("\n");
        sb.append("refresh.net_type=").append(net_type).append("\n");
        sb.append("refresh.net_type_name=").append(net_type_name).append("\n");
        return sb.toString();
    }
}
