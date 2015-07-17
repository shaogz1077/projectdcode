package com.frame.http.urlconnection;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * httpurlconnection的请求工具类
 *
 * @version V1.0
 * @Title: HTTPConnectTool.java
 * @Package: com.frame.http.urlconnection
 * @company: byb
 * @author: ollie
 * @date 2015-7-1 上午11:11:48
 */
public class HTTPConnectTool {
    /**
     * @param @param  httpurl 请求接口
     * @param @param  requestMethod 请求类型 GET,POST,DELETE,PUT,OPTIONS,HEAD,TRACE,CONECT
     * @param @return
     * @return Object
     * @throws
     * @Title: getNetObjectData
     */
    public static Object getNetObjectData(String httpurl, String requestMethod) {
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpClient = (HttpURLConnection) url
                    .openConnection();
            httpClient.setConnectTimeout(5 * 1000);// 请求响应的时间
            httpClient.setRequestMethod(requestMethod);// 设置请求的类型
            httpClient.connect();
            if (httpClient.getResponseCode() == 200) {// 请求成功
                InputStream in = httpClient.getInputStream();
                if (in != null) {
                    byte[] byteStr = ByteUtils.inputStreamToByte(in);// 把io类型文件转为byte文件
                    String jsonStr = new String(byteStr);
                    return jsonStr;
                } else {
                    return "error";// 返回错误的标识
                }
            } else {
                return httpClient.getResponseCode() + "";//返回错误的代码
            }
        } catch (Exception e) {
        } catch (OutOfMemoryError e) {// 预防内存溢出
        }
        return "error";// 返回错误的标识
    }

}
