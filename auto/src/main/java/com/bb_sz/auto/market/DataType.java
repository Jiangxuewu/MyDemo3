package com.bb_sz.auto.market;

/**
 * Created by Administrator on 2017/5/16.
 */

public class DataType {
    public int m_type;// 0->local data; 1->server data
    public int m_start;// 起始值

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        sb.append("m_type").append(":").append(m_type).append("\n");
        sb.append("m_start").append(":").append(m_start).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
