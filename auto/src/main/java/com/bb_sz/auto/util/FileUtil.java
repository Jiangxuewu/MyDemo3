package com.bb_sz.auto.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/11.
 */

public class FileUtil {
    public static void writeContentToFile(String content, String path) {
        FileOutputStream fos = null;
        try {
            File pathf = new File(path);
            fos = new FileOutputStream(path, false);
            fos.write(content.getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
