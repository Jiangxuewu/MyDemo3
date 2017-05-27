package com.bb_sz.sms;

/**
 * Created by Administrator on 2017/3/29.
 */

public class Conversations {
    private int msg_count;
    private String thread_id;
    private String snippte;

    public Conversations(String thread_id, int msg_count, String snippte) {
        this.msg_count = msg_count;
        this.thread_id = thread_id;
        this.snippte = snippte;
    }
}
