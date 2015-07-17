package com.frame.http.urlconnection;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * inputStream的数据转byte
 *
 * @version V1.0
 * @Title: ByteUtils.java
 * @Package: com.bangyibang.weixinmh.common.logic.json
 * @company: byb
 * @author: ollie
 * @date 2015-3-5 上午11:14:39
 */
public class ByteUtils {
    /**
     * inputStream转化为byte
     *
     * @param @param  in
     * @param @return
     * @return byte[]
     * @throws
     * @Title: inputStreamToByte
     */
    public static byte[] inputStreamToByte(InputStream in) {
        try {
            ByteArrayOutputStream beytOutputStream = new ByteArrayOutputStream();
            byte[] bt = new byte[1024];
            int len = 0;
            while ((len = in.read(bt)) != -1) {
                beytOutputStream.write(bt, 0, len);
            }
            beytOutputStream.close();
            in.close();
            return beytOutputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }

    }

}
