package com.frame.http.ssl.tool;

/**
 * 基于android-async-http工具处理的回调工具接口
 *
 * @version V1.0
 * @Title: IAsyncHttpTools.java
 * @Package: com.bangyibang.weixinmh.common.asynchttptools
 * @company: byb
 * @author: ollie
 * @date 2015-3-30 下午5:54:33
 */
public interface IAsyncHttpTools {
    /**
     * 请求成功的网络数据
     *
     * @param @param object
     * @return void
     * @throws
     * @Title: callAsyncDataSuccess
     */
    void callAsyncDataSuccess(Object object);

    /**
     * 请求失败的网络数据
     *
     * @param @param object
     * @return void
     * @throws
     * @Title: callAsyncDataFailure
     */
    void callAsyncDataFailure(Object object);

    /**
     * 请求网络头部数据Header
     *
     * @param @param object
     * @return void
     * @throws
     * @Title: callAsyncHeaderData
     */
    void callAsyncHeaderData(Object object);
}
