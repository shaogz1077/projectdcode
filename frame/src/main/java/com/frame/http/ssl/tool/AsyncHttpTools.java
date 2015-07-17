package com.frame.http.ssl.tool;

import android.content.Context;

import com.frame.http.ssl.AsyncHttpClient;
import com.frame.http.ssl.AsyncHttpResponseHandler;
import com.frame.http.ssl.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

/**
 * 基于android-async-http工具处理的工具类
 *
 * @version V1.0
 * @Title: AsyncHttpTools.java
 * @Package: com.bangyibang.weixinmh.common.asynchttptools
 * @author: ollie
 * @date 2015-3-30 下午5:14:55
 */
public class AsyncHttpTools {

    private static AsyncHttpClient asyncHttpClient;

    /**
     * 初始化请求方式
     *
     * @param
     * @return void
     * @throws
     * @Title: settingHttp
     */
    private static AsyncHttpClient settingHttp() {
        if (asyncHttpClient == null) {
            asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.setTimeout(5000);
        }
        return asyncHttpClient;
    }

	/*
     * ===================================================GET请求方式================
	 * =========================================
	 */

    /**
     * 根据url获取网络数据
     *
     * @param @param url 请求地址
     * @param @param iAsyncHttpTools
     * @return void
     * @throws
     * @Title: httpGetData
     */
    public static void httpGetData(String url,
                                   final IAsyncHttpTools iAsyncHttpTools) {
        settingHttp().get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataSuccess(new String(
                            responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataFailure(responseBody);
                }

            }
        });
    }

    /**
     * 根据上下文和地址请求网络
     *
     * @param @param context 上下文
     * @param @param url 请求地址
     * @param @param iAsyncHttpTools
     * @return void
     * @throws
     * @Title: httpGetData
     */
    public static void httpGetData(Context context, String url,
                                   final IAsyncHttpTools iAsyncHttpTools) {
        settingHttp().get(context, url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataSuccess(responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataFailure(responseBody);
                }
            }
        });
    }

    /**
     * 根据请求数据和地址请求网络数据
     *
     * @param @param url 请求地址
     * @param @param params 请求数据
     * @param @param iAsyncHttpTools
     * @return void
     * @throws
     * @Title: httpGetData
     */
    public static void httpGetData(String url, RequestParams params,
                                   final IAsyncHttpTools iAsyncHttpTools) {
        settingHttp().get(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataSuccess(responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataFailure(responseBody);
                }
            }
        });
    }

    /**
     * 根据下面参数请求数据
     *
     * @param @param context 上下文
     * @param @param url 请求地址
     * @param @param headers 头文件数据
     * @param @param params 请求数据
     * @param @param iAsyncHttpTools
     * @return void
     * @throws
     * @Title: httpGetData
     */
    public static void httpGetData(Context context, String url,
                                   Header[] headers, RequestParams params,
                                   final IAsyncHttpTools iAsyncHttpTools) {
        settingHttp().get(context, url, headers, params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          byte[] responseBody) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataSuccess(responseBody);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] responseBody, Throwable error) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataFailure(responseBody);
                        }
                    }
                });
    }

	/*
     * ===================================================POST请求方式================
	 * =========================================
	 */

    public static void httpPostData(String url,
                                    final IAsyncHttpTools iAsyncHttpTools) {
        settingHttp().post(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataSuccess(responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataFailure(responseBody);
                }
            }
        });
    }

    /**
     * @param @param url
     * @param @param params
     * @param @param iAsyncHttpTools
     * @return void
     * @throws
     * @Title: httpPostData
     */
    public static void httpPostData(String url, RequestParams params,
                                    final IAsyncHttpTools iAsyncHttpTools) {
        settingHttp().post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataSuccess(responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (iAsyncHttpTools != null) {
                    iAsyncHttpTools.callAsyncDataFailure(responseBody);
                }
            }
        });
    }

    /**
     * @param @param context
     * @param @param url
     * @param @param params
     * @param @param iAsyncHttpTools
     * @return void
     * @throws
     * @Title: httpPostData
     */
    public static void httpPostData(Context context, String url,
                                    RequestParams params, final IAsyncHttpTools iAsyncHttpTools) {
        settingHttp().post(context, url, params,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          byte[] responseBody) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataSuccess(responseBody);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] responseBody, Throwable error) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataFailure(responseBody);
                        }
                    }
                });
    }

    /**
     * @param @param context
     * @param @param url
     * @param @param entity
     * @param @param contentType
     * @param @param iAsyncHttpTools
     * @return void
     * @throws
     * @Title: httpPostData
     */
    public static void httpPostData(Context context, String url,
                                    HttpEntity entity, String contentType,
                                    final IAsyncHttpTools iAsyncHttpTools) {
        settingHttp().post(context, url, entity, contentType,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          byte[] responseBody) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataSuccess(responseBody);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] responseBody, Throwable error) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataFailure(responseBody);
                        }
                    }
                });
    }

    /**
     * @param @param context
     * @param @param url
     * @param @param headers
     * @param @param entity
     * @param @param contentType
     * @param @param iAsyncHttpTools
     * @return void
     * @throws
     * @Title: httpPostData
     */
    public static void httpPostData(Context context, String url,
                                    Header[] headers, HttpEntity entity, String contentType,
                                    final IAsyncHttpTools iAsyncHttpTools) {
        settingHttp().post(context, url, headers, entity, contentType,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          byte[] responseBody) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataSuccess(responseBody);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] responseBody, Throwable error) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataFailure(responseBody);
                        }
                    }
                });
    }

    /**
     * @param @param context
     * @param @param url
     * @param @param headers
     * @param @param params
     * @param @param contentType
     * @param @param iAsyncHttpTools
     * @return void
     * @throws
     * @Title: httpPostData
     */
    public static void httpPostData(Context context, String url,
                                    Header[] headers, RequestParams params, String contentType,
                                    final IAsyncHttpTools iAsyncHttpTools) {
        AsyncHttpClient asyncHttpClientd = new AsyncHttpClient();
        asyncHttpClientd.setTimeout(5000);
        asyncHttpClientd.post(context, url, headers, params, contentType,
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          byte[] responseBody) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataSuccess(new String(
                                    responseBody));
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] responseBody, Throwable error) {
                        if (iAsyncHttpTools != null) {
                            iAsyncHttpTools.callAsyncDataFailure(responseBody);
                        }
                    }
                });
    }
}
