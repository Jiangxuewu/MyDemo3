package com.bb_sz.auto.market;

/**
 * Created by Administrator on 2017/5/22.
 */

public class VpnInfo {
    public String name;// 服务器名称
    public String server;// 服务器地址
    public String userName;// 用户名
    public String password;// 密码

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        sb.append("name").append(":").append(name).append("\n");
        sb.append("server").append(":").append(server).append("\n");
        sb.append("userName").append(":").append(userName).append("\n");
        sb.append("password").append(":").append(password).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
