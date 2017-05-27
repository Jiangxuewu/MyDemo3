package com.bb_sz.auto.market;

/**
 * Created by Administrator on 2017/5/24.
 */

public class IP {
    public String ip;// 0->local data; 1->server data

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        sb.append("ip").append(":").append(ip).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
