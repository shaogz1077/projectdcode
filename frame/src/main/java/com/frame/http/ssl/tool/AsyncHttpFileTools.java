package com.frame.http.ssl.tool;

import android.os.AsyncTask;

import com.frame.http.ssl.AsyncHttpClient;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传工具
 *
 * @version V1.0
 * @Title: AsyncHttpFileTools.java
 * @Package: com.bangyibang.weixinmh.common.asynchttptools
 * @author: ollie
 * @date 2015-4-20 下午4:23:08
 */
public class AsyncHttpFileTools {
    /**
     * 微商添加商品接口
     *
     * @param @param  fakeID
     * @param @param  token
     * @param @param  name
     * @param @param  price
     * @param @param  descript
     * @param @param  map
     * @param @return
     * @return boolean
     * @throws
     * @Title: upLoadBusinessFile
     */
    public boolean upLoadBusinessFile(String fakeID, String token, String name,
                                      String price, String descript) {
        try {
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.setTimeout(5000);
            JSONObject json = new JSONObject();
            Map<String, String> params = new HashMap<String, String>();
            params.put("t", "wsy_WSYproductAPI");
            params.put("a", "addProduct");
            json.put("fakeID", fakeID);
            json.put("token", token);
            json.put("name", name);
            json.put("price", price);
            json.put("descript", descript);
            params.put("p", json.toString());
            Map<String, File> mapFile = new HashMap<String, File>();
            // if (map != null && !map.isEmpty()) {
            // int i = 1;
            // for (ImageItem item : map) {
            // if (item.getImagePath() != null
            // && item.getImagePath().length() > 0) {
            File file = new File("");
            if (file.exists()) {
                // String fileCode = item.getImagePath().substring(
                // item.getImagePath().lastIndexOf("."),
                // item.getImagePath().length());
                mapFile.put("image", file);
                // i++;
                // }
                // }
                // }
            }
            new FileUploadData().execute(params, mapFile);
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * @param @param  fakeID
     * @param @param  token
     * @param @param  name
     * @param @param  price
     * @param @param  descript
     * @param @param  map
     * @param @return
     * @return boolean
     * @throws
     * @Title: upLoadBusinessEditFile
     */
    public boolean upLoadBusinessEditFile(String fakeID, String token,
                                          String name, String price, String descript, String productID,
                                          List<Map<String, String>> list, int _i) {
        try {
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.setTimeout(5000);
            JSONObject json = new JSONObject();
            Map<String, String> params = new HashMap<String, String>();
            params.put("t", "wsy_WSYproductAPI");
            params.put("a", "editProduct");
            json.put("fakeID", fakeID);
            json.put("token", token);
            json.put("name", name);
            json.put("price", price);
            json.put("descript", descript);
            json.put("productID", productID);
            params.put("p", json.toString());
            Map<String, File> mapFile = new HashMap<String, File>();
            if (list != null && !list.isEmpty()) {
                int i = _i;
                for (Map<String, String> map : list) {
                    String item = map.get("imageurl");
                    File file = new File(map.get("imageurl"));
                    if (file.exists()) {
                        String fileCode = item.substring(item.lastIndexOf("."),
                                item.length());
                        mapFile.put("image" + i + fileCode, file);
                        i++;
                    }
                }
            }
            new FileUploadData().execute(params, mapFile);
        } catch (Exception e) {

        }
        return false;
    }

    class FileUploadData extends AsyncTask<Object, Object, Object> {

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... paramVarArgs) {
            Map<String, String> params = (Map<String, String>) paramVarArgs[0];
            Map<String, File> files = (Map<String, File>) paramVarArgs[1];
            try {
                String result = ImageFileUpTool.post(params, files);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            if (result != null) {
            }
        }
    }

}
