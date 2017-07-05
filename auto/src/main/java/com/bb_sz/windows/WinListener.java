package com.bb_sz.windows;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/23.
 */

public class WinListener {

    public static void main(String[] args){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runCmd(String cmd) {
        try {
            Process ps = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
