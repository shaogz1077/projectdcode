package com.frame.http.urlconnection;

/**
 * HTTPAsyncTask异步操作的回调接口
 *
 * @version V1.0
 * @Title: IHTTPAsyncTask.java
 * @Package: com.frame.http.urlconnection
 * @company:
 * @author: ollie
 * @date 2015-7-1 下午1:50:57
 */
public interface IHTTPAsyncTask {
    /**
     * 回调请求回来的数据
     *
     * @param @param object
     * @return void
     * @throws
     * @Title: resultNetData 请求后的数据
     */
    void resultNetData(Object object);

}
