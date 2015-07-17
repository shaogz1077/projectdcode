package com.frame.http.urlconnection;

import android.os.AsyncTask;

/**
 * HTTPConnectTool工具类的异步操作的工具类
 *
 * @version V1.0
 * @Title: HTTPAsyncTask.java
 * @Package: com.frame.http.urlconnection
 * @company:
 * @author: ollie
 * @date 2015-7-1 下午1:56:13
 */
public class HTTPAsyncTask extends AsyncTask<Object, Object, Object> {
    private IHTTPAsyncTask iHttpAsyncTask;

    public HTTPAsyncTask(IHTTPAsyncTask _iHttpAsyncTask) {
        this.iHttpAsyncTask = _iHttpAsyncTask;
    }

    @Override
    protected Object doInBackground(Object... params) {
        String url = params[0] + "";
        String requestMethod = params[1] + "";
        Object object = HTTPConnectTool.getNetObjectData(url, requestMethod);
        return object;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if (iHttpAsyncTask != null) {
            iHttpAsyncTask.resultNetData(result);
        }
    }

}
